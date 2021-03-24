package leetCode.string;

import java.util.*;

/**
 * 1781. 所有子字符串美丽值之和
 *
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 *
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 *
 * 提示
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 *
 * 输入：s = "aabcbaa"
 * 输出：17
 *  */
public class LeetCode_1781 {
    public int beautySum(String s){
        int len = s.length(), ans = 0;

        for(int i = 0; i < len; i++){
            int[] count = new int[26];
            for(int j = i; j < len; j++){
                count[s.charAt(j) - 'a'] += 1;

                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for(int val : count){
                    if(val > 0 && val > max){
                        max = val;
                    }

                    if(val > 0 && val < min){
                        min = val;
                    }
                }
                ans += (max - min);
            }
        }
        return ans;
    }

    boolean containsOneChar(String s){
        int[] chars = new int[26];
        int max = -1;
        for(int i = 0; i < 26; i++){
            chars[s.charAt(i) - 'a']++;
            max = Math.max(max, chars[s.charAt(i) - 'a']);
        }

        return max <= 1;
    }

    int calculate(String s){
        int len = s.length();
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        for(int i = 0; i < len; i++){
            int cnt = charMap.getOrDefault(s.charAt(i), 0);
            charMap.put(s.charAt(i), ++cnt);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(Map.Entry<Character, Integer> entry : charMap.entrySet()){
            min = Math.min(min, entry.getValue());
            max = Math.max(max, entry.getValue());
        }
        return max - min;
    }

    /**
     * 深度优先搜索获取所有子串 - 超时
     * */
    void dfs(int start, int end, String s, Set<String> subs){
        if(start >= end){
            return ;
        }

        String subString = s.substring(start, end);
        if(subString.length() > 1){
            subs.add(subString);
        }

        dfs(start + 1, end,s , subs);
        dfs(start, end - 1, s, subs);
    }

    public static void main(String[] args){
        String s = "vjlnfkctqspiyrummzykiyizwkfelpuuk";
        LeetCode_1781 solution = new LeetCode_1781();
        int ans = solution.beautySum(s);
        System.out.println(ans);
    }
}
