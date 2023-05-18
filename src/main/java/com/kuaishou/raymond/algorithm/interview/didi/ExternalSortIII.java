package com.kuaishou.raymond.algorithm.interview.didi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/12 08:22
 * 题目：
 */
public class ExternalSortIII {

    private static final int MAX_CHUNK_SIZE = 128 * 1024 * 1024; // 128MB
    private static final int DEFAULT_CHUNK_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_THREADS = 16; // 最大线程数

    public static void sort(String inputFile, String outputFile) throws IOException {
        // 将大文件切割成小文件
        List<String> chunkFiles = splitFile(inputFile, DEFAULT_CHUNK_SIZE);

        // 对每个小文件进行排序
        List<String> sortedChunkFiles = sortChunks(chunkFiles);

        // 将所有小文件归并成一个大文件
        mergeSortedChunks(sortedChunkFiles, outputFile);

        // 删除中间文件
        for (String chunkFile : chunkFiles) {
            new File(chunkFile).delete();
        }
    }

    private static List<String> sortChunks(List<String> chunkFiles) {
        return null;
    }

    private static void mergeSortedChunks(List<String> sortedChunkFiles, String outputFile) {

    }

    private static List<String> splitFile(String inputFile, int chunkSize) throws IOException {
        List<String> chunkFiles = new ArrayList<>();
        int chunkCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            List<String> lines = new ArrayList<>();
            long totalSize = 0;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                totalSize += line.length();

                if (totalSize >= chunkSize) {
                    chunkCount++;
                    String chunkFile = String.format("%s.%04d", inputFile, chunkCount);
                    chunkFiles.add(chunkFile);

                    try (PrintWriter writer = new PrintWriter(new FileWriter(chunkFile))) {
                        for (String chunkLine : sortAndFlush(lines)) {
                            writer.println(chunkLine);
                        }
                    }

                    lines.clear();
                    totalSize = 0;
                }
            }

            if (!lines.isEmpty()) {
                chunkCount++;

            }
        }
        return null;
    }

    private static String[] sortAndFlush(List<String> lines) {
        return new String[0];
    }
}
