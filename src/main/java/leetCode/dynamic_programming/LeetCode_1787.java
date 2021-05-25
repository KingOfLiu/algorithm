package leetCode.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1787. 使所有区间的异或结果为零
 * 给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果
 * 是对下标位于 left 和 right（包括 left 和 right ）之间所有元素进行 XOR
 * 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
 *
 * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 *
 *
 * 示例 1：
 * 输入：nums = [1,2,0,3,0], k = 1
 * 输出：3
 * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
 *
 * 示例 2：
 * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
 * 输出：3
 * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
 *
 * 示例 3：
 * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
 * 输出：3
 * 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
 *
 * 提示：
 *  1> 1 <= k <= nums.length <= 2000
 * ​​​​​​ 2> 0 <= nums[i] < 2^10
 * */
public class LeetCode_1787 {
    public int minChanges(int[] nums, int k) {
        // x范围为[0, 2^10)
        final int MAXX = 1 << 10;

        // 极大值，防止整数溢出 选择：INT_MAX / 2
        final int INFTY = Integer.MAX_VALUE / 2;

        int n = nums.length;
        int[] f = new int[MAXX];
        Arrays.fill(f, INFTY);
        // 边界条件
        f[0] = 0;

        for(int i = 0; i < k; i++){
            // 第i个组的哈希映射
            Map<Integer, Integer> cnt = new HashMap<>();
            int size = 0;
            for(int j = i; j < n; j += k){
                cnt.put(nums[j], cnt.getOrDefault(nums[j], 0 ) + 1);
                size++;
            }

            // 求出t2
            int t2Min = Integer.MAX_VALUE;
            for(int t2 : f){
                t2Min = Math.min(t2Min, t2);
            }

            int[] g = new int[MAXX];
            Arrays.fill(g, t2Min);
            for(int mask = 0; mask < MAXX; mask++){
                // t1则需要枚举x求出
                for(Map.Entry<Integer, Integer> entry : cnt.entrySet()){
                    int x = entry.getKey(), countx = entry.getValue();
                    g[mask] = Math.min(g[mask], f[mask ^ x] - countx);
                }
            }

            for(int j = 0; j < MAXX; j++){
                g[j] += size;
            }
            f = g;
        }

        return f[0];
    }

    public static void main(String[] args){
        int[] nums = {3,4,5,2,1,7,3,4,7};
        int k = 3;
        LeetCode_1787 solution = new LeetCode_1787();
        int ans = solution.minChanges(nums, k);
        System.out.println(ans);
    }
}
