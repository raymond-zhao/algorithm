package com.kuaishou.raymond.interview.didi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: raymond
 * CreateTime: 2023/5/11 13:40
 * 题目：
 */
public class TaxiDataProcessor {
    // 定义一个结构体用于保存地点和打车人数
    static class LocationData {
        String location;
        int count;

        public LocationData(String location, int count) {
            this.location = location;
            this.count = count;
        }
    }

    // 外部排序算法的实现
    public static void externalSort(File inputFile, File outputFile, int chunkSize) throws IOException {
        List<File> chunkFiles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = null;

        // 逐行读取输入文件，并将记录写入一个临时文件中
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(" ");
            String location = fields[0];
            int count = Integer.parseInt(fields[1]);

            // 将地点和打车人数保存到结构体中
            LocationData data = new LocationData(location, count);

            // 将结构体写入一个临时文件中
            File chunkFile = writeChunk(data, chunkSize);
            chunkFiles.add(chunkFile);
        }

        // 关闭输入文件
        reader.close();

        // 将所有临时文件进行归并排序，并将结果写入输出文件中
        mergeSort(chunkFiles, outputFile);
    }

    // 将一个结构体写入一个临时文件中
    private static File writeChunk(LocationData data, int chunkSize) throws IOException {
        File chunkFile = File.createTempFile("chunk_", ".txt");
        chunkFile.deleteOnExit();
        PrintWriter writer = new PrintWriter(chunkFile);

        // 将结构体转换为字符串，并写入临时文件中
        writer.println(data.location + " " + data.count);

        // 关闭临时文件
        writer.close();

        return chunkFile;
    }

    // 对一组临时文件进行归并排序，并将结果写入输出文件中
    private static void mergeSort(List<File> chunkFiles, File outputFile) throws IOException {
        List<BufferedReader> readers = new ArrayList<>();
        PriorityQueue<LocationData> queue = new PriorityQueue<>((a, b) -> a.location.compareTo(b.location));

        // 打开所有临时文件的读取器，并将它们添加到一个列表中
        for (File chunkFile : chunkFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(chunkFile));
            readers.add(reader);
        }

        // 创建一个写入器，用于将结果写入输出文件中
        PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

        // 从每个临时文件中读取一行，并将它们添加到一个优先队列中
        for (BufferedReader reader : readers) {
            String line = reader.readLine();
            if (line != null) {
                String[] fields = line.split(" ");
                String location = fields[0];
                int count = Integer.parseInt(fields[1]);
                queue.add(new LocationData(location, count));
            }
        }

        // 取出队列中最小的元素，并将它写入输出文件中
        while (!queue.isEmpty()) {
            LocationData data = queue.poll();
            writer.println(data.location + " " + data.count);

            // 从对应的临时文件中读取下一行，并将它加入队列中
            for (int i = 0; i < readers.size(); i++) {
                BufferedReader reader = readers.get(i);
                if (data.location.equals(chunkFiles.get(i).getName())) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] fields = line.split(" ");
                        String location = fields[0];
                        int count = Integer.parseInt(fields[1]);
                        queue.add(new LocationData(location, count));
                    }
                }
            }
        }

        // 关闭所有临时文件的读取器
        for (BufferedReader reader : readers) {
            reader.close();
        }

        // 关闭输出文件的写入器
        writer.close();
    }

    // 从输出文件中查找指定地点的打车人数
    public static int findCount(File outputFile, String location) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));
        String line = null;

        // 逐行读取输出文件，并查找指定地点的打车人数
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(" ");
            if (fields[0].equals(location)) {
                reader.close();
                return Integer.parseInt(fields[1]);
            }
        }

        // 如果没有找到指定地点，则返回0
        reader.close();
        return 0;
    }

    // 测试代码
    public static void main(String[] args) throws IOException {
        File inputFile = new File("taxi_data.txt");
        File outputFile = new File("taxi_data_sorted.txt");
        externalSort(inputFile, outputFile, 1000000);
        int count = findCount(outputFile, "快手");
        System.out.println("快手的打车人数为：" + count);
    }
}
