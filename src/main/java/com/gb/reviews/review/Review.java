package com.gb.reviews.review;

import com.gb.reviews.user.User;
import com.gb.reviews.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class Review {
    private long id;
    private long productId;
    private int rating;
    private String title;
    private String text;
    private String userId;
    private LocalDateTime reviewedDate;
    private String location;
    @Setter
    private List<Meta> metas;

    public List<Feature> features;

    public Review() {
        this.id = Utils.getRandomLong();
    }

    public void addReview(long productId, int rating, String title,
                          String text, List<Meta> metas, String userId,
                          List<Feature> features) {
        if (rating <= 0 || rating >= 5)
            return;
        this.id = Utils.getRandomLong();
        this.productId = productId;
        this.rating = rating;
        this.title = title;
        this.text = text;
        this.metas = metas;
        this.userId = userId;
        this.reviewedDate = LocalDateTime.now();
        User user = new User(userId);
        this.location = user.getUserProfile().getUserLocation().getCity();
        this.features = features;
    }
}
