package com.gb.reviews.app;

import com.gb.reviews.review.Review;
import com.gb.reviews.user.User;
import com.gb.reviews.user.UserLocation;
import com.gb.reviews.user.UserProfile;

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
    }
}
