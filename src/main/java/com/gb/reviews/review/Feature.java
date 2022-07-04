package com.gb.reviews.review;

import com.gb.reviews.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feature {
    private String productId;
    private int id;
    private String featureName;
    private String title;
    private String text;
    private int rating;

    public Feature() {
        id = Utils.getRandomInt();
    }
}
