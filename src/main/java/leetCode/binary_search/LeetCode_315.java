package leetCode.binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 *
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 *示例：
 *
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * */
public class LeetCode_315 {
    /**
     * 二分查找解法，sorted会存在插入排序。。
     * */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<Integer>();
        for(int i = nums.length - 1; i >= 0; i--){
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
            sorted.add(index, nums[i]);
        }

        return Arrays.asList(ans);
    }

    public int findIndex(List<Integer> sorted, int target){
        if(sorted.size() == 0){
            return 0;
        }

        int start = 0, end = sorted.size() - 1;
        if(sorted.get(start) > target){
            return 0;
        }

        if(sorted.get(end) < target){
            return end + 1;
        }

        while(start <= end){
            int mid = start + (end - start) / 2;
            if(sorted.get(mid) < target){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    public static void main(String[] args){
        int[] nums = {5,2,6,1,7,3};

        LeetCode_315 solution = new LeetCode_315();
        List<Integer> ans = solution.countSmaller(nums);
        System.out.println(ans);
    }
}
