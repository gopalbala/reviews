package com.gb.reviews.review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Review {
    private Long id;
    private String productId;
    private float rating;
    private String title;
    private String text;
    private String userId;
    private LocalDateTime reviewedDate;
    private String location;
}
