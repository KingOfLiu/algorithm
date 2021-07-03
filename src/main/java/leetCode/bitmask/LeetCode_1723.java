package leetCode.bitmask;

/**
 * 1723. 完成所有工作的最短时间
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。
 * 工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方
 * 案，使工人的 最大工作时间 得以 最小化 。
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 *
 * 示例 1：
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 *
 * 示例 2：
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 *
 * 提示：
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 * */
public class LeetCode_1723 {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[] sum = new int[1 << n];
        // 求出每一个组合的和
        for(int mask = 1; mask < (1 << n); mask++){
            int curSum = 0;
            for(int i = 0; i < n; i++){
                int curMask = (1 << i);
                if((mask & curMask) == 0){
                    continue;
                }
                curSum += jobs[i];
            }

            sum[mask] = curSum;
        }

        int[][] dp = new int[k][1 << n];
        for(int i = 0; i < (1 << n); i++){
            dp[0][i] = sum[i];
        }

        for(int i = 1; i < k; i++){
            for(int mask = 0; mask < (1 << n); mask++){
                int min = Integer.MAX_VALUE;

                // 求mask的子集
                for(int subMask = mask; subMask != 0; subMask = (subMask - 1) & mask){
                    int curSum = Math.max(dp[i - 1][mask ^ subMask], sum[subMask]);
                    min = Math.min(min, curSum);
                }

                dp[i][mask] = min;
            }
        }
        return dp[k - 1][(1 << n) - 1];
    }

    public static void main(String[] args){
        int[] jobs = {1,2,4,7,8};
        int k = 2;
        LeetCode_1723 solution = new LeetCode_1723();
        int ans = solution.minimumTimeRequired(jobs, k);
        System.out.println(ans);
    }
}
