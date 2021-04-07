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
        int left = 1, right = maxSum + 1, ans = 0;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(hasMaxVal(index, n, mid, maxSum)){
                ans = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return ans;
    }

    boolean hasMaxVal(int index, int n, int maxVal, int maxSum){
        // 超时算法
        // 计算0到index的和
        /*long preSum = preSum(0, index, maxVal);
        long suffixSum = suffixSum(index + 1, n - 1, maxVal - 1);
        return preSum + suffixSum <= maxSum;*/

        long sum = n - 1 + maxVal;
        long left = Math.min(index, maxVal - 1);
        sum += (1L * ((maxVal - left) + (maxVal - 1)) * left / 2);

        long right = Math.min(n - index - 1, maxVal - 1);
        sum += (1L * ((maxVal - 1) + (maxVal - right)) * right / 2);

        sum = sum - left - right;
        return sum <= maxSum;
    }

    long preSum(int startIndex, int endIndex, long maxVal){
        long sum = 0;
        for(int i = endIndex; i >= startIndex; i--){
            if(maxVal == 1){
                sum += 1;
            } else {
                sum += (maxVal--);
            }
        }
        return sum;
    }

    long suffixSum(int startIndex, int endIndex, long maxVal){
        long sum = 0;
        for(int i = startIndex; i <= endIndex; i++){
            if(maxVal == 1){
                sum += 1;
            } else {
                sum += (maxVal --);
            }
        }

        return sum;
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();

        int n = 999999999, index = 100000000, maxSum = 999999999;
        //int n = 9494370, index = 608170, maxSum = 461398012;
        LeetCode_1802 solution = new LeetCode_1802();
        int ans = solution.maxValue(n, index, maxSum);
        System.out.println(ans);

        long endTime = System.currentTimeMillis();
        System.out.println("时间：" + (endTime - startTime));
        //long test = preSum(0, 7, 5);
        //long test = suffixSum(4, 11, 5);
        //System.out.println(test);
    }
}