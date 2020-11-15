package leedCode;

import java.util.*;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * */
public class LeedCode_402 {
    /**
     * 暴力解法
     * */
    public static String removeKdigits1(String num, int k){
        if(null == num || num.length() == 0){
            return "0";
        }

        if(num.length() == 1){
            return "0";
        }

        if(k == 0){
            return num;
        }

        StringBuffer numSb = new StringBuffer(num);
        int len = numSb.length();
        while(k > 0){
            for(int i = 1; i < len; i++){
                Character cur = num.charAt(i);
                Character pre = num.charAt(i - 1);

                // 找到 1axxx 1bxxx中 a < b的，并删除
                if(pre > cur){
                    numSb.deleteCharAt(i - 1);
                    break;
                }
            }
            k--;
        }

        if(numSb.length() == 0){
            return "0";
        }

        StringBuffer rst = new StringBuffer();
        boolean isZero = true;
        for(int i = 0; i < numSb.length(); i++){
            Character ch = numSb.charAt(i);
            if(isZero && ch == '0'){
                continue;
            }
            rst.append(ch);
            isZero = false;
        }

        return (rst.length() == 0) ? "0" : rst.toString();
    }

    public static String removeKdigits(String num, int k){
        int len = num.length();
        LinkedList<Character> queue = new LinkedList();
        for(int i = 0; i < len; i++){
            Character ch = num.charAt(i);
            while(!queue.isEmpty() && k > 0 && ch < queue.peekLast()){
                queue.pollLast();
                k--;
            }
            queue.offerLast(ch);
        }

        for(int i = 0; i < k; i++){
            queue.pollLast();
        }

        StringBuffer rst = new StringBuffer();
        boolean flag = true;
        while(!queue.isEmpty()){
            Character ch = queue.pollFirst();
            if(flag && ch == '0'){
                continue;
            }
            flag = false;
            rst.append(ch);
        }

        return rst.length() == 0 ? "0" : rst.toString();
    }

    public static void main(String[] args){
        String num = "112";
        String rst = removeKdigits(num, 1);
        System.out.println(rst);
    }
}
