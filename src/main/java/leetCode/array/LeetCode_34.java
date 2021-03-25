package leetCode.array;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * */
public class LeetCode_34 {
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int[] ans = new int[]{-1, -1};

        int startIndex = findStartIndex(nums, target);
        int endIndex = findEndIndex(nums, target);
        if(startIndex == endIndex){
            return ans;
        }

        ans[0] = startIndex;
        ans[1] = endIndex - 1;
        return ans;
    }

    int findStartIndex(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if((nums[mid] < target)){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    int findEndIndex(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args){
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        LeetCode_34 solution = new LeetCode_34();
        int[] ans = solution.searchRange(nums, target);
        System.out.println(ans[0] + " - " + ans[1]);
    }
}
