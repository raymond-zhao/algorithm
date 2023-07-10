package com.kuaishou.raymond.algorithm.file.v2;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-29 17:55
 */
public class DownloadTask implements Runnable {
    private final OkHttpClient client;
    private final String url;
    private final long startRange;
    private final long endRange;
    private final FileOutputStream fileOutputStream;
    private final AtomicLong totalBytesRead;

    public DownloadTask(OkHttpClient client, String url, long startRange, long endRange,
            FileOutputStream fileOutputStream, AtomicLong totalBytesRead) {
        this.client = client;
        this.url = url;
        this.startRange = startRange;
        this.endRange = endRange;
        this.fileOutputStream = fileOutputStream;
        this.totalBytesRead = totalBytesRead;
    }

    @Override
    public void run() {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .header("Range", "bytes=" + startRange + "-" + endRange)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Failed to download file: " + response);
            }

            InputStream inputStream = response.body().byteStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            byte[] buffer = new byte[4096];
            int bytesRead;
            long bytesReadTotal = 0;

            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                bytesReadTotal += bytesRead;
                totalBytesRead.addAndGet(bytesRead);
            }

            bufferedInputStream.close();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}