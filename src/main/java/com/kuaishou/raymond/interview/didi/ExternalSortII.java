package com.kuaishou.raymond.interview.didi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Author: raymond
 * CreateTime: 2023/5/12 07:43
 */
public class ExternalSortII {

    /**
     * 块文件的大小
     */
    private static final int DEFAULT_CHUNK_SIZE = 1024 * 1024;

    /**
     * @param sortedFile 已排序好的文件
     * @param location 要获取的地址
     * @return Location 位置的数量
     * @throws IOException 异常签名
     */
    public static int findPassengersCountByLocation(File sortedFile, String location) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sortedFile))) {
            // 行字符串变量
            String line;
            int passengers = 0;
            // 按行读取排序后的文件，并统计当前 location 的数量。
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(location)) {
                    passengers += Integer.parseInt(parts[1]);
                }
            }
            return passengers;
        }
    }

    /**
     * 外部排序主方法
     * @param inputFile 输入文件
     * @param outputFile 排序后的输出文件
     * @param comparator 排序比较器
     * @throws IOException 异常签名
     */
    public static void sort(File inputFile, File outputFile, Comparator<String> comparator) throws IOException {
        // 以 try-with-resources 的方式读取输入文件，并指定字符编码。
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8))) {
            // 分割文件，并按照排序器排序结果。
            int chunkCount = splitAndSort(reader, comparator);
            // 合并排序后的块文件
            mergeSortedChunks(outputFile, comparator, chunkCount);
        }
    }

    /**
     * @param reader 文件读取器
     * @param comparator 排序比较规则
     * @return 块文件编号
     * @throws IOException 异常签名
     */
    private static int splitAndSort(BufferedReader reader, Comparator<String> comparator) throws IOException {
        int chunkCount = 0;
        // 每个块文件存储的行记录数量
        String[] chunk = new String[DEFAULT_CHUNK_SIZE];
        while (true) {
            int chunkSize = 0;
            String line;
            while (chunkSize < DEFAULT_CHUNK_SIZE && (line = reader.readLine()) != null) {
                // 当块文件未达到默认行数，并且文件未读取完时，读取行并存储到块文件数组中。
                chunk[chunkSize++] = line;
            }
            if (chunkSize == 0) {
                // 如果文件已经读取完毕，则跳出循环。
                break;
            }
            // 块文件的个数
            chunkCount++;
            sortChunk(chunk, chunkSize, comparator, chunkCount);
        }
        return chunkCount;
    }

    /**
     * 对块文件进行内部排序，先生成要暂存数据的块文件，然后根据比较规则，将块文件中的数据 chunk 全部写入临时文件。
     * @param chunk 当前块文件中的数据，空间大小为 1024*1024 条。
     * @param size 块文件中实际存储的数据行
     * @param comparator 比较规则
     * @param chunkIndex 块文件编号
     * @throws IOException 异常签名
     */
    private static void sortChunk(String[] chunk, int size, Comparator<String> comparator, int chunkIndex) throws IOException {
        File chunkFile = new File("chunk_" + chunkIndex + ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(chunkFile.toPath())) {
            Stream.of(chunk).limit(size)
                    .sorted(comparator)
                    .forEachOrdered(line -> writeLine(writer, line));
        }
    }

    private static void mergeSortedChunks(File outputFile, Comparator<String> comparator, int chunkCount) throws IOException {
        PriorityQueue<IndexedBufferedReader> pq = new PriorityQueue<>(chunkCount, Comparator.comparing(IndexedBufferedReader::peekLine, comparator));
        for (int i = 1; i <= chunkCount; i++) {
            File chunkFile = new File("chunk_" + i + ".txt");
            BufferedReader chunkReader = new BufferedReader(new FileReader(chunkFile));
            pq.offer(new IndexedBufferedReader(chunkReader, i));
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath())) {
            while (!pq.isEmpty()) {
                IndexedBufferedReader ibReader = pq.poll();
                String line = ibReader.readLine();
                writeLine(writer, line);
                if (ibReader.peekLine() != null) {
                    pq.offer(ibReader);
                }
            }
        }
    }

    private static void writeLine(BufferedWriter writer, String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Write line error, line = " + line);
            throw new RuntimeException(e);
        }
    }

    private static class IndexedBufferedReader {
        private final BufferedReader reader;
        private final int index;
        private String line;

        public IndexedBufferedReader(BufferedReader reader, int index) {
            this.reader = reader;
            this.index = index;
            try {
                this.line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String readLine() {
            String currentLine = line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return currentLine;
        }

        public String peekLine() {
            return line;
        }

        public int getIndex() {
            return index;
        }
    }

}

