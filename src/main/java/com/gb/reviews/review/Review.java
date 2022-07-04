package com.gb.reviews.review;

import com.gb.reviews.repository.ReviewRepository;
import com.gb.reviews.user.User;
import com.gb.reviews.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
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
        this.location = user.getUserProfile().getUserLocation().getCity();
        this.features = features;
        reviewState = ReviewState.MODERATION_PENDING;
    }

    public Review addReview(Review review) {
        ReviewRepository.reviews.add(review);
        ReviewRepository.reviewMap.put(review.getProductId(), review);
        moderate(review);
        return review;
    }

    public List<Review> getReviewsByProduct(long productId) {
        return
                ReviewRepository.reviews.stream().filter(r -> r.productId == productId)
                        .collect(Collectors.toList());
    }

    public Review moderate(Review review) {
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
                        && getReviewsByFeature(r, feature))
                .collect(Collectors.toList());
    }

    private boolean getReviewsByFeature(Review review, String feature) {
        List<Feature> features = review.getFeatures();
        features = features.parallelStream().filter(f -> f.getFeatureName().contains(feature))
                .collect(Collectors.toList());
        return features.size() > 0;
    }
}
