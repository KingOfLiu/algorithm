package leetCode.differential_array;

/**
 * 1674. 使数组互补的最少操作次数
 * 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。
 * 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。
 * 例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。
 * 返回使数组 互补 的 最少 操作次数。
 *
 * 示例 1：
 * 输入：nums = [1,2,4,3], limit = 4
 * 输出：1
 * 解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * 对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
 *
 * 示例 2：
 * 输入：nums = [1,2,2,1], limit = 2
 * 输出：2
 * 解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
 *
 * 示例 3：
 * 输入：nums = [1,2,1,2], limit = 2
 * 输出：0
 * 解释：nums 已经是互补的。
 *
 * 提示：
 * n == nums.length
 * 2 <= n <= 10^5
 * 1 <= nums[i] <= limit <= 10^5
 * n 是偶数。
 * */
public class LeetCode_1674 {
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;
        for(int i = 0; i < n / 2; i++){
            int min = Math.min(nums[i], nums[n - i - 1]);
            int max = Math.max(nums[i], nums[n - i - 1]);

            // 第一种： [2, min + 1)  两次操作
            diff[2] += 2;
            diff[min + 1] -= 2;

            // 第二种 [min + 1, min + max) 操作一次
            diff[min + 1] += 1;
            diff[min + max] -= 1;

            // 第三种 [min + max, max + limit] 操作一次
            diff[min + max + 1] += 1;
            diff[max + limit + 1] -= 1;

            // 第四种 [max + limit, 2 * limit] 操作两次
            diff[max + limit + 1] += 2;
            diff[2 * limit + 1] -= 2;
        }

        int sum = 0, ans = Integer.MAX_VALUE;
        for(int i = 2; i <= 2 * limit; i++){
            sum += diff[i];
            ans = Math.min(ans, sum);
        }

        return ans;
    }

    public static void main(String[] args){
        int[] nums = {1,2,2,1};
        int limit = 2;
        LeetCode_1674 solution = new LeetCode_1674();
        int ans = solution.minMoves(nums, limit);
        System.out.println(ans);
    }
}