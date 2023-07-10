package com.kuaishou.raymond.algorithm.file.v2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-29 17:56
 */
public class MultiThreadFileDownloader {

    private static final int CHUNK_SIZE = 4096; // 分片大小
    private static final int MAX_THREADS = 10; // 最大线程数

    public static void downloadFile(String url, String destinationPath) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed to download file: " + response);
        }

        long fileSize = response.body().contentLength();
        response.close();

        FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);
        AtomicLong totalBytesRead = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

        long rangeSize = fileSize / MAX_THREADS;
        // [startRange, endRange] 之间是一个线程负责下载的内容
        long startRange = 0;
        long endRange = rangeSize;

        for (int i = 0; i < MAX_THREADS - 1; i++) {
            executorService.execute(new DownloadTask(client, url, startRange, endRange,
                    fileOutputStream, totalBytesRead));
            // 下一个线程的下载起点
            startRange = endRange + 1;
            endRange += rangeSize;
        }

        // 最后一个分片的范围
        executorService.execute(new DownloadTask(client, url, startRange, fileSize - 1,
                fileOutputStream, totalBytesRead));

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fileOutputStream.close();

        System.out.println("File downloaded successfully.");
    }

    public static void main(String[] args) {
        String url = "https://p1-yx.adkwai.com/udata/pkg/DSP-EFFECT/apk/59e82c3f-6184-449e-9462-c2753cb38da4.apk";
        String destinationPath = "./59e82c3f-6184-449e-9462-c2753cb38da4.apk";

        try {
            long l = System.currentTimeMillis();
            downloadFile(url, destinationPath);
            long l1 = System.currentTimeMillis();
            System.out.println("(l1 - l) = " + (l1 - l));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
