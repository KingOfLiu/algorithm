package leetCode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1852. Distinct Numbers in Each Subarray
 *
 * Given an integer array nums and an integer k,
 * you are asked to construct the array ans of size n-k+1
 * where ans[i] is the number of distinct numbers in the subarray
 * nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]].
 * Return the array ans.
 *
 * Example 1:
 * Input: nums = [1,2,3,2,2,1,3], k = 3
 * Output: [3,2,2,2,3]
 * Explanation: The number of distinct elements in each subarray goes as follows:
 * - nums[0:2] = [1,2,3] so ans[0] = 3
 * - nums[1:3] = [2,3,2] so ans[1] = 2
 * - nums[2:4] = [3,2,2] so ans[2] = 2
 * - nums[3:5] = [2,2,1] so ans[3] = 2
 * - nums[4:6] = [2,1,3] so ans[4] = 3
 *
 * Example 2:
 * Input: nums = [1,1,1,1,2,3,4], k = 4
 * Output: [1,2,3,4]
 * Explanation: The number of distinct elements in each subarray goes as follows:
 * - nums[0:3] = [1,1,1,1] so ans[0] = 1
 * - nums[1:4] = [1,1,1,2] so ans[1] = 2
 * - nums[2:5] = [1,1,2,3] so ans[2] = 3
 * - nums[3:6] = [1,2,3,4] so ans[3] = 4
 *
 *
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * */
public class LeetCode_1852 {
    public int[] distinctNumbers(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ans = new int[n - k + 1];
        for(int i = 0; i < k; i++){
            int cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++cnt);
        }
        ans[0] = map.size();
        int j = 0;
        for(int i = k; i < n; i++){
            int cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++cnt);

            int delCnt = map.getOrDefault(nums[j], 0);
            map.put(nums[j], --delCnt);

            if(delCnt == 0){
                map.remove(nums[j]);
            }
            //System.out.println(j + " # " + i + " # " + map);
            ans[++j] = map.size();
        }

        return ans;
    }

    public static void main(String[] args){
        int[] nums = {1,2,3,2,2,1,3};
        int k = 7;
        LeetCode_1852 solution = new LeetCode_1852();
        int[] ans = solution.distinctNumbers(nums, k);
        for(int n : ans){
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
