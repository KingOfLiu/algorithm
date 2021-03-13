package leetCode.binary_search;


import java.util.ArrayList;
import java.util.List;

/**
 * 327. 区间和的个数
 * 给定一个整数数组 nums 。区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 请你以下标 i （0 <= i <= nums.length ）为起点，元素个数逐次递增，计算子数组内的元素和。
 *
 * 当元素和落在范围 [lower, upper] （包含 lower 和 upper）之内时，记录子数组当前最末元素下标 j ，记作 有效 区间和 S(i, j) 。
 *
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 有效 区间和的个数。
 *
 *  
 *
 * 注意：
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 *
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出：3
 * 解释：
 * 下标 i = 0 时，子数组 [-2]、[-2,5]、[-2,5,-1]，对应元素和分别为 -2、3、2 ；其中 -2 和 2 落在范围 [lower = -2, upper = 2] 之间，因此记录有效区间和 S(0,0)，S(0,2) 。
 * 下标 i = 1 时，子数组 [5]、[5,-1] ，元素和 5、4 ；没有满足题意的有效区间和。
 * 下标 i = 2 时，子数组 [-1] ，元素和 -1 ；记录有效区间和 S(2,2) 。
 * 故，共有 3 个有效区间和。
 *
 * */
public class LeetCode_327 {
    public int countRangeSum(int[] nums, int lower, int upper){
        if(nums.length == 0){
            return 0;
        }

        // 有序数组
        List<Long> sorted = new ArrayList<Long>();
        sorted.add(0L);

        long sum = 0;
        int ans = 0;

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];

            // 在有序数组中查找小于等于lower的元素索引（即：小于等于lower的元素数量）
            int lowerIndex = findIndex(false, sorted, sum - (long) lower);

            // 在有序数组中查找小于upper的元素索引（即：小于upper的元素数量）
            int upperIndex = findIndex(true, sorted, sum - (long) upper);

            // 把lower和upper重复计算的数量去掉，并累加到最终的结果上。
            ans += (lowerIndex - upperIndex);

            // 查找有序的插入索引
            int insertIndex = findIndex(true, sorted, sum);
            sorted.add(insertIndex, sum);
        }
        return ans;
    }

    public int findIndex(boolean flag, List<Long> sorted, long target){
        int left = 0, right = sorted.size() - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            boolean condition = sorted.get(mid) <= target;
            if(flag){
                condition = sorted.get(mid) < target;
            }
            if(condition){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return flag ?
                (sorted.get(left) < target ? left + 1 : left) :
                (sorted.get(left) <= target ? left + 1 : left) ;
    }

    public static void main(String[] args){
        int[] nums = {-2,5,-1,8, -10, 4, 9, 2, 1};
        int lower = -5, upper = 5;
        LeetCode_327 solution = new LeetCode_327();
        int ans = solution.countRangeSum(nums, lower, upper);
        System.out.println(ans);
    }
}
