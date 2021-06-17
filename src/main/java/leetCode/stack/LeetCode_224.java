package leetCode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 *
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 *
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 * 提示：
 *  1 <= s.length <= 3 * 105
 *  s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 *  s 表示一个有效的表达式
 * */
public class LeetCode_224 {
    public int calculate(String s) {
        Deque<Integer> stackNum = new ArrayDeque<Integer>();
        Deque<Integer> stackOp = new ArrayDeque<Integer>();

        // 正负号标识
        int ans = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == ' '){
                continue;
            }

            if(ch == '+' || ch == '-'){
                sign = (ch == '+') ? 1 : -1;
            } else if(Character.isDigit(ch)){
                int num = ch - '0';
                while(i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                ans += sign * num;
            } else if(ch == '('){
                stackNum.push(ans);
                stackOp.push(sign);
                ans = 0;
                sign = 1;
            } else {
                ans = stackNum.poll() + ans * stackOp.poll();
            }
        }

        return ans;
    }

    public static void main(String[] args){
        String s = "+48 + -48";
        LeetCode_224 solution = new LeetCode_224();
        int ans = solution.calculate(s);
        System.out.println(ans);

        Arrays.stream(new int[]{}).sum();
    }
}
