package leetCode.dynamic_programming;


/**
 * 1883. 准时抵达会议现场的最小跳过休息次数
 * */
public class LeetCode_1883 {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        // 可忽略的误差
        final double EPS = 1e-7;

        // 极大值
        final double INFTY = 1e20;

        int n = dist.length;
        double[][] dp = new double[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            double t = (double) dist[i - 1] / speed;
            for(int j = 0; j <= i; j++){
                dp[i][j] = Double.MAX_VALUE;
                if(j <= i - 1){
                    dp[i][j] = Math.ceil(dp[i - 1][j] + t - EPS);
                }

                if(j > 0){
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + t);
                }
            }
        }

        for(int i = 0; i <= n; i++){
            if(dp[n][i] <= hoursBefore){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        int[] dist = {7,3,5,5};
        int speed = 2;
        int hoursBefore = 10;
        LeetCode_1883 solution = new LeetCode_1883();
        int ans = solution.minSkips(dist, speed, hoursBefore);
        System.out.println(ans);
    }
}
