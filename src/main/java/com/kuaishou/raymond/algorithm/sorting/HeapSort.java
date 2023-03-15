package com.kuaishou.raymond.algorithm.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeapSort {

    public static void main(String[] args) {
        // int[] nums1 = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        // int[] nums2 = {1, 2, 3, 5, 2, 1, 5, 15, 193, 9};
        int[] nums3 = {15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1};
        int[] array = nums3;
        log.info("递归最大堆 = {}", buildMaxHeap(array));
        // log.info("最小堆 = {}", buildMinHeap(array));
        // log.info("迭代最大堆 = {}", buildMaxHeapIteratively(array));
        // log.info("最大堆排序结果 = {}", heapSort(array));
        // log.info("提取最大堆最大值 = {}", heapExtractMax(array, array.length));
        // log.info("提取最大堆后的数组 = {}", array);
        // log.info("增加优先队列某个值 = {}", heapIncreaseKey(array, 9, 100));
        log.info("增加最大堆 Key = {}", maxHeapInsert(array, 10));
    }

    /**
     * 向堆中新增一个元素
     * 首先增加一个 key=-\infty 的叶子结点来扩展最大堆，
     * 然后调用 heapIncreaseKey 将这个新增的叶子结点增加到 key。
     * 用数组的话，可能需要复制一遍数组，以存储新的叶子结点，
     * 如果是 list 的话，则可以省略这个复制。
     */
    public static int[] maxHeapInsert(int[] array, int key) {
        int[] newHeap = new int[array.length + 1];
        System.arraycopy(array, 0, newHeap, 0, array.length);
        newHeap[newHeap.length - 1] = Integer.MIN_VALUE;
        return heapIncreaseKeyInsertion(newHeap, newHeap.length - 1, key);
    }

    public static int[] maxHeapDelete(int[] array, int idx, int heapSize) {
        if (array[idx] > array[heapSize]) {
            array[idx] = array[heapSize];
            maxHeapify(array, idx, heapSize);
        } else {
            heapIncreaseKey(array, idx, array[heapSize]);
        }
        heapSize--;
        return array;
    }

    /**
     * 将堆中位置 i 处的值增加到 k
     */
    public static int[] heapIncreaseKey(int[] array, int i, int key) {
        if (key <= array[i]) {
            throw new UnsupportedOperationException("New key must greater than current key.");
        }

        // 把 i 位置的数值增加到 key，但这时可能会违反最大堆性质，应该获取其父节点进行调整。
        array[i] = key;
        while (i > 0 && array[parent(i)] < array[i]) {
            swap(array, i, parent(i)); // 需要三次变量复制
            i = parent(i);
        }

        return array;
    }

    public static int[] heapIncreaseKeyInsertion(int[] array, int i, int key) {
        if (key <= array[i]) {
            throw new UnsupportedOperationException("New key must greater than current key.");
        }

        // 利用插入排序的思想，向前寻找。
        while (i > 0 && array[parent(i)] < key) {
            array[i] = array[parent(i)]; // array[j + 1] = array[j]
            i = parent(i); // j--
        }
        array[i] = key;

        return array;
    }

    public static int heapExtractMax(int[] array, int heapSize) {
        if (heapSize < 0) {
            throw new UnsupportedOperationException("heap size underflow.");
        }

        int max = heapMaximum(array); // 提取最大堆根节点
        array[0] = array[heapSize - 1]; // 令根节点等于最后一个叶子结点，此时将很可能会违反最大堆性质。
        heapSize = heapSize - 1; // 因为摘除了根节点，整个堆的长度 - 1
        maxHeapify(array, 0, heapSize); // 从根节点到倒数第二个结点进行调整，维护最大堆性质。

        return max;
    }

    /**
     * 获取大根堆的最大值
     */
    public static int heapMaximum(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return Integer.MIN_VALUE;
        }
        return array[0];
    }

    public static int[] heapSort(int[] array) {
        buildMaxHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            maxHeapify(array, 0, i);
        }
        return array;
    }

    public static int[] buildMaxHeap(int[] array) {
        // 不需要对所有的结点最大堆化，只需要从最后一个非叶子结点开始即可。
        for (int i = (array.length >>> 1); i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
        return array;
    }

    public static int[] buildMaxHeapIteratively(int[] array) {
        for (int i = (array.length) >>> 1; i >= 0; i--) {
            maxHeapifyIteratively(array, i);
        }
        return array;
    }

    public static int[] buildMinHeap(int[] array) {
        for (int i = (array.length) >>> 1; i >= 0; i--) {
            minHeapify(array, i);
        }
        return array;
    }

    public static int[] maxHeapify(int[] array, int idx, int heapSize) {
        int l = left(idx);
        int r = right(idx);
        int largest = idx;

        if (l < heapSize && array[l] > array[idx]) {
            largest = l;
        }

        if (r < heapSize && array[r] > array[largest]) {
            largest = r;
        }

        if (largest != idx) {
            swap(array, largest, idx);
            maxHeapify(array, largest, heapSize);
        }

        return array;
    }

    public static int[] minHeapify(int[] array, int idx) {
        int l = left(idx);
        int r = right(idx);
        int smallest = idx;

        if (l < array.length && array[l] < array[idx]) {
            smallest = l;
        }

        if (r < array.length && array[r] < array[smallest]) {
            smallest = r;
        }

        if (smallest != idx) {
            swap(array, smallest, idx);
            minHeapify(array, smallest);
        }

        return array;
    }

    public static int[] maxHeapifyIteratively(int[] array, int idx) {
        while (true) {
            int l = left(idx);
            int r = right(idx);
            int largest = idx;

            if (l < array.length && array[l] > array[idx]) {
                largest = l;
            }

            if (r < array.length && array[r] > array[largest]) {
                largest = r;
            }

            if (largest == idx) { // 找到最大平凡堆
                break;
            }

            swap(array, idx, largest);
            idx = largest;
        }
        return array;
    }

    public static int parent(int i) {
        return (i - 1) >>> 1;
    }

    public static int left(int i) {
        return (i << 1) + 1;
    }

    public static int right(int i) {
        return (i << 1) + 2;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 合并 K 个有序链表/数组
    public static List<Integer> mergeK(List<List<Integer>> arrays) {
        return new ArrayList<>();
    }
}
