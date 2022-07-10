package com.gb.reviews.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLocation {
    private String userId;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String pin;
}
