package leetCode.dynamic_programming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1896. 反转表达式值的最少操作次数
 * 给你一个 有效的 布尔表达式，用字符串 expression 表示。
 * 这个字符串包含字符 '1'，'0'，'&'（按位 与 运算），'|'（按位 或 运算），'(' 和 ')' 。
 *      比方说，"()1|1" 和 "(1)&()" 不是有效 布尔表达式。而 "1"， "(((1))|(0))" 和 "1|(0&(1))" 是 有效 布尔表达式。
 * 你的目标是将布尔表达式的 值 反转 （也就是将 0 变为 1 ，或者将 1 变为 0），请你返回达成目标需要的 最少操作 次数。
 * 比方说，如果表达式 expression = "1|1|(0&0)&1" ，它的 值 为 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1 。
 * 我们想要执行操作将 新的 表达式的值变成 0 。
 *
 *  可执行的 操作 如下：
 * 将一个 '1' 变成一个 '0' 。
 * 将一个 '0' 变成一个 '1' 。
 * 将一个 '&' 变成一个 '|' 。
 * 将一个 '|' 变成一个 '&' 。
 * 注意：'&' 的 运算优先级 与 '|' 相同 。计算表达式时，括号优先级 最高 ，然后按照 从左到右 的顺序运算。
 *
 *
 * 示例 1：
 * 输入：expression = "1&(0|1)"
 * 输出：1
 * 解释：我们可以将 "1&(0|1)" 变成 "1&(0&1)" ，执行的操作为将一个 '|' 变成一个 '&' ，执行了 1 次操作。
 * 新表达式的值为 0 。
 *
 * 示例 2：
 * 输入：expression = "(0&0)&(0&0&0)"
 * 输出：3
 * 解释：我们可以将 "(0&0)&(0&0&0)" 变成 "(0|1)|(0&0&0)" ，执行了 3 次操作。
 * 新表达式的值为 1 。
 *
 * 示例 3：
 * 输入：expression = "(0|(1|0&1))"
 * 输出：1
 * 解释：我们可以将 "(0|(1|0&1))" 变成 "(0|(0|0&1))" ，执行了 1 次操作。
 * 新表达式的值为 0 。
 *
 * 提示：
 *  1 <= expression.length <= 10^5
 *  expression 只包含 '1'，'0'，'&'，'|'，'(' 和 ')'
 *  所有括号都有与之匹配的对应括号。
 *  不会有空的括号（也就是说 "()" 不是 expression 的子字符串）。
 * */
public class LeetCode_1896 {
    Deque<int[]> stackNum = new ArrayDeque<int[]>();
    Deque<Character> stackOp = new ArrayDeque<Character>();

    public int minOperationsToFlip(String expression) {
        for(char ch : expression.toCharArray()){
            if(ch == '(' || ch == '&' || ch == '|'){
                stackOp.push(ch);
            } else if(ch == '0'){
                stackNum.push(new int[]{0, 1});
                calu();
            } else if(ch == '1'){
                stackNum.push(new int[]{1, 0});
                calu();
            } else {
                stackOp.poll();
                calu();
            }
        }

        int[] ans = stackNum.poll();
        return Math.max(ans[0], ans[1]);
    }

    void calu(){
        if(stackNum.size() >= 2 && (stackOp.peek() == '&' || stackOp.peek() == '|')){
            int[] one = stackNum.poll();
            int[] two = stackNum.poll();

            int[] opAnd = opAnd(one[0], one[1], two[0], two[1]);
            int[] opOr = opOr(one[0], one[1], two[0], two[1]);

            if(stackOp.peek() == '&'){
                int newX = Math.min(opAnd[0], opOr[0] + 1);
                int newY = Math.min(opAnd[1], opOr[1] + 1);
                stackNum.push(new int[]{newX, newY});
            } else if(stackOp.peek() == '|'){
                int newX = Math.min(opOr[0], opAnd[0] + 1);
                int newY = Math.min(opOr[1], opAnd[1] + 1);
                stackNum.push(new int[]{newX, newY});
            }
            stackOp.poll();
        }
    }

    int[] opAnd(int x1, int y1, int x2, int y2){
        int min1 = Math.min(x1 + x2, x1 + y2);
        int min2 = Math.min(min1, y1 + x2);
        return new int[]{Math.min(min1, min2), y1 + y2};
    }

    int[] opOr(int x1, int y1, int x2, int y2){
        int min1 = Math.min(x1 + y2, x2 + y1);
        int min2 = Math.min(min1, y1 + y2);
        return new int[]{x1 + x2, Math.min(min1, min2)};
    }

    public static void main(String[] args){
        String expression = "(0|(1|0&1))";
        LeetCode_1896 solution = new LeetCode_1896();
        int ans = solution.minOperationsToFlip(expression);
        System.out.println(ans);
    }
}
