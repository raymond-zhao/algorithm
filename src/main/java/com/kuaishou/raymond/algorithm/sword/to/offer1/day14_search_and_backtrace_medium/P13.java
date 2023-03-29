package com.kuaishou.raymond.algorithm.sword.to.offer1.day14_search_and_backtrace_medium;

/**
 * 机器人的运动范围
 * mxn 的矩阵，从坐标 (0, 0) 到 (m-1, n-1)。
 * 机器人从坐标 (0, 0) 开始移动，每次可以向左向右，向上向下移动一格。
 * 但是不能移动到矩阵之外，也不能移动到横竖坐标的数位之和大于 k 的方格。
 * 例如，当 k = 18 时，机器人可以进入 (35,37)，因为 3+5+3+7=18, 但不能进入 (35, 38).
 * 问：机器人能够达到多少个格子？
 * 1 <=m,n<=20
 * 0<=k<=20
 */
public class P13 {

    public static void main(String[] args) {
        P13 p13 = new P13();
        System.out.println("p13.movingCount(2, 3, 1) = " + p13.movingCount(2, 3, 1)); // 3
        System.out.println("p13.movingCount(3, 1, 0) = " + p13.movingCount(3, 1, 0)); // 1
    }

    private boolean[][] visited;

    private int m = 0;

    private int n = 0;

    private int k = 0;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }

    private int dfs(int i, int j, int digitSumOfI, int digitSumOfJ) {
        if (i >= m || i < 0 || j < 0 || j >= n || visited[i][j] || digitSumOfI + digitSumOfJ > k) {
            return 0;
        }

        visited[i][j] = true;
        int res = 1 + dfs(i + 1, j, sumOfDigit(i + 1), digitSumOfJ)
                + dfs(i - 1, j, sumOfDigit(i - 1), digitSumOfJ)
                + dfs(i, j + 1, digitSumOfI, sumOfDigit(j + 1))
                + dfs(i, j - 1, digitSumOfI, sumOfDigit(j - 1));
        return res;
    }

    public int movingCount2(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }

    private int dfs2(int i, int j, int s_i, int s_j) {
        if (i >= m || j >= n || s_i + s_j > k || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(i + 1, j, sumOfDigit(i, s_i), s_j) + dfs(i, j + 1, s_i, sumOfDigit(j, s_j));
    }

    private int sumOfDigit(int x, int s_x) {
        return (x + 1) % 10 != 0 ? s_x + 1 : s_x - 8;
    }

    private int sumOfDigit(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x = x / 10;
        }
        return sum;
    }
}
