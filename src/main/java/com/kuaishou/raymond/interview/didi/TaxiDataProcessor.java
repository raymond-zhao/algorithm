package com.kuaishou.raymond.interview.didi;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: raymond
 * CreateTime: 2023/5/11 13:40
 * 题目：一个文件，每行格式为"地点 打车人数"，比如"快手 500"，文件很大，有50G。内存限制在128M。输入一个地点，如何快速找出地址对应的打车人数？
 * 如果内存只有 128M，但是数据有 50G，我们需要采用外部排序（External Sorting）的算法来进行处理。
 * 外部排序算法的基本思路是将待排序的文件分成若干个能够存放进内存的小块（通常称为块或页），将每一块载入内存，进行内部排序，然后将每一块排好序的结果写回到磁盘上。最后再将排好序的小块逐一归并，得到最终的排序结果。
 * 在这个问题中，我们可以先将原始数据文件切割成若干个小的数据文件，每个小文件的大小不超过 128M。然后对于每个小文件，使用归并排序或者快速排序等内部排序算法进行排序。最后再将每个小文件合并成一个大文件，得到最终的排序结果。
 * 具体实现上，我们可以采用分治的思想，将原始数据文件划分成若干个小文件，并行地进行排序。这个过程可以使用多线程或者分布式处理来加速。最终得到排好序的小文件后，我们可以使用多路归并排序算法将它们归并成一个有序的大文件。在归并的过程中，我们可以使用优先队列（Priority Queue）来进行较为高效的合并。
 */
public class TaxiDataProcessor {

    /**
     * 测试代码
     */
    public static void main(String[] args) throws IOException {
        // 获取输入文件：排序前的文件
        File inputFile = new File("taxi_data.txt");
        // 指定输出文件：排序后的文件
        File outputFile = new File("taxi_data_sorted.txt");
        // 外部排序
        // externalSort(inputFile, outputFile, 1000000);
        // ExternalSort.sort(inputFile, outputFile,  128 * 1024 * 1024);
        ExternalSortII.sort(inputFile, outputFile, Comparator.naturalOrder());

        String location = "美团";
        // 查找打车人数
        // int count = findCount(outputFile, location);
        int count = ExternalSortII.findPassengersCountByLocation(outputFile, location);
        System.out.println(location + "的打车人数为：" + count);
    }

    /**
     * 从输出文件中查找指定地点的打车人数
     */
    public static int findCount(File outputFile, String location) throws IOException {
        // try-with-resources
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            // 逐行读取输出文件，并查找指定地点的打车人数。
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(" ");
                if (fields[0].equals(location)) {
                    return Integer.parseInt(fields[1]);
                }
            }
            // 如果没有找到指定地点，则返回0
            return 0;
        }
    }

    /**
     * 定义一个结构体用于保存地点和打车人数
     */
    private static class LocationData {

        private final String location;

        private final int count;

        public LocationData(String location, int count) {
            this.location = location;
            this.count = count;
        }
    }

    /**
     * 外部排序算法具体实现
     * @param inputFile 输入文件
     * @param outputFile 输出文件
     * @param chunkSize 分批文件大小
     * @throws IOException IO 读写异常
     */
    public static void externalSort(File inputFile, File outputFile, int chunkSize) throws IOException {
        List<File> chunkFiles = new ArrayList<>();
        // try-with-resource
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            // 逐行读取输入文件，并将记录写入一个临时文件中。
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
        }

        // 将所有临时文件进行归并排序，并将结果写入输出文件中
        mergeSort(chunkFiles, outputFile);
    }

    // 将一个结构体写入一个临时文件中
    private static File writeChunk(LocationData data, int chunkSize) throws IOException {
        File chunkFile = File.createTempFile("chunk_", ".txt");
        chunkFile.deleteOnExit();
        PrintWriter writer = new PrintWriter(chunkFile);

        // 将结构体转换为字符串，并写入临时文件中。
        writer.println(data.location + " " + data.count);

        // 关闭临时文件
        writer.close();

        return chunkFile;
    }

    /**
     * 对一组临时文件进行归并排序，并将结果写入输出文件中。
     */
    private static void mergeSort(List<File> chunkFiles, File outputFile) throws IOException {
        List<BufferedReader> readers = new ArrayList<>();

        // 打开所有临时文件的读取器，并将它们添加到一个列表中。
        for (File chunkFile : chunkFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(chunkFile));
            readers.add(reader);
        }

        // 小根堆
        PriorityQueue<LocationData> queue = new PriorityQueue<>(Comparator.comparing(a -> a.location));

        // 创建一个写入器，用于将结果写入输出文件中。
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
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

            // 取出队列中最小的元素（location），并将它写入输出文件中。
            while (!queue.isEmpty()) {
                LocationData data = queue.poll();
                writer.println(data.location + " " + data.count);

                // 从对应的临时文件中读取下一行，并将它加入队列中。
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
        }
    }
}