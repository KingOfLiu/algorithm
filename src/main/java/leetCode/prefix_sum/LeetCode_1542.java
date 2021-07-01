package leetCode.prefix_sum;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

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
        Set<Integer> goodAns = new TreeSet<Integer>(){{
            add(0);
            for(int mask = 0; mask < 10; mask++){
                add(1 << mask);
            }
        }};

        int[] cnt = new int[1 << 10];
        Arrays.fill(cnt, -1);
        int n = s.length();
        int sum = 0;
        int ans = 0;
        for(int i = 0; i < n; i++){
            int curNum = s.charAt(i) - '0';
            sum ^= (1 << curNum);
            if(goodAns.contains(sum)){
                ans = Math.max(ans, i + 1);
            }

            for(int mask = 0; mask < 10; mask++){
                int curMask = 1 << mask;
                int prevIndex = cnt[sum ^ curMask];

                /*System.out.println(i + " # " + prevIndex + " # " + (sum ^ mask) + " # " + sum + " # " + curMask);
                if((goodAns.contains(sum ^ curMask) || goodAns.contains(sum ^ sum ^ curMask)) && prevIndex != -1){
                    //System.out.println(prevIndex + " # " + i + " --- " + (sum ^ sum ^ curMask));
                    ans = Math.max(ans, i - prevIndex);
                }*/
                if(prevIndex != -1){
                    ans = Math.max(ans, i - prevIndex);
                }
            }

            cnt[sum] = i;
        }
        return ans;
    }

    public static void main(String[] args){
        //String s = "51224";
        //String s = "350844";
        String s = "0928362497011412243";
        //String s = "213123";
        LeetCode_1542 solution = new LeetCode_1542();
        int ans = solution.longestAwesome(s);
        System.out.println(ans);
    }
}