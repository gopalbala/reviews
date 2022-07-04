package com.gb.reviews.utils;

import java.util.Random;

public class Utils {
    public static Random RANDOM = new Random();

    public static long getRandomLong() {
        return RANDOM.nextLong();
    }

    public static int getRandomInt() {
        return RANDOM.nextInt();
    }
}
