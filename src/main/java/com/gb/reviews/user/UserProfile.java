package com.gb.reviews.user;

import com.gb.reviews.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfile {
    private String userId;
    private String userProfileId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userName;
    private String emailId;
    private String password;
    private String phoneNumber;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedAt;

    public UserProfile(String userId) {
        this.userId = userId;
    }

    public UserProfile getByUserId(String userId) {
        if (UserRepository.usersMap.get(userId) != null)
         return UserRepository.usersMap.get(userId).getUserProfile();
        return null;
    }
}
