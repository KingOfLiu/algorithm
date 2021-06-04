package leetCode.dynamic_programming;

/**
 * 1799. N 次操作后的最大分数和
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 *  选择两个元素 x 和 y 。
 *  获得分数 i * gcd(x, y) 。
 *  将 x 和 y 从 nums 中删除。
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 *
 *
 * 示例 1：
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最优操作是：
 * (1 * gcd(1, 2)) = 1
 *
 * 示例 2：
 * 输入：nums = [3,4,6,8]
 * 输出：11
 * 解释：最优操作是：
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,6]
 * 输出：14
 * 解释：最优操作是：
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *
 *
 * 提示：
 *  1. 1 <= n <= 7
 *  2. nums.length == 2 * n
 *  3. 1 <= nums[i] <= 10^6
 * */
public class LeetCode_1799 {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] dp = new int[1 << n];

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int x = nums[i], y = nums[j];
                dp[(1 << i) | (1 << j)] = gcd(x, y);
            }
        }

        for(int mask = 1; mask < (1 << n); mask++){
            if(oneCnt(mask) % 2 != 0){
                continue;
            }

            // 子集的枚举
            for(int i = mask; i != 0; i = (i - 1) & mask){
                if(oneCnt(mask) - oneCnt(i) == 2){
                    int score = (oneCnt(mask) / 2) * dp[mask ^ i];
                    dp[mask] = Math.max(dp[mask], dp[i] + score);
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    int gcd(int x, int y){
        if(x % y == 0){
            return y;
        }
        return gcd(y, x % y);
    }

    int oneCnt(int num){
        int cnt = 0;
        while(num > 0){
            num = num & (num - 1);
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args){
        int[] nums = {3,4,6,8};
        LeetCode_1799 solution = new LeetCode_1799();
        int ans = solution.maxScore(nums);
        System.out.println(ans);
    }
}
