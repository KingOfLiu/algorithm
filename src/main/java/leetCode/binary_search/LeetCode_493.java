package leetCode.binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 *
 * */
public class LeetCode_493 {
    public int reversePairs(int[] nums){
        int len = nums.length;
        List<Integer> sorted = new ArrayList<Integer>();
        int ans = 0;
        for(int i = len - 1; i >= 0; i--){
            // 第一次二分查找找到的是逆序对的数量
            int index = findIndex(true, sorted, nums[i]);
            ans += index;

            // 第二次二分查找找到的是有序数组的插入位置
            index = findIndex(false, sorted, nums[i]);
            sorted.add(index, nums[i]);
        }
        return ans;
    }

    public int findIndex(boolean flag, List<Integer> sorted, int target){
        int len = sorted.size();
        if(len == 0){
            return 0;
        }

        int start = 0, end = len - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            long k = sorted.get(mid);
            if(flag){
                k = k * 2;
            }
            if(k < target){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public static void main(String[] args){
        int[] nums = {2,4,3,5,1};
        LeetCode_493 solution = new LeetCode_493();
        int ans = solution.reversePairs(nums);
        System.out.println(ans);
    }
}
