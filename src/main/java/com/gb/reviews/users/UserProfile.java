package com.gb.reviews.users;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfile {
    private String userId;
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
}
