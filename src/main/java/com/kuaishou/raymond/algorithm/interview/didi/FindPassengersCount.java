package com.kuaishou.raymond.algorithm.interview.didi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: raymond
 * CreateTime: 2023/5/12 08:14
 * 题目：从文件流中获取某个地点的打车人数
 */
public class FindPassengersCount {

    public static int findPassengersCountByLocation(File sortedFile, String location) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sortedFile))) {
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(location)) {
                    counter += Integer.parseInt(parts[1]);
                }
            }
            return counter;
        }
    }

    public static Map<String, Integer> readPassengersCountFile(String filename) throws IOException {
        Map<String, Integer> passengersCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String location = parts[0];
                int count = Integer.parseInt(parts[1]);
                passengersCount.put(location, passengersCount.getOrDefault(location, 0) + count);
            }
        }
        return passengersCount;
    }

    public static int findPassengersCountByLocation(String filename, String location) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals(location)) {
                    count += Integer.parseInt(parts[1]);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        String filename = "taxi_data.txt";
        Map<String, Integer> passengersCount = readPassengersCountFile(filename);

        String location = "快手";
        int count = passengersCount.getOrDefault(location, 0);
        System.out.println(location + " 打车人数：" + count);

        int passengersCountByLocation = findPassengersCountByLocation(filename, location);
        System.out.println("passengersCountByLocation = " + passengersCountByLocation);

        location = "滴滴";
        count = passengersCount.getOrDefault(location, 0);
        System.out.println(location + " 打车人数：" + count);
    }
}
