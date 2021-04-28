package leetCode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 633. 平方数之和
 *
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 *
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 *
 *
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 *
 *
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 *
 *
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 *
 *
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 *
 *
 * 提示：
 * 0 <= c <= 2^31 - 1
 *  */
public class LeetCode_633 {
    /**
     * 哈希表思路
     * */
    public boolean judgeSquareSum(int c) {
        double sqrt = Math.sqrt(c);
        int max = (int) Math.ceil(sqrt);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i <= max; i++){
            if(map.containsKey(i * i) || (i * i) + (i * i) == c){
                return true;
            }
            int diff = c - (i * i);
            map.put(diff, i * i);
        }
        return false;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();

        int n = Integer.MAX_VALUE - 2;
        LeetCode_633 solution = new LeetCode_633();
        boolean ans = solution.judgeSquareSum(n);
        System.out.println(ans);

        long end = System.currentTimeMillis();
        System.out.println("耗时："+ (end - start));
    }
}
