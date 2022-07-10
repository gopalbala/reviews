package com.gb.reviews.summary;

import lombok.Getter;

@Getter
public class Summary {
    private long productId;
    private int totalRatings;
    private float averageRating;
    private int totalOneStars;
    private int totalTwoStars;
    private int totalThreeStars;
    private int totalFourStars;
    private int totalFiveStars;

    public void incrementStar(int star) {
        if (star <= 0 || star >= 5) {
            return;
        }
        totalRatings++;
        switch (star) {
            case 2:
                totalTwoStars++;
                break;
            case 3:
                totalThreeStars++;
                break;
            case 4:
                totalFourStars++;
                break;
            case 5:
                totalFiveStars++;
                break;
            default:
                totalOneStars++;
        }
    }
}
