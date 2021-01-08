package leetCode.array;

/**
 * 不同路径
 * */
public class LeetCode_62 {
    public int uniquePaths(int m, int n){
        int[][] f = new int[m][n];
        for(int i = 0; i < m; i++){
            f[i][0] = 1;
        }

        for(int j = 0; j < n; j++){
            f[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }


        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }

        return f[m - 1][n - 1];
    }

    public static void main(String[] args){
        int m = 3;
        int n = 7;
        LeetCode_62 solution = new LeetCode_62();
        int ans = solution.uniquePaths(m, n);
        System.out.println(ans);
    }
}
