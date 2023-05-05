package com.kuaishou.raymond.algorithm.leetcode.hot100.h17technique;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.swap;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 19:03
 * 题目：<a href="https://leetcode.cn/problems/find-the-duplicate-number/?envType=study-plan-v2&id=top-100-liked">287. 寻找重复数</a>
 * 解析：<a href="https://leetcode.cn/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/">...</a>
 * - 要求：不能修改数组，且只能使用 O(1) 的时间
 * 相似题目：
 * - {@link com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist.Hot141LinkedListCycle}
 * - <a href="https://leetcode.cn/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/">142. 环形链表 II</a>
 */
public class Hot287FindTheDumplicatedNumber {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,3,4,2,2]");
        System.out.println("findDuplicate(nums) = " + findDuplicate(nums));
    }

    /**
     * 含有 n+1 个数字的数组 nums 中的元素取值范围在 [1..n] 之间，
     * 而数组的取值范围刚好在 [0..n] 之间，所以，可以考虑将数组中的每个元素，
     * 放置到以其值为下标的位置之上，比如把数字 1 放到索引 1，把数字 2 放到索引 2...
     * 当重复的数字 num 试图归位时，发现如果当前索引上的元素 num 已经与其相等，则表示找到了重复数字。
     */
    public static int findDuplicate(int[] nums) {
        while (true) {
            // 如果以当前值为索引的位置上的值已经正确归位，则找到了重复数字。
            if (nums[nums[0]] == nums[0]) {
                return nums[0];
            }
            // 如果当前值未被正确放置到以其为下标的索引上，持续交换。
            swap(nums, 0, nums[0]);
        }
    }

    /**
     * 二分搜索：统计每个数字出现的次数
     * 题目保证了一定出现并只出现一个重复数字，假如取数组中点将数组分为前后两段，
     * 如 [1,2,2,3,4], 取中点 nums[2]=2 将数组划分成 [1,2,2] 与 [3,4] 两段，
     * - 在前半段统计 <= nums[2] 的数字的数量，即 1+1+1=3;
     * - 在后半段统计 > nums[2] 的数字的数量，即 1+1=2;
     * 而如果数字互不相同，如 [1,2,5,3,4]，以中点 nums[2] 按照相同方式统计，
     * - 在前半段统计 <= nums[2] 的数字的数量，即 1+1=2;
     * - 在后半段统计 > nums[2] 的数字的数量，即 1+1=2;
     * 也就是说，前后两段中出现数字的数量是相同的，但如果出现了重复数字，
     * 则出现重复数字的那段区间的数字 count 会多 1，就可以排除掉另一段区间的所有元素，实现"二分"效果。
     */
    public int findDuplicateIII(int[] nums) {
        // 在 1..n 之间进行二分搜索
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) >>> 1;
            int counter = 0;
            for (int num : nums) {
                if (num <= middle) {
                    counter++;
                }
            }

            if (counter > middle) {
                // 某个数字至少出现了两次，并且是出现在前半段区间。
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 龟兔赛跑/弗洛伊德判圈法
     * 本题最优解
     */
    public int findDuplicateII(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

}
