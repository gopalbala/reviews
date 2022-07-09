package com.gb.reviews.product;

import com.gb.reviews.repository.ProductRepository;
import com.gb.reviews.repository.ReviewRepository;
import com.gb.reviews.review.Feature;
import com.gb.reviews.review.Review;
import com.gb.reviews.review.ReviewState;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.gb.reviews.review.ReviewType.CERTIFIED_BUYER;

@Getter
@Setter
public class Product {
    private long productId;
    private String sku;
    private String productName;

    private List<Review> reviews;

    public Product() {}

    public Product(long productId, String sku, String productName) {
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        reviews = new ArrayList<>();
    }

    public Product addProduct() {
        ProductRepository.products.add(this);
        ProductRepository.productMap.put(productId, this);
        return this;
    }

    public List<Review> getReviewsByFeature(long productId, String feature) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getProductId() == productId
                        && r.getReviewState() == ReviewState.MODERATION_SUCCESS
                        && getReviewsByFeature(r, feature))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByDateDesc(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getProductId() == productId
                        && r.getReviewState() == ReviewState.MODERATION_SUCCESS)
                .sorted(Comparator.comparing(Review::getReviewedDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByProduct(long productId) {
        return
                ReviewRepository.reviews.stream().filter(r -> r.getProductId() == productId)
                        .collect(Collectors.toList());
    }

    public List<Review> getReviewsByDate(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getProductId() == productId
                        && r.getReviewState() == ReviewState.MODERATION_SUCCESS)
                .sorted(Comparator.comparing(Review::getReviewedDate))
                .collect(Collectors.toList());
    }

    public List<Review> getCertifiedReviews(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getProductId() == productId
                && r.getReviewState() == ReviewState.MODERATION_SUCCESS
                && r.getReviewType() == CERTIFIED_BUYER).collect(Collectors.toList());
    }

    public List<Review> getReviewsWithMeta(long productId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getProductId() == productId
                && r.getReviewState() == ReviewState.MODERATION_SUCCESS
                && isMetaPresent(r)).collect(Collectors.toList());
    }

    public List<Review> getReviewsByRating(long productId, int star) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getProductId() == productId
                        && r.getReviewState() == ReviewState.MODERATION_SUCCESS
                        && r.getRating() >= star)
                .collect(Collectors.toList());
    }

    private boolean getReviewsByFeature(@NotNull Review review, String feature) {
        List<Feature> features = review.getFeatures();
        features = features.parallelStream().filter(f -> f.getFeatureName().contains(feature))
                .collect(Collectors.toList());
        return features.size() > 0;
    }

    private boolean isMetaPresent(Review review) {
        if (review.getMetas() != null && review.getMetas().size() > 0)
            return true;
        for (Feature feature : review.features) {
            if (feature.getMetas() != null && feature.getMetas().size() > 0)
                return true;
        }
        return false;
    }
}
