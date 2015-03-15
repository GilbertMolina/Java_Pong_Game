package com.gilberth.framework.util;

import java.util.Random;

/**
 *
 * @author Gilberth
 */
public class RandomNumberGenerator {
    
    private static Random rand = new Random();
    
    public static int getRandIntBetween(int lowerBound, int upperBound){
        return rand.nextInt(upperBound - lowerBound) + lowerBound;
    }
    
    public static int getRandInt(int upperBound){
        return rand.nextInt(upperBound);
    }
    
}
