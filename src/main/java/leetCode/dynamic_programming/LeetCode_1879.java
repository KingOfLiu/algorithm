package leetCode.dynamic_programming;

import java.util.Arrays;

/**
 * 1879. 两个数组最小的异或值之和
 * 给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。
 * 两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) 
 * （下标从 0 开始）。
 *  比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。
 * 请你将 nums2 中的元素重新排列，使得 异或值之和 最小 。
 * 请你返回重新排列之后的 异或值之和 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [2,3]
 * 输出：2
 * 解释：将 nums2 重新排列得到 [3,2] 。
 * 异或值之和为 (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2 。
 *
 * 示例 2：
 * 输入：nums1 = [1,0,3], nums2 = [5,3,4]
 * 输出：8
 * 解释：将 nums2 重新排列得到 [5,4,3] 。
 * 异或值之和为 (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8 。
 *
 *
 * 提示：
 *
 * n == nums1.length
 * n == nums2.length
 * 1 <= n <= 14
 * 0 <= nums1[i], nums2[i] <= 10^7
 * */
public class LeetCode_1879 {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] f = new int[1 << n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;

        for(int mask = 1; mask < (1 << n); mask++){
            System.out.println("mask:" + mask);
            for(int i = 0; i < n; i++){
                //System.out.println("     i = " + i + " mask & (1 << i) => " + Integer.toBinaryString(mask) + " & " + Integer.toBinaryString((1 << i)));
                if((mask & (1 << i)) != 0){
                    int w = f[mask ^ (1 << i)];
                    int s = nums1[oneCount(mask) - 1] ^ nums2[i];
                    //System.out.print("w = f[mask ^ (1 << i)] => " + Integer.toBinaryString(mask) + " ^ " + Integer.toBinaryString((1 << i)) + "      ");
                    //System.out.print("s = nums1[oneCount(mask) - 1] ^ nums2[i]");
                    f[mask] = Math.min(f[mask], w + s);
                }
            }
        }

        return f[(1 << n) - 1];
    }

    int oneCount(int num){
        int cnt = 0;
        for(int i = 30; i >= 0; i--){
            int mask = 1 << i;
            if((num & mask) == mask){
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args){
        int[] nums1 = {1,0,3};
        int[] nums2 = {5,3,4};
        LeetCode_1879 solution = new LeetCode_1879();
        int ans = solution.minimumXORSum(nums1, nums2);
        System.out.println(ans);
    }
}
