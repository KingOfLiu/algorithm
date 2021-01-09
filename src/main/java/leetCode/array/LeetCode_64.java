package leetCode.array;

import common.ArrayUtil;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * */
public class LeetCode_64 {
    public int minPathSum(int[][] grid){
        if(grid.length == 0){
            return 0;
        }

        ArrayUtil.printArray(grid);
        System.out.println("#######################");

        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < dp.length; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for(int j = 1; j < dp[0].length; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for(int i = 1; i < dp.length; i++){
            int[] col = dp[i];
            for(int j = 1; j < col.length; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        ArrayUtil.printArray(dp);
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args){
        int[][] grid = {{1,3,1,5},{1,5,1,8},{4,2,1,9}};
        LeetCode_64 solution = new LeetCode_64();
        int ans = solution.minPathSum(grid);
        System.out.println(ans);
    }
}
