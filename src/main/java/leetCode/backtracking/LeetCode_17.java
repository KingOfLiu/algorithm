package leetCode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。
 * 注意 1 不对应任何字母。
 *
 * 1 -> !@#
 * 2 -> abc
 * 3 -> def
 * 4 -> ghi
 * 5 -> jkl
 * 6 -> mno
 * 7 -> pqrs
 * 8 -> tuv
 * 9 -> wxyz
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * */
public class LeetCode_17 {
    public List<String> letterCombinations(String digits){
        List<String> ans = new ArrayList<String>();
        if(digits.length() == 0){
            return ans;
        }

        Map<Character, String> phoneMap = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        dfs(ans, new StringBuffer(), digits, 0, phoneMap);
        return ans;
    }

    void dfs(List<String> combinations, StringBuffer combination, String digits, int index, Map<Character, String> phoneMap){
        if(index == digits.length()){
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            for(int i = 0; i < letters.length(); i++){
                combination.append(letters.charAt(i));
                dfs(combinations, combination, digits, index + 1, phoneMap);
                combination.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args){
        String digits = "23";
        LeetCode_17 solution = new LeetCode_17();
        List<String> ans = solution.letterCombinations(digits);
        System.out.println(ans);
    }
}
