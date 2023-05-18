package com.kuaishou.raymond.algorithm.interview.didi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExternalSortIV {

    private static final int DEFAULT_CHUNK_SIZE = 1024 * 1024; // 1M
    private static final String TMP_FILE_PREFIX = "sort_";
    private static final String TMP_FILE_SUFFIX = ".tmp";

    public static void sort(String inputFile, String outputFile) throws IOException {
        sort(inputFile, outputFile, DEFAULT_CHUNK_SIZE);
    }

    public static void sort(String inputFile, String outputFile, int chunkSize) throws IOException {
        List<String> chunkFilePaths = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine();
        int chunkIndex = 0;
        while (line != null) {
            List<String> chunkLines = new ArrayList<>(chunkSize);
            for (int i = 0; i < chunkSize && line != null; i++) {
                chunkLines.add(line);
                line = reader.readLine();
            }
            chunkFilePaths.add(sortAndSaveChunk(chunkLines, chunkIndex));
            chunkIndex++;
        }
        reader.close();

        mergeChunks(chunkFilePaths, outputFile);
    }

    private static String sortAndSaveChunk(List<String> lines, int chunkIndex) throws IOException {
        lines.sort(Comparator.comparing(s -> s.split("\\s+")[0]));
        String chunkFilePath = TMP_FILE_PREFIX + chunkIndex + TMP_FILE_SUFFIX;
        BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFilePath));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        return chunkFilePath;
    }

    private static void mergeChunks(List<String> chunkFilePaths, String outputFile) throws IOException {
        List<BufferedReader> readers = new ArrayList<>();
        for (String chunkFilePath : chunkFilePaths) {
            readers.add(new BufferedReader(new FileReader(chunkFilePath)));
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        List<String> lines = new ArrayList<>(chunkFilePaths.size());
        for (BufferedReader reader : readers) {
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
        }
        while (!lines.isEmpty()) {
            String minLine = Collections.min(lines, Comparator.comparing(s -> s.split("\\s+")[0]));
            writer.write(minLine);
            writer.newLine();
            int minLineIndex = lines.indexOf(minLine);
            String nextLine = readers.get(minLineIndex).readLine();
            if (nextLine == null) {
                lines.remove(minLineIndex);
                readers.get(minLineIndex).close();
                readers.remove(minLineIndex);
            } else {
                lines.set(minLineIndex, nextLine);
            }
        }
        writer.close();
        for (String chunkFilePath : chunkFilePaths) {
            Files.delete(Paths.get(chunkFilePath));
        }
    }
}

