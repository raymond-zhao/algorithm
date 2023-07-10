package com.kuaishou.raymond.algorithm.file.v3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-30 06:48
 */
public class MultiThreadFileChunkDownloader {

    /**
     * 最大线程数
     */
    private static final int MAX_THREADS = 5;

    public static void downloadFile(String url, String destinationPath) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        long fileSize = 0;
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to download file: " + response);
            }
            if (response.body() != null) {
                fileSize = response.body().contentLength();
            }
        }

        try (RandomAccessFile file = new RandomAccessFile(destinationPath, "rw")) {
            file.setLength(fileSize);

            long rangeSize = fileSize / MAX_THREADS;
            long startRange = 0;
            long endRange = rangeSize;


            ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

            for (int i = 0; i < MAX_THREADS - 1; i++) {
                executorService.execute(new ChunkDownloadTask(client, url, startRange, endRange, file));
                startRange = endRange + 1;
                endRange += rangeSize;
            }

            // 最后一个分片的范围
            executorService.execute(new ChunkDownloadTask(client, url, startRange, fileSize - 1, file));

            executorService.shutdown();

            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("File downloaded successfully.");
        }
    }

    public static void main(String[] args) throws IOException {

        String url = "https://p1-yx.gskwai.com/udata/pkg/DSP-EFFECT/ad-app-center-file-template/AppCenterChannelIDAndCommentFile.xlsx";

        Path directories = Files.createDirectories(Paths.get("./2023-06-30-11/"));
        String filePath = directories + "/" + UUID.randomUUID().toString().replace("-", "") + ".xlsx";
        System.out.println("filePath = " + filePath);
        System.out.println("LocalDateTime.now().toString() = " + LocalDateTime.now().toString());
        try {
            long l = System.currentTimeMillis();
            downloadFile(url, filePath);
            long l1 = System.currentTimeMillis();
            System.out.println("(l1 - l) = " + (l1 - l));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
