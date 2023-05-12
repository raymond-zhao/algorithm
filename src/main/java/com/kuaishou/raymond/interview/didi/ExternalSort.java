package com.kuaishou.raymond.interview.didi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: raymond
 * CreateTime: 2023/5/11 22:46
 */
public class ExternalSort {

    public static void main(String[] args) throws IOException {
        File sortedFile = new File("taxi_data_sorted.txt");
        String location = "快手";
        int passengersCount = findPassengersCountByLocation(sortedFile, location);
        System.out.println("地点 " + location + " 对应的打车人数为：" + passengersCount);
    }

    private static final int DEFAULT_CHUNK_SIZE = 2 * 1024 * 1024; // 默认数据块大小为 2MB

    public static void sort(File inputFile, File outputFile, int memoryLimit, int... chunkSize) throws IOException {
        int chunkLimit = chunkSize.length > 0 ? chunkSize[0] : DEFAULT_CHUNK_SIZE;
        List<File> chunkFiles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count == 0) {
                    File chunkFile = File.createTempFile("chunk", null);
                    chunkFiles.add(chunkFile);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFile))) {
                        writer.write(line);
                        writer.newLine();
                    }
                    count = chunkLimit;
                } else {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFiles.get(chunkFiles.size() - 1), true));
                    try {
                        writer.write(line);
                        writer.newLine();
                    } finally {
                        writer.close();
                    }
                    count -= line.length() + 1;
                    if (count < 0) {
                        chunkFiles.sort((f1, f2) -> {
                            try {
                                return getFirstLine(f1).compareTo(getFirstLine(f2));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        mergeChunks(chunkFiles, outputFile, memoryLimit);
                        count = chunkLimit;
                        chunkFiles.clear();
                        File chunkFile = File.createTempFile("chunk", null);
                        chunkFiles.add(chunkFile);
                        writer = new BufferedWriter(new FileWriter(chunkFile));
                        try {
                            writer.write(line);
                            writer.newLine();
                        } finally {
                            writer.close();
                        }
                    }
                }
            }
            if (!chunkFiles.isEmpty()) {
                chunkFiles.sort((f1, f2) -> {
                    try {
                        return getFirstLine(f1).compareTo(getFirstLine(f2));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                mergeChunks(chunkFiles, outputFile, memoryLimit);
            }
        } finally {
            for (File chunkFile : chunkFiles) {
                chunkFile.delete();
            }
        }
    }

    private static String getFirstLine(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        }
    }

    private static void mergeChunks(List<File> chunkFiles, File outputFile, int memoryLimit) throws IOException {
        PriorityQueue<BufferedReader> pq = new PriorityQueue<>(chunkFiles.size(), (o1, o2) -> {
            try {
                return o1.readLine().compareTo(o2.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        for (File chunkFile : chunkFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(chunkFile), memoryLimit);
            pq.add(reader);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = pq.poll().readLine()) != null) {
                writer.write(line);
                writer.newLine();
                BufferedReader reader = pq.peek();
                if (reader == null) {
                    break;
                }
                pq.poll();
                pq.add(reader);
            }
        }
    }

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
}
