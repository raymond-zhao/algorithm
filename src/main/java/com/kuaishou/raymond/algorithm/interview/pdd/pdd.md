# 笔试

1. 求一组整数数组中的最长连续递增子序列的长度。
2. 给定两个字符串，求它们的最长公共子序列。
3. 给定一个长度为n的数组，找到数组中的两个数，使它们的和为目标值target。要求时间复杂度为O(n)。
4. 给定一个数组，找到所有出现次数大于n/2的元素。
5. 给定一个二叉树，找到从根节点到叶子节点的所有路径，使得路径上节点的和等于给定的目标值。
6. 给定一个字符串，找到最长的回文子串。
7. 给定一个矩阵，找到其中的一个最长递增子序列。这个子序列可以从任何一个元素开始，并且每个元素只能向右或向下移动。

# 面试

> 请给我一些拼多多Java工程师面试中出现的笔试题

1. 删除有序数组中的重复项

```java
public class Solution {
    /**
     * 快慢指针
     * 慢指针：指向归位后的数组中不重复字符第一次出现的位置
     * 快指针：寻找某个数字第一次出现的位置，如果遇到相同的则跳过。
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int length = nums.length;
        while (fast < length) {
            if (nums[fast] != nums[slow]) {
                // 此时，fast 与 slow 指向的字符不同
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
```

2. 最长公共前缀：给定一组字符串，找出它们的最长公共前缀。

```java
public class Solution {
    public String longestCommonPrefix(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        String prefix = words[0];
        for (String word : words) {
            // 依次取当前字符串与当前单词的最长前缀
            prefix = longestCommonPrefix(prefix, word);
        }
        return prefix;
    }

    /**
     * 求两个字符串的最长公共前缀
     */
    private String longestCommonPrefix(String prefix, String word) {
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < prefix.length() && idx2 < word.length()
                && prefix.charAt(idx1) == word.charAt(idx2)) {
            idx1++;
            idx2++;
        }
        return prefix.substring(0, Math.min(idx1, idx2));
    }
}
```

3. 反转链表：给定一个链表，将其反转。
```java
public class Solution {
    /**
     * 1->2->3->null
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode current = head;
        
        while (head != null) {
            head = head.next;
            current.next = prev;
            prev = current;
            current = head;
        }
        
        return prev;
    }
}
```

4. 快速排序：给定一个数组，使用快速排序算法对其进行排序。
```java
public class Solution {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }
    
    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partition = lomutoPartition(nums, left, right);
            quickSort(nums, left, partition - 1);
            quickSort(nums, partition + 1, right);
        }
    }

    /**
     * 《算法导论》与《编程珠玑》中用于快排划分的算法
     * Nico Lomuto
     * 效率略低于 Hoare Partition
     */
    private int lomutoPartition(int[] nums, int left, int right) {
        int key = nums[right];
        int idx = left - 1;
        
        for (int i = left; i < right; i++) {
            if (nums[i] <= key) {
                // 保证 [left, idx] 之间的值均小于等于 key 
                // [idx + 1, i] 之间的值均大于 key
                // [i + 1, right - 1] 未知
                idx++;
                swap(nums, idx, i);
            }
        }
        
        swap(nums, idx + 1, right);
        
        return idx + 1;
    }

    /**
     * 《算法导论》中提到的最原始的快排划分方式，
     * 也是国内大部分人知道和使用的方式
     * Author: Tony Hoare, 1959.
     */
    private int hoarePartition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int l = left;
        int r = right;
        
        while (l < r) {
            while (l < r &&  nums[l] < pivot) {
                l++;
            }
            while (l <  r && nums[r] >= pivot) {
                r--;
            }
            // 此时 nums[l] >= pivot，nums[r] < pivot，交换。
            swap(nums, l, r);
        }
        // 此时 l == r，这个位置是 pivot 在最终排序数组中的位置，交换 pivot 与 nums[l]。
        swap(nums, l, right);
        
        return l;
    }
    
    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
```

5. 二分查找：给定一个有序数组和一个目标值，使用二分查找算法查找目标值在数组中的位置。
```java
public class Solution {
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        return -1;
    }
}
```

6. **❤️字符串匹配**：给定一个文本串和一个模式串，查找模式串在文本串中的位置。
```java
public class Solution {
    /**
     * 暴力解法：O(mn)
     */
    public int searchPattern(String word, String pattern) {
        if (word == null || word.length() == 0 || pattern == null
                || pattern.length() == 0 || word.length() < pattern.length()) {
            return -1;
        }
        int m = word.length();
        int n = pattern.length();
        int endIndex = m - n;
        for (int i = 0; i < endIndex; i++) {
            if (word.charAt(i) == pattern.charAt(0)) {
                int index = searchPattern(word, pattern, endIndex, i);
                if (index != -1) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    private int searchPattern(String word, String pattern, int endIndex, int beginIndex) {
        int idx = beginIndex;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != word.charAt(idx)) {
                return -1;
            } else {
                idx++;
            }
        }
        return beginIndex;
    }
}
```

7. 斐波那契数列：计算斐波那契数列的第 n 项。
```java
public class Solution {
    /**
     * 斐波那契数列：0，1，1，2，3，5，8
     */
    public long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        
        int nMinus2 = 0;
        int nMinus1 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = cur;
        }
        return nMinus1;
    }
}
```

8. **❤️最长回文子串**：给定一个字符串，找出其中的最长回文子串。

```java
public class Solution {

    /**
     * 动态规划法
     * T(n) = O(n^2)
     */
    public String longestPalindromeDynamicProgramming(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        int len = str.length();
        // 状态定义：dp[i][j] 表示子串 str[i..j] 是否为回文串
        boolean[][] dp = new boolean[len][len];
        // 基准条件：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        
        char[] chars = str.toCharArray();
        int beginIndex = 0;
        int palindromeLength = 0;
        
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // s[i] == s[j]
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > palindromeLength) {
                    beginIndex = i;
                    palindromeLength = j - i + 1;
                }
            }
        }
        
        return str.substring(beginIndex, beginIndex + palindromeLength);
    }
    
    /**
     * T(n) = O(n^3)
     * 暴力法：枚举所有的字符串，记录最长的回文子串。
     */
    public String longestPalindromeBrutalForce(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] chars = str.toCharArray();
        int maxLength = 1;
        int beginIndex = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (j - i + 1 > maxLength && isPalindrome(chars, i, j)) {
                    maxLength = j - i + 1;
                    beginIndex = i;
                }
            }
        }
        
        return str.substring(beginIndex, beginIndex + maxLength);
    }

    /**
     * 中心扩散
     */
    public String longestPalindromeCenterSpread(String str) {
        // TODO
    }

    /**
     * 判断是否是回文串
     */
    private boolean isPalindrome(char[] charArray, int i, int j) {
        while (i < j) {
            if (charArray[i] != charArray[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
```

9. 单例模式：实现单例模式。
```java
/**
 * 饿汉式：在类加载时就完成实例化，线程安全。
 */
public class Singleton {
    
    private static final Singleton INSTANCE = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        return INSTANCE;
    }
    
}

/**
 * 懒汉式：使用时才创建，非线程安全。
 */
class Singleton {
    
    private static final Singleton INSTANCE = null;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
    
}

/**
 * 懒汉式：双端检锁，线程安全。
 */
class Singleton {

    /**
     * volatile 作用：创建对象不是原子操作，需要禁止指令重排。
     */
    private static volatile Singleton INSTANCE;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}

/**
 * 《Effective Java》作者推荐的单例实现方式
 * 线程安全，天生支持序列化，还可防止反序列化时新建对象。
 */
enum Singleton {
    INSTANCE,
    ;
    
    public void getInstance() {
        // TODO
    }
}
```

10. **💚哈希表**：实现一个哈希表，并实现其中的put、get、remove等操作。
```java
import java.util.ArrayList;

public class MyHashMap<K, V> {

    private ArrayList<Entry<K, V>> bucketArray;
    private int numBuckets;
    private int size;

    public MyHashMap() {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;

        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % numBuckets;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);
        Entry<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        size--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            bucketArray.set(bucketIndex, head.next);
        }

        return head.value;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = bucketArray.get(bucketIndex);
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        bucketArray.set(bucketIndex, newEntry);

        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<Entry<K, V>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;

            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }

            for (Entry<K, V> entry : temp) {
                while (entry != null) {
                    put(entry.key, entry.value);
                    entry = entry.next;
                }
            }
        }
    }

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

```

11. 给定一个数组，找到其中两个数的和等于给定的目标值。要求时间复杂度为 O(n)。
```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);

        for (int idx = 1; idx < nums.length; idx++) {
            int residual = target - nums[idx];
            if (map.containsKey(residual)) {
                return new int[] {map.get(residual), idx};
            }
            map.put(nums[idx], idx);
        }
        return new int[0];
    }
}
```

12. 给定一个链表，将链表从中间断开成两个部分，如果链表长度为奇数，则第一个部分长度要比第二个部分长一个节点。

```java

public class Solution {
    /**
     * 1. 遍历链表，统计结点个数 n。
     * 2. 再遍历一次，
     *  2.1 当 n 为奇数时，走到 n/2 + 1 时停止。
     *  2.2 当 n 为偶数时，走到 n/2 时停止。
     * 3. 先保存中点的下一个结点，再把中点的下一个结点置为 null。
     */
    public ListNode[] splitList(ListNode head) {
        if (head == null) {
            return new ListNode[0];
        }
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count++;
            node = node.next;
        }
        int middle = count % 2 == 0 ? n / 2 : n / 2 + 1;

        node = head;

        for (int i = 0; i < middle; i++) {
            node = node.next;
        }

        ListNode secondList = node.next;
        node.next = null;

        return new ListNode[] {head, secondList};
    }

    /**
     * 快慢指针
     * 1->2->3->4->5->null
     * 1->2->3->4->null
     * 1. slow 指针
     */
    public ListNode[] splitListInHalf(ListNode head) {
        if (head == null) {
            return new ListNode[0];
        }
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        fast = slow.next;
        slow.next = null;
        
        return new ListNode[] {slow, fast};
    }
}
```

13. 给定一个字符串，判断它是否是一个回文字符串。回文字符串是指从左往右和从右往左读都是一样的字符串。
```java
public class Solution {
    public boolean isPalindrome(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

14. **❤️LRU 缓存**实现一个 LRU 缓存（Least Recently Used），支持 put(key, value) 和 get(key) 操作。如果缓存已满，当再次添加新的键值对时，应该淘汰最近最少使用的键值对时间复杂度应该为 O(1)。

```java
/**
 * 
 * @param <K> key
 * @param <V> value
 */
public class P146LRU<K, V> {

    /**
     * 存储实际数据的缓存
     * K: key
     * V: 双向链表
     */
    private final Map<K, DLinkedNode<K, V>> cache = new HashMap<>();

    /**
     * LRU 缓存中实际存储的元素数量
     */
    private int size;

    /**
     * LRU 缓存的最大存储容量，如果超过最大容量，则要淘汰链表尾部数据。
     */
    private final int capacity;

    private final DLinkedNode<K, V> head;

    private final DLinkedNode<K, V> tail;

    public P146LRU(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode<>();
        tail = new DLinkedNode<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 从 LRU 缓存中获取数据
     */
    public V get(K key) {
        DLinkedNode<K, V> node = cache.get(key);
        if (node == null) {
            throw new NullPointerException();
        }
        // 把这个结点移动到链表头部
        moveToHead(node);
        return node.value;
    }

    /**
     * 向 LRU 缓存中添加数据
     * 首先判断要插入的数据是否已经存在，
     * - 如果存在，则更新该 Node 的值，并将其移动到链表头部；
     * - 如果不存在，则将其直接插入到链表头部。
     */
    public void put(K key, V value) {
        if (capacity == 0) {
            throw new UnsupportedOperationException("This LRUCache has no capacity. Cannot put any value.");
        }
        DLinkedNode<K, V> node = cache.get(key);
        if (node == null) {
            DLinkedNode<K, V> newNode = new DLinkedNode<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                // 已达到最大容量，删除链表最后一个结点，同时清除缓存中的数据。
                DLinkedNode<K, V> last = removeLast();
                cache.remove(last.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 删除链表尾部数据结点
     * y <-> x <-> tail
     * y <-> tail
     */
    private DLinkedNode<K, V> removeLast() {
        DLinkedNode<K, V> last = tail.prev;
        removeNode(last);
        return last;
    }

    /**
     * 将 node 移动到链表头部
     * 1. 先在链表中删除当前结点；
     * 2. 再把当前结点添加到链表头部。
     * head <-> tail
     * head <-> node <->tail
     */
    private void moveToHead(DLinkedNode<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 从链表中删除键结点
     * x <-> node <-> y
     * x <-> y
     */
    private void removeNode(DLinkedNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将结点添加到链表头部
     */
    private void addToHead(DLinkedNode<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private static class DLinkedNode<K, V> {

        private K key;

        private V value;

        private DLinkedNode<K, V> prev;

        private DLinkedNode<K, V> next;

        public DLinkedNode() {
            // 无参构造器
        }

        public DLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
```

15. 给定一个矩阵，其中每个元素表示一个数字。从矩阵的左上角出发，每次可以向右或者向下走一步，最终到达矩阵的右下角。找到一条路径，使得经过的数字之和最小。
```java
public class SearchMinPath {

    /**
     * 未进行空间压缩的原始解法
     * 可以在原矩阵上修改，将空间压缩到 O(1)。
     */
    public int searchMinPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("The matrix to be searched is not valid.");
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        // 状态定义：dp[i][j] 表示到达 (i, j) 时的最小数字之和
        int[][] dp = new int[m][n];
        
        // 基准条件：初始化行列
        dp[0][0] = matrix[0][0];
        for (int row = 1; row < m; row++) {
            dp[row][0] = matrix[row][0] + dp[row - 1][0];   
        }
        
        for (int col = 1; col < n; col++) {
            dp[0][col] = matrix[0][col] + dp[0][col - 1];    
        }
        
        // 状态转移方程：dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j]
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[row][col];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}
```

16. 给定一个数组，找到其中第 k 大的数。
```java
public class KthLargestElementUsingTimSort {
    
    public int kthLargestElementUsingTimSort(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(nums);
        return nums[k - 1];
    }
    
    /**
     * 快速排序算法中，每一轮 partition 操作可以将一个元素基准元素摆放到最终排序后的数组中的正确位置
     * 所以，我们可以修改快速排序算法的 partition 操作，当 partition = k-1 时就跳出循环。
     */
    public int kthLargestElement(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            throw new IllegalArgumentException();
        }
        return findKthLargestElement(nums, 0, nums.length - 1, k);
    }
    
    private int findKthLargestElement(int[] nums, int left, int right, int k) {
        if (left < right) {
            int partition = partition(nums, left, right);
            if (partition == k - 1) {
                return nums[partition];
            }
            findKthLargestElement(nums, left, partition - 1, k);
            findKthLargestElement(nums, partition + 1, right, k);
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int l = left;
        int r = right;
        int pivot = nums[right];
        
        while (l < r) {
            while (l < r && nums[l] < pivot) {
                l++;
            }
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            swap(nums, l, r);
        }
        
        swap(nums, l, right);
        
        return l;
    }
    
    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
```

17. **💚二叉树遍历**实现一个二叉树的先序、中序、后序遍历，以及按层遍历。

```java
import java.util.ArrayDeque;

/**
 * 递归的实现比较简单，就不再赘述了，这里均使用迭代的方式实现。
 */
public class TraverseTree {
    
    private void print(TreeNode node) {
        System.out.print("node.val = " + node.val);
    }

    /**
     * 先序遍历：根-左-右
     * 
     */
    public void preOrderTraverse(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new ArrayDeque<>();
        
        while (node != null || !stack.isEmpty()) {
            // 访问并存储当前节点，指针指向左子节点。
            // 存储当前节点是因为后续需要出栈，再访问其右节点。
            while (node != null) {
                print(node);
                stack.push(node);
                node = node.left;
            }
            // 访问右节点
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历：左-根-右
     */
    public void inOrderTraverse(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new ArrayDeque<>();
        
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                // 添加左节点
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                // 访问根节点
                node = stack.pop();
                print(node);
                // 加入右节点
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历：左-右-根
     */
    public void postOrderTraverse(TreeNode root) {
        
    }

    public void levelOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            print(node);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
```

18. **💚生产者-消费者**实现一个生产者-消费者（Producer-Consumer）模型，其中生产者线程生产随机数并将其放入一个共享的缓冲区，而消费者线程从缓冲区中获取随机数并将其打印出来。
```java
/**
 * 生产者-消费者模式中的缓冲队列
 * 这里提供了一种基于 Object 对象的 Wait-Notify 模式的视线，
 * 另外也可以基于 J.U.C 中的 Lock 或阻塞队列 BlockingQueue 实现。
 */
public class ProducerConsumerQueue<E> {

    /**
     * 队列最大容量
     */
    private static final int MAX_SIZE = 4;

    /**
     * 存放实际元素的队列
     */
    private final Queue<E> queue;

    public ProducerConsumerQueue() {
        queue = new ArrayDeque<>();
    }

    /**
     * 向队列中添加元素
     */
    public synchronized boolean offer(E e) throws InterruptedException {
        // 如果队列已满，需要阻塞生产者继续向其中添加元素。
        while (queue.size() == MAX_SIZE) {
            this.wait();
        }
        // 如果队列未满，生产者向其中添加元素。
        queue.offer(e);
        System.out.println(Thread.currentThread().getName() + " is producing element = [" + e + "], current size = " + queue.size());
        // 通知其他线程
        this.notifyAll();

        return true;
    }

    /**
     * 从队列中取元素
     */
    public synchronized E poll() throws InterruptedException {
        // 如果队列为空，需要阻塞消费者消费数据。
        while (queue.isEmpty()) {
            this.wait();
        }
        // 取数据
        E e = queue.poll();
        System.out.println(Thread.currentThread().getName() + " is consuming element [" + e + "]");
        // 通知其他线程
        this.notifyAll();

        return e;
    }
}

/**
 * 测试主程序
 */
public class ProducerConsumerApplication {

    private static final ExecutorService EXECUTORS = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        ProducerConsumerQueue<Integer> queue = new ProducerConsumerQueue<>();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        for (int i = 0; i < 2; i++) {
            // 2 个生产者线程
            EXECUTORS.submit(producer);
        }

        for (int i = 0; i < 2; i++) {
            // 2 个消费者线程
            EXECUTORS.submit(consumer);
        }

        EXECUTORS.shutdown();
    }

}

class Producer implements Runnable {

    private final ProducerConsumerQueue<Integer> queue;

    public Producer(ProducerConsumerQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                queue.offer(random.nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {

    private final ProducerConsumerQueue<Integer> queue;

    public Consumer(ProducerConsumerQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

19. 实现一个字符串反转函数，不可以直接使用库函数。

```java
class ReverseString {

    /**
     * 反转所有字符
     */
    public void reverseString344(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            swap(s, l, r);
            l++;
            r--;
        }
    }

    /**
     * 每 k 个一组反转字符
     */
    public String reverseStr541(String s, int k) {
        char[] chars = s.toCharArray();
        for (int start = 0; start < s.length(); start += 2 * k) {
            int l = start;
            int r = Math.min(start + k - 1, s.length() - 1);
            while (l < r) {
                // 反转从 start ~ start + k - 1 之间的字符
                swap(chars, l++, r--);
            }
        }
        return new String(chars);
    }

    private void swap(char[] chars, int l, int r) {
        char c = chars[l];
        chars[l] = chars[r];
        chars[r] = c;
    }
}
```
24. 反转字符串中的单词。编写一个函数，将一个字符串反转，并返回反转后的字符串。例如，输入 "hello world"，输出 "dlrow olleh"。
```java
/**
 * LeetCode：151，186，557
 * 《剑指 Offer》 58-1
 */
public class ReverseWords {

    public String reverseWords151(String s) {

    }
}
```

20. 实现一个正则表达式匹配函数，判断一个字符串是否符合给定的正则表达式。

21. 写一个函数，接受一个字符串参数，返回该字符串中第一个不重复的字符。例如，对于字符串"hello"，函数应该返回'e'。

23. 写一个函数，接受一个整数参数n，返回一个n行杨辉三角的列表。例如，当n=5时，函数应该返回以下列表：

    [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]

25. 给定一个二叉树和一个整数，找出从根节点到叶子节点的所有路径中，和等于该整数的路径。

26. 写一个函数，接受一个整数数组作为输入，返回一个新的数组，其中每个元素是原数组中除了它自己以外所有元素的乘积。

27. 写一个函数来判断两个给定的字符串是否是同构的（同构的定义为两个字符串中的每个字符都可以替换成另一个字符，使得两个字符串相同）。

28. 给定两个字符串，写一个函数来确定其中一个字符串的字符重新排列后是否可以形成另一个字符串。

29. 数组中重复的数字：在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

30. 单词搜索：给定一个二维网格和一个单词，找出该单词是否存在于网格中。

31. 用递归实现一个斐波那契数列生成函数

32. 实现一个简单的线程池

33. 实现一个简单的 RPC 框架

34. 实现一个简单的 HTTP 服务器

35. 实现一个归并排序算法

36. 判断一个数是否为质数

37. 实现一个线程安全的计数器

38. 实现一个字符串匹配算法

39. 实现一个堆排序算法

40. 实现一个二叉搜索树

41. 实现一个求最大公约数算法

42. 实现一个求最小公倍数算法

43. 实现一个约瑟夫环问题的解法

44. 实现一个大数相加算法

45. 实现一个大数相减算法

46. 实现一个大数相乘算法

47. 实现一个大数相除算法

48. 实现一个拓扑排序算法

49. 实现一个广度优先搜索算法

50. 实现一个深度优先搜索算法

