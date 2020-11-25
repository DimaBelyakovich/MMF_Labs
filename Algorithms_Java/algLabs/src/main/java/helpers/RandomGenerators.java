package helpers;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerators {
    public static long getRandomLong(Integer leftBound, Integer rightBound){
        return ThreadLocalRandom.current().nextInt(leftBound, rightBound + 1);
    }

    public static int getRandomInt(int leftBound, int rightBound){
        if(rightBound>leftBound){
            Random random = new Random();
            return random.nextInt((rightBound - leftBound) + 1)+leftBound;
        } else {
            throw new IllegalArgumentException("Right bound must be greater than left bound");
        }
    }
}
