package leetCode.bit_manipulation;

import java.util.*;

/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * 请返回所有可行解 s 中最长长度。
 *
 * 示例 1：
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 *
 * 示例 2：
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 *
 * 示例 3：
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *
 *
 * 提示：
 *  - 1 <= arr.length <= 16
 *  - 1 <= arr[i].length <= 26
 *  - arr[i] 中只含有小写英文字母
 * */
public class LeetCode_1239 {
    public int maxLength(List<String> arr) {
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        return dfs(0, arr, map);
    }

    int dfs(int index, List<String> arr, TreeMap<Character, Integer> map){
        if(index == arr.size()){
            return 0;
        }

        TreeMap<Character, Integer> t = new TreeMap<Character, Integer>(map);
        String str = arr.get(index);
        boolean check = isUnique(str, t);
        if(check){
            int curLen = arr.get(index).length();
            int len1 = curLen + dfs(index + 1, arr, t);
            int len2 = dfs(index + 1, arr, map);
            return Math.max(len1, len2);
        }

        return dfs(index + 1, arr, map);
    }

    boolean isUnique(String str, TreeMap<Character, Integer> map){
        for(char ch : str.toCharArray()){
            int cnt = map.getOrDefault(ch, 0);
            map.put(ch, ++cnt);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() > 1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        List<String> arr = Arrays.asList("cha","r","ers","xy");
        LeetCode_1239 solution = new LeetCode_1239();
        int ans = solution.maxLength(arr);
        System.out.println(ans);
    }
}
