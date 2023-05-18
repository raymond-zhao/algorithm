package com.kuaishou.raymond.algorithm.bigdata;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-05-16 21:28
 */
public class FindCommonURLs {

    public static void main(String[] args) {
        String fileA = "fileA.txt";
        String fileB = "fileB.txt";
        try {
            findCommonURLs(fileA, fileB);
        } catch (IOException e) {
            System.out.println("发生错误：" + e.getMessage());
        }
    }

    public static void findCommonURLs(String fileA, String fileB) throws IOException {
        // 预处理文件A，将URL分散到小文件中。
        Map<Integer, BufferedWriter> fileMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileA))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int fileIndex = hash(line) % NUM_FILES;
                try (BufferedWriter writer = fileMap.computeIfAbsent(fileIndex, FindCommonURLs::createTempFileWriter)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        // 对文件B进行匹配查找
        Set<String> commonURLs = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileB))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int fileIndex = hash(line) % NUM_FILES;
                try (BufferedReader fileReader = new BufferedReader(new FileReader(getTempFilePath(fileIndex)))) {
                    String url;
                    while ((url = fileReader.readLine()) != null) {
                        if (url.equals(line)) {
                            commonURLs.add(line);
                            break;
                        }
                    }
                }
            }
        }

        // 输出共同的URL
        for (String url : commonURLs) {
            System.out.println(url);
        }

        // 删除临时文件
        deleteTempFiles();
    }

    private static final int NUM_FILES = 1000;
    private static final String TMP_FILE_PREFIX = "temp_";
    private static final String TMP_FILE_SUFFIX = ".tmp";

    private static int hash(String url) {
        // 根据URL计算哈希值
        return url.hashCode();
    }

    @SneakyThrows
    private static BufferedWriter createTempFileWriter(int index) {
        // 创建临时文件并返回写入器
        String filePath = getTempFilePath(index);
        File file = new File(filePath);
        return new BufferedWriter(new FileWriter(file));
    }

    private static String getTempFilePath(int index) {
        // 获取临时文件路径
        return TMP_FILE_PREFIX + index + TMP_FILE_SUFFIX;
    }

    private static void deleteTempFiles() throws IOException {
        // 删除临时文件
        for (int i = 0; i < NUM_FILES; i++) {
            String filePath = getTempFilePath(i);
            File file = new File(filePath);
            Files.delete(file.toPath());
        }
    }
}

