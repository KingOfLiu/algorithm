package leetCode.prefix_sum;

import java.util.*;

/**
 * 1542. 找出最长的超赞子字符串
 * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
 * 「超赞子字符串」需满足满足下述两个条件：
 *      该字符串是 s 的一个非空子字符串
 *      进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 *
 * 示例 1：
 * 输入：s = "3242415"
 * 输出：5
 * 解释："24241" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "24142"
 *
 * 示例 2：
 * 输入：s = "12345678"
 * 输出：1
 *
 * 示例 3：
 * 输入：s = "213123"
 * 输出：6
 * 解释："213123" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "231132"
 *
 * 示例 4：
 * 输入：s = "00"
 * 输出：2
 *
 * 提示：
 *  - 1 <= s.length <= 10^5
 *  - s 仅由数字组成
 * */
public class LeetCode_1542 {
    public int longestAwesome(String s) {
        int[] cnt = new int[1 << 10];
        Arrays.fill(cnt, -2);
        cnt[0] = -1;
        int sum = 0, ans = 0, n = s.length();
        for(int i = 0; i < n; i++){
            int digit = s.charAt(i) - '0';
            sum ^= (1 << digit);

            if(cnt[sum] != -2){
                ans = Math.max(ans, i - cnt[sum]);
            } else {
                cnt[sum] = i;
            }

            for(int k = 0; k < 10; k++){
                int mask = (1 << k);
                if(cnt[sum ^ mask] != -2){
                    ans = Math.max(ans, i - cnt[sum ^ mask]);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args){
        //String s = "51224";
        //String s = "350844";
        String s = "213123";
        //String s = "213123";
        LeetCode_1542 solution = new LeetCode_1542();
        int ans = solution.longestAwesome(s);
        System.out.println(ans);
    }
}