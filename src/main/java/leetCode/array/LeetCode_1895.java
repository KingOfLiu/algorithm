package leetCode.array;

/**
 * 1895. 最大的幻方
 *
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。
 * 幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 *
 * 示例 1：
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 * 解释：最大幻方尺寸为 3 。
 * 每一行，每一列以及两条对角线的和都等于 12 。
 * - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 * - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 * - 对角线的和：5+4+3 = 6+4+2 = 12
 *
 * 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * 输出：2
 *
 * 提示：
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 50
 *  1 <= grid[i][j] <= 106
 * */
public class LeetCode_1895 {
    private int[][] rowSum;
    private int[][] colSum;
    private int[][] ascSum;
    private int[][] descSum;

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        rowSum = new int[m + 1][n + 1];
        colSum = new int[m + 1][n + 1];
        ascSum = new int[m + 1][n + 1];
        descSum = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                rowSum[i + 1][j + 1] = rowSum[i + 1][j] + grid[i][j];
                colSum[i + 1][j + 1] = colSum[i][j + 1] + grid[i][j];
                ascSum[i + 1][j + 1] = ascSum[i][j] + grid[i][j];
                descSum[i + 1][j + 1] = (j == n - 1 ? 0 : descSum[i][j + 2]) + grid[i][j];
            }
        }

        int maxLen = Math.min(m, n);
        for(int len = maxLen; len > 1; len--){
            for(int i = 0; i < m - len + 1; i ++){
                for(int j = 0; j < n - len + 1; j ++){
                    if(isMatch(i + 1, j + 1, len, n)){
                        return len;
                    }
                }
            }
        }

        return 1;
    }

    boolean isMatch(int i, int j, int len, int n){
        int sum = colSum[i + len - 1][j] - colSum[i - 1][j];
        for(int k = 0; k < len; k++){
            if(rowSum[i + k][j + len - 1] - rowSum[i + k][j - 1] != sum){
                return false;
            }

            if(colSum[i + len - 1][j + k] - colSum[i - 1][j + k] != sum){
                return false;
            }
        }

        if(ascSum[i + len - 1][j + len - 1] - ascSum[i - 1][j - 1] != sum){
            return false;
        }

        if(descSum[i + len - 1][j] - (j + len - 1 == n ? 0 : descSum[i - 1][j + len]) != sum){
            return false;
        }

        return true;
    }

    public static void main(String[] args){
        int[][] grid = {{7,1,4,5,6},{2,5,1,6,4},{1,5,4,3,2},{1,2,7,3,4}};
        LeetCode_1895 solution = new LeetCode_1895();
        int ans = solution.largestMagicSquare(grid);
        System.out.println(ans);
    }
}
