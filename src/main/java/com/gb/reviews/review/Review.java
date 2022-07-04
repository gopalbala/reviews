package com.gb.reviews.review;

import com.gb.reviews.user.User;
import com.gb.reviews.user.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Review {
    private Long id;
    private String productId;
    private int rating;
    private String title;
    private String text;
    private String userId;
    private LocalDateTime reviewedDate;
    private String location;
    private List<ReviewMeta> reviewMetas;

    public void addReview(String productId, int rating, String title,
                          String text,List<ReviewMeta> reviewMetas, String userId) {
        if (rating <=0 || rating >=5)
            return;
        this.id = 1L;
        this.productId = productId;
        this.rating = rating;
        this.title = title;
        this.text = text;
        this.reviewMetas = reviewMetas;
        this.userId = userId;
        this.reviewedDate = LocalDateTime.now();
        User user = new User(userId);
        this.location = user.getUserProfile().getLocation();
    }
}
