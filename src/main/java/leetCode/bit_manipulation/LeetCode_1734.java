package leetCode.bit_manipulation;

import java.util.Arrays;

/**
 * 1734. 解码异或后的排列
 *
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，
 * 满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，
 * 如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 *
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 *
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *
 *
 * 提示：
 * 3 <= n < 10^5
 * n 是奇数。
 * encoded.length == n - 1
 * */
public class LeetCode_1734 {
    public int[] decode(int[] encoded) {
        int len = encoded.length;
        int n = len + 1;
        int nXORTotal = 0;
        for(int i = 1; i <= n; i++){
            nXORTotal ^= i;
        }

        int noFirstTotal = encoded[1];
        for(int i = 3; i < len; i++){
            // 奇数下标
            if((i & 1) == 1){
                noFirstTotal = noFirstTotal ^ encoded[i];
            }
        }

        int[] ans = new int[n];
        ans[0] = (nXORTotal ^ noFirstTotal);

        for(int i = 0; i < len; i++){
            ans[i + 1] = encoded[i] ^ ans[i];
        }

        return ans;
    }

    public static void main(String[] args){
        int[] encoded = {6,5,4,6};
        LeetCode_1734 solution = new LeetCode_1734();
        int[] ans = solution.decode(encoded);
        for(int n : ans){
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
