package com.gb.reviews.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    public UserProfile findByUserId(String userId) {
        if (this.userProfile == null)
            this.userProfile = new UserProfile(userId);
        this.userProfile = userProfile.getByUserId(userId);
        return this.userProfile;
    }
}
