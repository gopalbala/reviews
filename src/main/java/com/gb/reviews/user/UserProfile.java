package com.gb.reviews.user;

import com.gb.reviews.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
public class UserProfile {
    private String userId;
    private long userProfileId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userName;
    private String secondaryEmailId;
    private String password;
    private String phoneNumber;
    private UserLocation userLocation;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedAt;
    private VerificationStatus verificationStatus;

    public UserProfile(String userId) {
        this.userId = userId;
        if (getByUserId(userId) == null) {
            this.createdAt = LocalDateTime.now();
            this.userProfileId = new Random().nextLong();
            verificationStatus = VerificationStatus.NON_VERFIFIED;
        }
    }

    public UserProfile getByUserId(String userId) {
        if (UserRepository.usersMap.get(userId) != null)
            return UserRepository.usersMap.get(userId).getUserProfile();
        return null;
    }
}
