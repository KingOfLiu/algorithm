package leetCode.stack;

import java.util.Stack;

/**
 * 计算器||
 * */
public class LeetCode_227 {
    public int calculate(String s){
        Stack<Integer> stack = new Stack();
        int len = s.length();
        int num = 0;
        char sign = '+';
        for(int i = 0; i < len; i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }

            if((!Character.isDigit(ch) && ch != ' ') || i == len - 1){
                switch(sign){
                    case '+' :
                        stack.push(num); break;
                    case '-':
                        stack.push(-num); break;
                    case '*':
                        stack.push(stack.pop() * num); break;
                    case '/':
                        stack.push(stack.pop() / num); break;
                }

                // 更新当前符号
                sign = ch;
                num = 0;
            }
        }

        int ans = 0;
        while(!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }

    public static void main(String[] args){
        String s = " 3+5 / 2 * 10     / 3";
        LeetCode_227 solution = new LeetCode_227();
        int ans = solution.calculate(s);
        System.out.println(ans);
    }
}
