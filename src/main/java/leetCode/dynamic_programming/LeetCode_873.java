package leetCode.dynamic_programming;

import java.util.HashMap;

/**
 * 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *  a. n >= 3
 *  b. 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列，
 * 找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），
 * 而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 * 示例 1：
 * 输入: [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释:
 * 最长的斐波那契式子序列为：[1,2,3,5,8]。
 *
 * 示例 2：
 * 输入: [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释:
 * 最长的斐波那契式子序列有：
 * [1,11,12]，[3,11,14] 以及 [7,11,18]。
 *
 * 提示：
 *  a. 3 <= A.length <= 1000
 *  b. 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 *  c. （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）
 * */
public class LeetCode_873 {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        // arr单调递增，且无重复
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++){
            map.put(arr[i], i);
        }

        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                dp[i][j] = 2;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int diff = arr[j] - arr[i];
                int index = map.getOrDefault(diff, -1);
                if(index != -1){
                    if(index < i){
                        dp[i][j] = dp[index][i] + 1;
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans > 2 ? ans : 0;
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7,8};
        LeetCode_873 solution = new LeetCode_873();
        int ans = solution.lenLongestFibSubseq(arr);
        System.out.println(ans);
    }
}