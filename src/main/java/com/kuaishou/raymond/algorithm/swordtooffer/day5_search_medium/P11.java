package com.kuaishou.raymond.algorithm.swordtooffer.day5_search_medium;

/**
 * 旋转数组中的最小数字
 */
public class P11 {

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 1, 1, 1, 2};
        P11 p11 = new P11();
        System.out.println("p11.minArray(nums) = " + p11.minArray2(nums));
        System.out.println("p11.minArray(nums) = " + p11.minArray(nums));
    }

    /**
     * 数组中的最小元素 min 将数组 nums 分成两个有序数组，假设数组长度为 m，min 下标为 x，
     * 本题可转化为求《求右排序数组的最小值》，nums[middle] 只跟 nums[right] 比较。
     * \Theta(lgn)，O(n)
     */
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (numbers[middle] > numbers[right]) {
                // middle 值大于 right 值时，middle 值肯定不是最小值，可以排除掉。
                left = middle + 1;
            } else if (numbers[middle] < numbers[right]) {
                // middle 值小于 right 值，不能执行 right = middle - 1 操作。
                // 因为 middle 值也可能是整个数组中的最小值，如果舍弃的话就再也找不到这个正确结果了。
                right = middle;
            } else {
                right--;
            }
        }
        return numbers[left];
    }

    private int minArray2(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            min = Math.min(min, numbers[i]);
        }
        return min;
    }

}
