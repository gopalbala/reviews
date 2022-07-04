package com.gb.reviews.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
@AllArgsConstructor
public class ReviewMeta {
    private BufferedImage reviewImages;
    private String imagePath;
}
