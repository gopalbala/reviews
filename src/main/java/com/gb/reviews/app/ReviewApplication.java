package com.gb.reviews.app;

import com.gb.reviews.product.Product;
import com.gb.reviews.repository.ProductRepository;
import com.gb.reviews.repository.ReviewRepository;
import com.gb.reviews.review.Feature;
import com.gb.reviews.review.Review;
import com.gb.reviews.review.ReviewType;
import com.gb.reviews.user.User;
import com.gb.reviews.user.UserLocation;
import com.gb.reviews.user.UserProfile;
import com.gb.reviews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReviewApplication {
    public static void main(String[] args) {
        User user = new User("user1@abc.com", "user1");
        UserProfile userProfile = new UserProfile(user.getUserId());
        userProfile.setFirstName("user");
        userProfile.setLastName("1");
        userProfile.setUserName("user1");
        userProfile.setSecondaryEmailId("user1alt@abc.com");
        userProfile.setPassword("encrypted");
        userProfile.setPhoneNumber("8349545162");
        UserLocation userLocation = new UserLocation();
        userLocation.setAddress1("1, ORR");
        userLocation.setCity("Bangalore");
        userLocation.setPin("560100");
        userProfile.setUserLocation(userLocation);
        user.setUserProfile(userProfile);
        user.saveUser(user);

        Review review = new Review();

        List<Feature> features = new ArrayList<>();
        Feature feature = new Feature();
        feature.setFeatureName("camera");
        feature.setTitle("excellent camera");
        feature.setText("Camera quality is great (as I bought it for only 14000) it is way better.");
        feature.setRating(4);
        features.add(feature);

        feature = new Feature();
        feature.setFeatureName("speakers");
        feature.setTitle("audio is good");
        feature.setText("Songs are good to listen from this phone. Phone audio quality is good");
        feature.setRating(4);

        long product1 = Utils.getRandomLong();

        Product product = new Product(product1,"sku1","OnePlus 9R");
        product.addProduct();

        review.addReview(product1, 4, "great product",
                "attractive design,  awesome display, super camera, super design fabulous",
                null, user.getUserId(), features);


        review.setModerationStateSuccess(review.getId());
        review.setReviewType(ReviewType.CERTIFIED_BUYER);
        review.addReview(review);


        user = new User("user2@abc.com", "user1");
        userProfile = new UserProfile(user.getUserId());
        userProfile.setFirstName("user");
        userProfile.setLastName("2");
        userProfile.setUserName("user2");
        userProfile.setSecondaryEmailId("user2alt@abc.com");
        userProfile.setPassword("encrypted");
        userProfile.setPhoneNumber("9349545162");
        userLocation = new UserLocation();
        userLocation.setAddress1("1, ORR");
        userLocation.setCity("Delhi");
        userLocation.setPin("100100");
        userProfile.setUserLocation(userLocation);
        user.setUserProfile(userProfile);
        user.saveUser(user);

        features = new ArrayList<>();
        feature = new Feature();
        feature.setFeatureName("camera");
        feature.setTitle("excellent camera");
        feature.setText("Able to take pictures in low light.");
        feature.setRating(4);
        features.add(feature);

        review.addReview(product1, 3, "value for money",
                "attractive color choices, good product at this price",
                null, user.getUserId(), features);

        List<Review> reviews = product.getReviewsByFeature(product1, "camera");
        for (Review r : reviews) {
            System.out.println(r.getText());
        }

        reviews = product.getCertifiedReviews(product1);
        for (Review r : reviews) {
            System.out.println(r.getText());
        }

        review.setModerationStateSuccess(review.getId());
        review.setReviewType(ReviewType.CERTIFIED_BUYER);

        reviews = product.getReviewsWithMeta(product1);
        assert reviews.size() == 0;
    }
}
