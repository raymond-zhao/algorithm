package com.kuaishou.raymond.interview.didi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-05-16 19:29
 */
public class TaxiCounter {

    public static void main(String[] args) throws IOException {
        String input = args[0];
        String output = args[1];
        String location = args[2];
        // 将50G的输入文件划分为多个小文件
        List<String> tempFiles = splitInputFile(input);

        // 对每个小文件排序
        for (String tempFile : tempFiles) {
            sortFile(tempFile);
        }

        // 将排序后的小文件合并成一个大文件
        mergeFiles(tempFiles, output);

        // 在合并后的大文件中查找指定地点的打车人数
        int count = findLocationCount(output, location);

        // 打印输出
        System.out.println("count = " + count);
    }


    private static int findLocationCount(String output, String location) {
        // - 根据排序后的输出文件
        return 0;
    }

    private static int binarySearch(List<LocationCount> list, String location) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int middle = (left + right) >>> 1;
            LocationCount locationCount = list.get(middle);
            int cmp = location.compareTo(locationCount.location);
            if (cmp == 0) {
                return Integer.parseInt(locationCount.count);
            } else if (cmp < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        // 没有找到指定地点
        return -1;
    }

    /**
     * 将分块文件中的数据写入到排序后的新文件中
     */
    private static void mergeFiles(List<String> filenames, String output) throws IOException {
        PriorityQueue<BufferedReader> queue = new PriorityQueue<>((reader1, reader2) -> {
            try {
                String first = reader1.readLine();
                String second = reader2.readLine();
                if (first == null && second == null) {
                    return 0;
                } else if (first == null) {
                    return 1;
                } else if (second == null) {
                    return -1;
                } else {
                    return first.split(" ")[0].compareTo(second.split(" ")[0]);
                }
            } catch (IOException exception) {
                throw new RuntimeException();
            }
        });

        // 将每个小文件的第一行读入内存，并将 BufferedReader 加入优先队列。
        for (String filename : filenames) {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            if (line != null) {
                queue.offer(reader);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            // 使用优先队列中的 BufferedReader 逐个读取行，并写入到输出文件中。
            while (!queue.isEmpty()) {
                // 取出当前读取最小值的 reader
                BufferedReader reader = queue.poll();
                // 读取当前行
                String line = reader.readLine();

                writer.write(line);
                writer.newLine();

                // 读取之后再添加回去
                if (line != null) {
                    queue.offer(reader);
                }
            }
        }

        // 关闭优先队列中所有的 BufferedReader
        for (String filename : filenames) {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.close();
        }
    }

    private static void sortFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        // 读取文件中的所有行
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        // 对所有行排序
        lines.sort(Comparator.naturalOrder());

        // 将排序后的行写入原文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * 1. 每次从 input 文件中读取 128MB 字节的数据（但未排序）
     * 2. 将临时文件加入结果集
     * @param input 输入文件名
     * @return 拆分后的临时文件
     * @throws IOException 异常签名
     */
    private static List<String> splitInputFile(String input) throws IOException {
        List<String> tempFiles = new ArrayList<>();

        int bufferSize = 128 * 1024 * 1024;
        byte[] buffer = new byte[bufferSize];
        int bytesRead;
        int fileNumber = 0;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input))) {
            // 从输入流中读取数据，并且写入 buffer 中，直到 buffer 写满。
            while ((bytesRead = bis.read(buffer)) != -1) {
                // 读满了数据，准备写入文件。
                String tempFilename = String.format("temp_%s.txt", fileNumber);
                // 将缓冲区数据写入临时文件
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFilename))) {
                    bos.write(buffer, 0, bytesRead);
                }
                tempFiles.add(tempFilename);
                fileNumber++;
            }
        }

        return tempFiles;
    }

    private static class LocationCount {

        private String location;

        private String count;

        public LocationCount(String line) {
            String[] data = line.split(" ");
            this.location = data[0];
            this.count = data[1];
        }
    }
}
