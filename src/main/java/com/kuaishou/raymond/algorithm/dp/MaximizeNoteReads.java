package com.kuaishou.raymond.algorithm.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: raymond
 * @Datetime: 2023/9/15 14:46
 * Description:
 */
public class MaximizeNoteReads {
    public static List<NoteRead> findMaxNoteReads(List<NoteRead> noteReads, int totalAmount) {
        int n = noteReads.size();
        int[][] dp = new int[n + 1][totalAmount + 1];

        for (int i = 1; i <= n; i++) {
            NoteRead currentNoteRead = noteReads.get(i - 1);
            int noteReadValue = currentNoteRead.appendBudget;

            for (int j = 1; j <= totalAmount; j++) {
                if (noteReadValue <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - noteReadValue] + noteReadValue);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 从 dp 数组中构建最优解
        List<NoteRead> selectedNoteReads = Lists.newArrayList();
        int i = n;
        int j = totalAmount;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedNoteReads.add(noteReads.get(i - 1));
                j -= noteReads.get(i - 1).appendBudget;
            }
            i--;
        }

        return selectedNoteReads;
    }

    public static void main(String[] args) {
        List<NoteRead> noteReads = Lists.newArrayList(
                new NoteRead("65058e690000000001029485", 22500, System.currentTimeMillis()),
                new NoteRead("65058f040000000001029486", 22500, System.currentTimeMillis()),
                new NoteRead("65058f450000000001029487", 2300, System.currentTimeMillis()),
                new NoteRead("65058f8f0000000000004cf6", 2300, System.currentTimeMillis()),
                new NoteRead("65058fcb0000000000004cf7", 2300, System.currentTimeMillis()),
                new NoteRead("650590090000000000004cf8", 2300, System.currentTimeMillis()),
                new NoteRead("650590580000000001029488", 2300, System.currentTimeMillis())
        );

        int totalAmount = 75000;

        List<NoteRead> selectedNoteReads = findMaxNoteReadsV4(noteReads, totalAmount);

        System.out.println("最大价值的 NoteRead 组合：");
        for (NoteRead noteRead : selectedNoteReads) {
            System.out.println("NoteId: " + noteRead.noteId + ", Reads: " + noteRead.appendBudget);
        }
    }

    @Data
    @AllArgsConstructor
    public static class NoteRead {

        private String noteId;

        private int appendBudget;

        private long createTime;
    }

    public static List<NoteRead> findMaxNoteReadsV2(List<NoteRead> noteReads, int totalAmount) {
        // 按照 appendBudget 降序排序
        noteReads.sort(Comparator.comparingInt((NoteRead nr) -> nr.appendBudget).reversed());

        List<NoteRead> selectedNoteReads = new ArrayList<>();
        int remainingAmount = totalAmount;

        for (NoteRead noteRead : noteReads) {
            if (noteRead.appendBudget <= remainingAmount) {
                selectedNoteReads.add(noteRead);
                remainingAmount -= noteRead.appendBudget;
            }
        }

        return selectedNoteReads;
    }

    public static List<NoteRead> findMaxNoteReadsV4(List<NoteRead> noteReads, int totalAmount) {
        int n = noteReads.size();
        int[][] dp = new int[n + 1][totalAmount + 1];

        int maxAmount = 0;
        for (int i = 1; i <= n; i++) {
            NoteRead currentNoteRead = noteReads.get(i - 1);
            int noteReadValue = currentNoteRead.appendBudget;

            for (int j = 1; j <= totalAmount; j++) {
                if (noteReadValue <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - noteReadValue] + noteReadValue);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                maxAmount = Math.max(maxAmount, dp[i][j]);
            }
        }

        // 从 dp 数组中构建最优解
        List<NoteRead> selectedNoteReads = new ArrayList<>();
        int i = n;
        int j = totalAmount;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedNoteReads.add(noteReads.get(i - 1));
                j -= noteReads.get(i - 1).appendBudget;
            }
            i--;
        }

        // 恢复与输入数据的相对顺序一致
        selectedNoteReads.sort(Comparator.comparing(NoteRead::getCreateTime));
        System.out.println("maxAmount = " + maxAmount);
        return selectedNoteReads;
    }
}
