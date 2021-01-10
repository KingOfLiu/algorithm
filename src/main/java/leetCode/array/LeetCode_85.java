package leetCode.array;

import common.ArrayUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * */
public class LeetCode_85 {
    /**
     * 暴力优化解法
     * */
    public int maximalRectangle(char[][] matrix){
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        // 计算当前单元格左边最多连续1的长度
        int[][] left = new int[rowLen][colLen];
        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(matrix[i][j] == '1'){
                    if(j == 0){
                        left[i][j] = left[i][j] + 1;
                    } else {
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(matrix[i][j] == '0'){
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for(int k = i - 1; k >= 0; k--){
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }

                ans = Math.max(ans, area);
            }
        }
        return ans;
    }

    public int maximalRectangleStack(char[][] matrix){
        ArrayUtil.printArray(matrix);

        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        System.out.println();
        ArrayUtil.printArray(left);

        // 单调栈实现
        int ans = 0;

        for(int j = 0; j < n; j++){
            int[] up = new int[m];
            int[] down = new int[m];
            Deque<Integer> stack = new ArrayDeque<Integer>();
            for(int i = 0; i < m; i++){
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]){
                    stack.pop();
                }
                up[i] = (stack.isEmpty() ? -1 : stack.peek());
                stack.push(i);
            }

            stack.clear();

            for(int i = m - 1; i >= 0; i--){
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]){
                    stack.pop();
                }
                down[i] = (stack.isEmpty() ? m : stack.peek());
                stack.push(i);
            }
            System.out.print("列 = " + j + " up = ");
            ArrayUtil.printArray(up, false);
            System.out.print(" down = ");
            ArrayUtil.printArray(down, false);


            // 计算结果
            for(int i = 0; i < m; i++){
                int height = down[i] - up[i] - 1;
                System.out.println(" h = " + height);
                int area = height * left[i][j];
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }

    public static void main(String[] args){
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        /*char[][] matrix = {
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','0','0','0'}
        };*/

        LeetCode_85 solution = new LeetCode_85();
        int ans = solution.maximalRectangleStack(matrix);
        System.out.println(ans);
    }
}
