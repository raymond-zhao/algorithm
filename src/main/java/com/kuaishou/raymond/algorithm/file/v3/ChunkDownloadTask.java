package com.kuaishou.raymond.algorithm.file.v3;

import java.io.IOException;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-30 06:57
 */
public class ChunkDownloadTask implements Runnable {

    /**
     * 分片大小
     */
    private static final int CHUNK_SIZE = 4096;

    private final OkHttpClient client;
    private final String url;
    private final long startRange;
    private final long endRange;
    private final RandomAccessFile file;

    public ChunkDownloadTask(OkHttpClient client, String url, long startRange, long endRange, RandomAccessFile file) {
        this.client = client;
        this.url = url;
        this.startRange = startRange;
        this.endRange = endRange;
        this.file = file;
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

            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            long position = startRange;

            while ((bytesRead = response.body().byteStream().read(buffer)) != -1) {
                file.seek(position);
                file.write(buffer, 0, bytesRead);
                position += bytesRead;
            }

            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
