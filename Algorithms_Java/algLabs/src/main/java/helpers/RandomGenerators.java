package helpers;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerators {
    public static long getRandomInt(Integer leftBound, Integer rightBound){
        return ThreadLocalRandom.current().nextInt(leftBound, rightBound + 1);
    }
}
