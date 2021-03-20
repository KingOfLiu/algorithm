package leetCode.binary_search;

import java.util.HashSet;
import java.util.Set;

/**
 * 1044. 最长重复子串
 *
 * 给出一个字符串 S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。
 *
 * 返回任何具有最长可能长度的重复子串。（如果 S 不含重复子串，那么答案为 ""。）
 *
 * 示例 1：
 *
 * 输入："banana"
 * 输出："ana"
 *
 *
 * 示例 2：
 *
 * 输入："abcd"
 * 输出：""
 * */
public class LeetCode_1044 {
    long MOD = (long) Math.pow(2, 55) + 1;
    int base = 26;

    public String longestDupSubstring(String s){
        // 在1 到 s.length() - 1中进行二分查找
        int left = 1, right = s.length() - 1;
        int lastDupIndex = 0;
        while(left <= right){
            int subStrLen = left + (right - left) / 2;
            int dupIndex = findDupSubstringIndex(s, subStrLen);
            if(dupIndex == -1){
                right = subStrLen - 1;
            } else {
                lastDupIndex = subStrLen;
                left = subStrLen + 1;
            }
        }

        // 查询到第一个重复的hash值的索引值
        int dupFirstIndex = findDupSubstringIndex(s, lastDupIndex);
        if(dupFirstIndex == -1){
            return "";
        }

        return s.substring(dupFirstIndex, dupFirstIndex + lastDupIndex);
    }

    /**
     * 查找子字符串的索引
     */
    private int findDupSubstringIndex(String s, int subStrLen){
        long h = 0;
        long aL = 1;
        char[] chars = s.toCharArray();
        for(int i = 0; i < subStrLen; i++){
            h = (h * base + (chars[i] - 'a')) % MOD;
            aL = (aL * base) % MOD;
        }

        Set<Long> seen = new HashSet<Long>();
        seen.add(h);

        // 在长度为strLen - subStrLen的滑动窗口中查找重复的hash值
        int strLen = chars.length;
        for(int i = 1; i < strLen - subStrLen + 1; i++){
            // 去掉滑动窗口最左边的位
            h = (h * base - (chars[i - 1] - 'a') * aL % MOD + MOD) % MOD;

            // 计算滑动新的最右边的位
            h = (h + (chars[i + subStrLen - 1] - 'a')) % MOD;

            // 判断如果set中有相同的hash值，则直接返回索引
            if(seen.contains(h)){
                return i;
            }
            seen.add(h);
        }

        return -1;
    }

    public static void main(String[] args){
        String str = "nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy";
        LeetCode_1044 solution = new LeetCode_1044();
        String ans = solution.longestDupSubstring(str);
        System.out.println(ans);
    }
}
