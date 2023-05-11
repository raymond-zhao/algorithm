# 滴滴网约车一面
## 介绍项目
## Redis怎么用的

## 场景题：通过 Redis 存一个配置信息，每日提现金额上限，现在有一亿 QPS 提现请求，怎么解决（本地缓存），
1. 可以选择 string 类型
```json
{
  "daily_withdraw_limit": 1000
}
```
处理一亿 QPS 的提现请求是属于高负载、高并发问题，需要使用多种技术和架构方案来实现。这是典型的高并发设计题，谈到高并发设计有句俗话：“高并发有三宝，缓存、异步、队排好。”所以，我们可以首先围绕这三个技术点加以探讨。
1. 从架构角度，采用分布式架构，将请求负载均衡到多台机器上，实现高可用与水平扩展。
2. 从数据库角度，设计可扩展的主从架构，以便于水平和垂直扩展，另外就是使用数据库分库分表技术，分担单表压力，并且设计合理的数据库表结构与索引，加快查询处理速度。
3. 从缓存角度，使用 Redis 或者 Memcached 缓存，设计可扩展、高可用的 Redis 集群架构，请求时首先从缓存取数据，减少数据库服务器的负载与 TPS。
4. 从异步角度，使用消息队列缓存请求，使用多个消费者异步处理，提高系统吞吐量与性能。
5. 从队列角度，使用如 Kafka 或 RocketMQ 这样支持高并发的消息队列，并且保持请求的有序性与最终一致性。
6. 从代码角度，优化使用到的数据结构与算法，并且使用并发编程、异步I/O，内存池、本地缓存等，并且尽量减少 CPU 和内存的使用。
7. 总之，设计超高并发的系统需要考虑的内容有很多，我们还是需要具体问题具体分析，持续优化与调整，以保证服务的高可用与高性能。
## 更新后怎么刷新本地缓存
采用写后刷新，
## 本地缓存的工具类有哪些？
- HashMap/ConcurrentHashMap 等常见的内存数据结构均可以做本地缓存，但效果有优劣之分。
- Guava Cache：
  - 基于LRU
  - 驱逐策略：by Size、by Weight、by Time
  - Weak Keys, Soft Values
  - 处理 null 值
  - 刷新方式：手动刷新、自动刷新
  - 预加载
  - RemovalNotification
  - 
- Caffeine
- Ehcache
## 分布式事务原理
## 2PC和3PC区别？
## 要你设计一个3PC你要考虑什么问题。
## 慢 SQL 排查过程，表 (a,b,c,d)，索引（c,a,d），where c=1 and a > 1 and d = 1，where c=1 and a > 1 and d = 1 and d = 2
## 查询优化器的原理
## 算法：手写LRU
# 滴滴网约车二面
## 编程：一个文件，每行格式为"地点 打车人数"，比如"快手 500"，文件很大，有50G。内存限制在128M。输入一个地点，如何快速找出地址对应的打车人数？
对于这个问题，由于内存限制非常小，因此不能直接将整个文件加载到内存中。下面介绍两种解决方案：

使用数据库
可以使用一些流行的关系型数据库，如 MySQL、PostgreSQL 等来处理这个问题。将文件中的每一行作为数据库中的一条记录，其中地点作为一个字段，打车人数作为另一个字段。

对于查询，可以使用 SQL 语句来快速找出指定地点对应的打车人数。由于关系型数据库通常具有优秀的索引和查询优化技术，因此可以快速地处理大量数据，并在较短时间内返回结果。

使用外部排序算法
外部排序算法是一种用于排序和处理大量数据的算法，其基本思想是将大文件分成若干个小文件，然后对这些小文件进行排序，并将它们合并成一个有序的文件。

在这个问题中，可以将原始文件分成若干个小文件，每个小文件包含一定数量的记录。然后，对于每个小文件，可以使用快速排序或归并排序等算法进行排序。

当所有小文件都排序完成后，可以使用归并排序等算法将它们合并成一个有序的文件。在这个过程中，可以将同一地点的记录合并到一起，以便查询时更加高效。

最后，对于查询，可以使用二分查找等算法在有序的文件中快速找出指定地点对应的打车人数。
```java
import java.io.*;
import java.util.*;

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
```


