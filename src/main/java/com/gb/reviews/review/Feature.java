package com.gb.reviews.review;

import com.gb.reviews.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Feature {
    private String productId;
    private int id;
    private String featureName;
    private String title;
    private String text;
    private int rating;
    private List<Meta> metas;

    public Feature() {
        id = Utils.getRandomInt();
    }
}
