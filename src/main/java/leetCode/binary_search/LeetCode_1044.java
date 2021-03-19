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
        int left = 1, right = s.length() - 1;
        int lastDupIndex = 0;
        while(left <= right){
            int subStrLen = left + (right - left) / 2;
            int dupIndex = findStartStringIndex(s, subStrLen);
            if(dupIndex == -1){
                right = subStrLen - 1;
            } else {
                lastDupIndex = subStrLen;
                left = subStrLen + 1;
            }
        }

        int firstIndex = findStartStringIndex(s, lastDupIndex);
        return firstIndex == -1 ? " " : s.substring(firstIndex, firstIndex + lastDupIndex);
    }

    /**
     * 查找重复字符串的起始位置索引
     * */
    private int findStartStringIndex(String s, int mid){
        long h = 0;
        long aL = 1;
        char[] chars = s.toCharArray();
        for(int i = 0; i < mid; i++){
            h = (h * base + (chars[i] - 'a')) % MOD;
            aL = (aL * base) % MOD;
        }

        Set<Long> set = new HashSet<Long>();
        set.add(h);

        for(int i = 1; i < chars.length - mid + 1; i++){
            h = (h * base - (chars[i - 1] - 'a') * aL % MOD + MOD) % MOD;
            h = (h + (chars[i + mid - 1] - 'a')) % MOD;

            if(set.contains(h)){
                return i;
            }
            set.add(h);
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
