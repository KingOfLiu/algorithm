package leetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 输入：nums = []
 * 输出：[]
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 * */
public class LeetCode_15 {
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len == 0){
            return ans;
        }

        Arrays.sort(nums);
        for(int i = 0; i < len; i++){
            // 如果当前元素大于0，后面的元素也一定大于0，因为数组有序，所以直接退出循环
            if(nums[i] > 0){
                break;
            }

            // 去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }



            int start = i + 1, end = len - 1;
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i], nums[start], nums[end]));

                    // 去重
                    while(start < end && nums[start] == nums[start + 1]){ start++; }
                    while(start < end && nums[end] == nums[end - 1]){ end--; }

                    start++;
                    end--;
                } else if(sum < 0){
                    start ++;
                } else {
                    end--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = new int[]{-1,0,1, 1,2,-1,-4};
        LeetCode_15 solution = new LeetCode_15();
        List ans = solution.threeSum(nums);
        System.out.println(ans);
    }
}
