package com.gb.reviews.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feature {
    private String productId;
    private int featureId;
    private String featureName;
    private String title;
    private String text;
    private int rating;
}
