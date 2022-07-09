package com.gb.reviews.review;

import com.gb.reviews.notifier.UserEmailNotification;
import com.gb.reviews.notifier.UserEmailNotifier;
import com.gb.reviews.notifier.UserEmailNotifierImpl;
import com.gb.reviews.repository.ReviewRepository;
import com.gb.reviews.repository.UserRepository;
import com.gb.reviews.user.User;
import com.gb.reviews.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.gb.reviews.review.ReviewType.CERTIFIED_BUYER;

@Getter
@Setter
@ToString
public class Review {
    private long id;
    private long productId;
    private int rating;
    private String title;
    private String text;
    private String userId;
    private LocalDateTime reviewedDate;
    private String location;

    private List<Meta> metas;
    public List<Feature> features;
    private ReviewState reviewState;
    private ReviewType reviewType;

    public Review() {
        this.id = Utils.getRandomLong();
    }

    public void addReview(long productId, int rating, String title,
                          String text, List<Meta> metas, String userId,
                          List<Feature> features) {
        if (rating <= 0 || rating >= 5)
            return;
        this.id = Utils.getRandomLong();
        this.productId = productId;
        this.rating = rating;
        this.title = title;
        this.text = text;
        this.metas = metas;
        this.userId = userId;
        this.reviewedDate = LocalDateTime.now();
        User user = new User(userId);
        user.setUserProfile(UserRepository.usersMap.get(userId).getUserProfile());
        this.location = user.getUserProfile().getUserLocation().getCity();
        this.features = features;
        reviewState = ReviewState.MODERATION_PENDING;
    }

    public Review addReview(Review review) {
        ReviewRepository.reviews.add(review);
        ReviewRepository.reviewMap.put(review.getProductId(), review);
        moderate(review);
        updateReviewType(review);
        return review;
    }

    public List<Review> getReviewsByProduct(long productId) {
        return
                ReviewRepository.reviews.stream().filter(r -> r.productId == productId)
                        .collect(Collectors.toList());
    }

    public Review moderate(@NotNull Review review) {
        //send the review to moderation
        //if this fails then do not save
        //if the moderation succeeds then make the review available for users
        review.reviewState = ReviewState.MODERATION_SUCCESS;
        return review;
    }

    public List<Review> getMyReviews(String userId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.userId == userId)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByFeature(long productId, String feature) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.productId == productId
                        && r.reviewState == ReviewState.MODERATION_SUCCESS
                        && getReviewsByFeature(r, feature))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByRating(long productId, int star) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.productId == productId
                        && r.reviewState == ReviewState.MODERATION_SUCCESS
                        && r.getRating() >= star)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByDateDesc(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.productId == productId
                        && r.reviewState == ReviewState.MODERATION_SUCCESS)
                .sorted(Comparator.comparing(Review::getReviewedDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByDate(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.productId == productId
                        && r.reviewState == ReviewState.MODERATION_SUCCESS)
                .sorted(Comparator.comparing(Review::getReviewedDate))
                .collect(Collectors.toList());
    }

    public List<Review> getCertifiedReviews(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.productId == productId
                && r.reviewState == ReviewState.MODERATION_SUCCESS
                && r.reviewType == CERTIFIED_BUYER).collect(Collectors.toList());
    }

    public void setModerationStateSuccess(long reviewId) {
        Review review = ReviewRepository.reviewMap.get(reviewId);
        if (review != null) {
            review.setReviewState(ReviewState.MODERATION_SUCCESS);
        }
    }

    public void setModerationStateFailed(long reviewId) {
        Review review = ReviewRepository.reviewMap.get(reviewId);
        if (review != null) {
            review.setReviewState(ReviewState.MODERATION_FAILED);
        }
        //notify the state and reason to user
        UserEmailNotifier userEmailNotifier = new UserEmailNotifierImpl();
        userEmailNotifier.notifyUser(new UserEmailNotification(userId,
                "review moderation failed",
                "review moderation status", Instant.now()));
    }

    private boolean getReviewsByFeature(@NotNull Review review, String feature) {
        List<Feature> features = review.getFeatures();
        features = features.parallelStream().filter(f -> f.getFeatureName().contains(feature))
                .collect(Collectors.toList());
        return features.size() > 0;
    }

    private void updateReviewType(Review review) {
        //fetch the orders by user
        //if the user has ordered the product then the review type is certified
        //else anonymous
        review.setReviewType(CERTIFIED_BUYER);
    }
}
