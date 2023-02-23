package com.kuaishou.raymond.algorithm.swordtooffer.day30_divide_and_conquer_hard;

/**
 * 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * ---
 * 0 <= 数组长度 <= 50000，也就是说不能使用 O(n^2) 及以上复杂度的算法。
 */
public class P51 {

    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4}; // 5
        P51 p51 = new P51();
        System.out.println("p51.reversePairs(nums) = " + p51.reversePairs(nums));
        System.out.println("p51.reversePairsV2(nums) = " + p51.reversePairsV2(nums));
    }

    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private int count;

    public int reversePairsV2(int[] nums) {
        this.count = 0;
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) >>> 1;
        mergeSort(nums, left, middle); // 分左
        mergeSort(nums, middle + 1, right); // 分右
        mergeAndCount(nums, left, middle, right); // 合
    }

    private void mergeAndCount(int[] nums, int left, int middle, int right) {
        // 定义一个临时数组
        int[] tempArr = new int[right - left + 1];
        int leftIdx = left; // 指向第一个数组的第一个元素
        int rightIdx = middle + 1; // 指向第二个数组的第一个元素
        int idx = 0; // 指向临时数组的第一个元素
        while (leftIdx <= middle && rightIdx <= right) {
            // 比较两个数组的首元素，将较小的加入到临时数组，并移动其指针。
            if (nums[leftIdx] <= nums[rightIdx]) {
                tempArr[idx++] = nums[leftIdx++];
            } else {
                // 出现了逆序对，左子数组首元素 > 右子数组首元素，
                // 因为左子数组已有序，则左子数组之后的所有元素也大于右子数组首元素。
                count = count + (middle - leftIdx + 1);
                tempArr[idx++] = nums[rightIdx++];
            }
        }
        // 执行完上面的循环后，leftIdx > middle || rightIdx > right，
        // 表示要么左子数组全被合入临时数组，要么右子数组全被合入临时数组，
        // 需要合并未被全部归为的子数组中剩余的元素，保持这次合并后的数组仍然保序。
        while (leftIdx <= middle) {
            tempArr[idx++] = nums[leftIdx++];
        }
        while (rightIdx <= right) {
            tempArr[idx++] = nums[rightIdx++];
        }
        // 使用临时数组中的元素，覆盖原数组中的元素。
        System.arraycopy(tempArr, 0, nums, left, tempArr.length);
    }
}
