package com.kuaishou.raymond.algorithm.interview.meituan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: raymond
 * CreateTime: 2023/6/26 13:47
 * 题目：Assume that there is a climber wants to go to place B from Place A and there are some mountains between place A and B.
 * To make the problem simpler, we assume each mountain is a rectangle,
 * which could be represented by a triple (2, 4, 5).
 * This triple means that the mountain begins from position 2 and ends at position 4 while its height is 5 consistently.
 * One step further, let's assume place A is at position 0 and all the mountains's begin positions are non-negative.
 * Place B locates at the mountain's end position which end position is the largest one.
 * The question is, if the climber starts from position A,
 * climbs up when a mountain is in front of him/her, and climbs down when he/she meets a cliff.
 * How much does this climber needs to walk?
 */
public class ClimbMountains {

    public static int calculateDistance(int[][] mountains) {
        // Sort mountains based on their starting positions
        Arrays.sort(mountains, Comparator.comparingInt(a -> a[0]));

        int currentPosition = 0;
        int totalDistance = 0;

        for (int[] mountain : mountains) {
            int startPosition = mountain[0];
            int endPosition = mountain[1];
            int height = mountain[2];

            // Walk to the starting position of the mountain
            totalDistance += startPosition - currentPosition;

            // Climb up the mountain
            totalDistance += height;

            // Walk across the mountain's width
            totalDistance += endPosition - startPosition;

            // Climb down the mountain
            totalDistance += height;

            // Update the current position
            currentPosition = endPosition;
        }

        // If there is remaining distance to place B, walk it
        if (currentPosition < mountains[mountains.length - 1][1]) {
            totalDistance += mountains[mountains.length - 1][1] - currentPosition;
        }

        return totalDistance;
    }

    public static void main(String[] args) {
        int[][] mountains = {{2, 4, 5}, {7, 9, 3}, {10, 12, 6}};
        int[][] mountains2 = {{1, 3, 2}, {2, 4, 4}, {6, 7, 5}};
        int distance = calculateDistance(mountains2);
        System.out.println(distance);
    }
}

