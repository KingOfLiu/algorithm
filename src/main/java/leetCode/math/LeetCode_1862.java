package leetCode.math;

import java.util.Arrays;

/**
 * 1862. 向下取整数对和
 * 给你一个整数数组 nums ，请你返回所有下标对 0 <= i, j < nums.length 的 floor(nums[i] / nums[j]) 结果之和。
 * 由于答案可能会很大，请你返回答案对10^9 + 7 取余 的结果。
 * 函数 floor() 返回输入数字的整数部分。
 *
 * 示例 1：
 * 输入：nums = [2,5,9]
 * 输出：10
 * 解释：
 * floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
 * floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
 * floor(5 / 2) = 2
 * floor(9 / 2) = 4
 * floor(9 / 5) = 1
 * 我们计算每一个数对商向下取整的结果并求和得到 10 。
 *
 * 示例 2：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：49
 *
 *
 * 提示：
 *  - 1 <= nums.length <= 10^5
 *  - 1 <= nums[i] <= 10^5
 * */
public class LeetCode_1862 {
    final int MOD = (int) 1e9;

    public int sumOfFlooredPairs(int[] nums) {
        int upper = Arrays.stream(nums).max().getAsInt();
        int[] cnt = new int[upper + 1];
        for(int num : nums){
            ++cnt[num];
        }

        // 预处理前缀和
        int[] pre = new int[upper + 1];
        for(int i = 1; i <= upper; i++){
            pre[i] = pre[i - 1] + cnt[i];
        }

        long ans = 0;
        for(int y = 1; y <= upper; y++){
            if(cnt[y] != 0){
                for(int d = 1; d * y <= upper; d++){
                    int xStart = (d + 1) * y - 1;
                    int xEnd = (d * y) - 1;

                    for(int x = xEnd + 1; x <= Math.min(xStart, upper); x++){
                        System.out.println("x = " + x + " y = " + y + " d = " + d + " cnt[x] = " + cnt[x] + "     x / y = " + d + " ");
                    }
                    System.out.println();
                    int xCnt = (pre[Math.min(xStart, upper)] - pre[xEnd]);
                    long yCnt = (long) cnt[y] * d;

                    ans += xCnt * yCnt;
                }
                System.out.println("------------------------");
            }
        }

        return (int) (ans % MOD);
    }

    public static void main(String[] args){
        int[] nums = {2,5,9,7,6,3,5};
        LeetCode_1862 solution = new LeetCode_1862();
        int ans = solution.sumOfFlooredPairs(nums);
        System.out.println(ans);
    }
}