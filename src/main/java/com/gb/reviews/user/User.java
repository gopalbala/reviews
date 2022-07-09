package com.gb.reviews.user;

import com.gb.reviews.repository.ReviewRepository;
import com.gb.reviews.repository.UserRepository;
import com.gb.reviews.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String userNickName;
    private UserProfile userProfile;

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String userNickName) {
        this.userId = userId;
        this.userNickName = userNickName;
    }

    public User findByUserId(String userId) {
        return UserRepository.usersMap.get(userId);
    }

    public User saveUser(User user) {
        UserRepository.usersMap.put(user.userId, user);
        initiateVerification(user);
        return user;
    }

    public void initiateVerification(User user) {
        //initiate verification process;
        //update the verification info for the user object
        setVerificationStatus(userId, VerificationStatus.VERIFIED);
    }

    public void setVerificationStatus(String userId, VerificationStatus verificationStatus) {
        findByUserId(userId).getUserProfile().setVerificationStatus(verificationStatus);
    }

    public List<Review> getMyReviews(String userId) {
        return ReviewRepository.reviews.parallelStream().filter(r -> r.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
