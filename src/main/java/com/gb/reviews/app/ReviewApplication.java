package com.gb.reviews.app;

import com.gb.reviews.repository.ReviewRepository;
import com.gb.reviews.review.Feature;
import com.gb.reviews.review.Review;
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

        review.addReview(Utils.getRandomLong(),4,"great product",
                "attractive design,  awesome display, super camera, super design fabulous",
                null, user.getUserId(), features);

        ReviewRepository.reviews.add(review);
        ReviewRepository.reviewMap.put(review.getProductId(), review);
    }
}
