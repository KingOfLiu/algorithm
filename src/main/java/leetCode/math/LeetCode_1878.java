package leetCode.math;

import common.ArrayUtil;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 1878. 矩阵中最大的三个菱形和
 * 给你一个 m x n 的整数矩阵 grid 。
 * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。本题中的菱形必须为正方形旋转45度，
 * 且四个角都在一个格子当中。下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标
 * 注在图中。
 * 注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
 * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们
 * 全部返回。
 *
 * 示例 1：
 * 输入：grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * 输出：[228,216,211]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：20 + 3 + 200 + 5 = 228
 * - 红色：200 + 2 + 10 + 4 = 216
 * - 绿色：5 + 200 + 4 + 2 = 211
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[20,9,8]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：4 + 2 + 6 + 8 = 20
 * - 红色：9 （右下角红色的面积为 0 的菱形）
 * - 绿色：8 （下方中央面积为 0 的菱形）
 *
 * 示例 3：
 * 输入：grid = [[7,7,7]]
 * 输出：[7]
 * 解释：所有三个可能的菱形和都相同，所以返回 [7] 。
 *
 *
 * 提示：
 *  - m == grid.length
 *  - n == grid[i].length
 *  - 1 <= m, n <= 100
 *  - 1 <= grid[i][j] <= 10^5
 * */
public class LeetCode_1878 {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ascSum = new int[m + 1][n + 1];
        int[][] descSum = new int[m + 2][n + 2];

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                ascSum[i][j] = ascSum[i - 1][j - 1] + grid[i - 1][j - 1];
                descSum[i][j] = descSum[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                set.add(grid[i - 1][j - 1]);
                for(int k = 1; i - k >= 1 && i + k <= m && j - k >= 1 && j + k <= n; k++){
                    int edge_i = (i - 1);
                    int edge_j = (j - 1);

                    int a = descSum[i][j - k] - descSum[i - k][j];
                    int c = descSum[i + k - 1][j + 1] - descSum[i - 1][j + k + 1];

                    int b = ascSum[i - 1][j + k - 1] - ascSum[i - k - 1][j - 1];
                    int d = ascSum[i + k][j] - ascSum[i][j - k];
                    set.add(a + b + c + d);
                }

                while(set.size() > 3){
                    set.pollFirst();
                }
            }
        }

        int size = set.size();
        int[] ans = new int[size];
        int idx = size - 1;
        while(!set.isEmpty()){
            ans[idx--] = set.pollFirst();
        }
        return ans;
    }

    public static void main(String[] args){
        int[][] grid = {{3,4,5,1,3},{3,3,4,2,3},{20,30,200,40,10},{1,5,5,4,1},{4,3,2,2,5}};
        LeetCode_1878 solution = new LeetCode_1878();
        int[] ans = solution.getBiggestThree(grid);
        ArrayUtil.printArray(ans);
    }
}
