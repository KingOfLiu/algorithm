package leetCode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. 连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 *
 * 提示：
 *  1 <= nums.length <= 10^5
 *  nums[i] 不是 0 就是 1
 * */
public class LeetCode_525 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int counter = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);

        int ans = 0;
        for(int i = 0; i < n; i++){
            int num = nums[i];
            if(num == 1){
                counter ++;
            } else {
                counter --;
            }

            if(map.containsKey(counter)){
                int prevIndex = map.get(counter);
                ans = Math.max(ans, (i - prevIndex));
            } else {
                map.put(counter, i);
            }
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {0,0,1,0,0,0,1,1};
        LeetCode_525 solution = new LeetCode_525();
        int ans = solution.findMaxLength(nums);
        System.out.println(ans);
    }
}
