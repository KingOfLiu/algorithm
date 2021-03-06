package leetCode.dynamic_programming;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *      1 <= nums.length <= 2500
 *      -10^4 <= nums[i] <= 10^4
 * */
public class LeetCode_300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for(int i = 0; i < n; i++){
            dp[i] = 1;

            // 找到nums[i]之前比nums[i]小的nums[j]
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 二分查找优化
     * */
    public int lengthOfLISBR(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        // j为dp数组的初始位置
        int j = 0;
        for(int i = 1; i < n; i++){
            if(dp[j] < nums[i]){
                dp[++j] = nums[i];
            } else {
                int left = 0, right = j;
                while(left < right){
                    int mid = left + (right - left) / 2;
                    if(dp[mid] < nums[i]){
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                dp[left] = nums[i];
            }
        }

        return j + 1;
    }

    public static void main(String[] args){
        int[] nums = {10,9,2,5,3,7,101,18};
        LeetCode_300 solution = new LeetCode_300();
        int ans = solution.lengthOfLISBR(nums);
        System.out.println(ans);
    }
}
