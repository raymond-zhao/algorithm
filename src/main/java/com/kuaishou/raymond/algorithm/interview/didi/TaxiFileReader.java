package com.kuaishou.raymond.algorithm.interview.didi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * AI: 对于这种大文件的场景，一般可以采用外部排序和分块的方式来进行处理，以保证内存使用量不超过限制。具体的解决方案可以如下：
 * 1. 分块：将50G的大文件分成小文件，每个小文件包含一定数量的行数据，使用缓存来减少磁盘IO次数，加快读取速度。每个小文件可以称为一个块。
 * 2. 排序：可以使用外部排序算法将每个块内的数据按地点进行排序，使得每个块内的数据都按地点有序排列。
 * 3. 归并：将所有块按照地点顺序合并成一个大文件，并使得文件内数据有序排列。这样在查询时只需在合并后的文件中找到对应地点的行数据，用二分查找等算法可以使查询速度更快。
 * 4. 查询：遍历文件，查找对应地点，并统计打车人数即可。
 * 由于内存限制在128M，因此在实现分块和排序时需要注意内存使用量，并使用高效的算法实现。
 */
public class TaxiFileReader {

    public static void main(String[] args) throws IOException {
        String filePath = "taxi_data.txt";
        long maxMemory = 128 * 1024 * 1024;  // 128M
        TaxiFileReader reader = new TaxiFileReader(filePath, maxMemory);
        String location = "快手";
        long taxiNum = reader.findTaxiNumByLocation(location);
        System.out.println(location + " 打车人数: " + taxiNum);
    }

    /**
     * 文件路径
     */
    private final String filePath;

    /**
     * 块文件大小
     */
    private final long blockSize;

    public TaxiFileReader(String filePath, long maxMemory) {
        this.filePath = filePath;
        // 每个块大小设为内存限制的一半
        this.blockSize = maxMemory >> 1;
    }

    /**
     * 查找对应地点的打车人数
     *
     * @param location 地点
     * @return 打车人数
     * @throws IOException 文件读取异常
     */
    public long findTaxiNumByLocation(String location) throws IOException {
        // 读取文件并进行归并排序
        List<File> sortedFileBlocks = sortAndSplitFile();
        // 在所有块中查找对应地点的行数据并统计打车人数
        long sum = 0;
        for (File block : sortedFileBlocks) {
            sum += findTaxiNumByLocationInBlock(block, location);
        }
        // 删除块文件
        for (File block : sortedFileBlocks) {
            Files.delete(block.toPath());
            System.out.println("File: " + block + " has been deleted.");
        }
        return sum;
    }

    /**
     * 将大文件分块并进行排序
     *
     * @return 分块后的文件列表
     * @throws IOException 文件读取异常
     */
    private List<File> sortAndSplitFile() throws IOException {
        // 读取文件，分块，并对每个块进行排序。
        List<File> fileBlocks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            // 用于存储排序后的行数据
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                // 如果行数据数量已达到块大小，则将块写入文件并清空行数据列表。
                if ((long) lines.size() * estimateLineSize(line) > blockSize) {
                    File sortedFileBlock = sortAndWriteBlock(lines);
                    fileBlocks.add(sortedFileBlock );
                }
            }
            // 处理剩余行数据
            if (!lines.isEmpty()) {
                File sortedFileBlock = sortAndWriteBlock(lines);
                fileBlocks.add(sortedFileBlock);
            }
        }
        return fileBlocks;
    }

    /**
     * 对行数据进行排序并写入文件
     *
     * @param lines 待排序的行数据
     * @return 排序后的块文件
     * @throws IOException 文件写入异常
     */
    private File sortAndWriteBlock(List<String> lines) throws IOException {
        // 对行数据按地点进行排序
        Collections.sort(lines);
        File block = File.createTempFile("block", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(block), StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        lines.clear();
        return block;
    }

    /**
     * 在块文件中查找对应地点的行数据并统计打车人数
     *
     * @param block 块文件
     * @param location 地点
     * @return 打车人数
     * @throws IOException 文件读取异常
     */
    private long findTaxiNumByLocationInBlock(File block, String location) throws IOException {
        long sum = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(block), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(location)) {  // 如果地点一致，则累加打车人数
                    sum += Long.parseLong(parts[1]);
                }
            }
        }
        return sum;
    }

    /**
     * 估计行数据大小
     *
     * @param line 行数据
     * @return 估计的大小
     */
    private int estimateLineSize(String line) {
        // 估计为每个字符占用1个字节，加上系统换行符的2个字节
        return line.length() + 2;
    }
}
