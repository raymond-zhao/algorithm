package com.kuaishou.raymond.algorithm.file.v1;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-29 17:46
 */
@Slf4j
public class FileDownloader {

    private static final int CHUNK_SIZE = 4096; // 分片大小

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

        InputStream inputStream = response.body().byteStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        try (FileOutputStream fileOutputStream = new FileOutputStream(destinationPath)) {
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            long totalBytesRead = 0;

            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                log.info("Downloaded {} bytes", totalBytesRead);
            }

            fileOutputStream.flush();
            bufferedInputStream.close();
            response.close();
            log.info("File downloaded successfully.");
        }
    }

    public static void main(String[] args) {
        String url = "https://p1-yx.adkwai.com/udata/pkg/DSP-EFFECT/apk/59e82c3f-6184-449e-9462-c2753cb38da4.apk";
        String destinationPath = "./59e82c3f-6184-449e-9462-c2753cb38da4.apk";

        try {
            downloadFile(url, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
