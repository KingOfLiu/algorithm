package leetCode.greedy;

/**
 * 1802. 有界数组中指定下标处的最大值
 *
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * 1.nums.length == n
 * 2.nums[i] 是 正整数 ，其中 0 <= i < n
 * 3.abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * 4.nums 中所有元素之和不超过 maxSum
 * 5.nums[index] 的值被 最大化
 *
 * 返回你所构造的数组中的 nums[index] 。
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 * 示例 1：
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 *
 * 示例 2：
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 * */
public class LeetCode_1802 {
    public int maxValue(int n, int index, int maxSum) {
        // 1 到 n中间二分的尝试找到答案
        int left = 1, right = n;
        while(left <= right){
            int maxVal = left + (right - left) / 2;
            if(hasMaxVal(index, n, maxVal, maxSum)){
                left = maxVal + 1;
            } else {
                right = maxVal - 1;
            }
        }

        return left;
    }

    boolean hasMaxVal(int index, int n, int maxVal, int maxSum){
        // 0 到 index
        int presum = 0, preVal = maxVal;
        for(int i = index; i >= 0; i--){
            System.out.print(preVal + " ");
            if(preVal == 1){
                presum += 1;
            } else {
                presum += preVal - 1;
                preVal -= 1;
            }
        }

        // index + 1 到 n
        int suffsum = 0, suffVal = maxVal;
        for(int i = index + 1; i <= n; i++){
            System.out.print(suffVal + " ");
            if(suffVal == 1){
                suffsum += 1;
            } else {
                suffsum += suffVal;
                suffVal -= 1;
            }
        }
        System.out.println();

        System.out.println(maxVal + " # " + presum + " # " + suffsum + " # " + maxSum);
        return (presum + suffsum) <= maxSum;
    }
}