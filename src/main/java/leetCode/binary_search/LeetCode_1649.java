package leetCode.binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 * 1649. 通过指令创建有序数组
 * 给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。
 * 一开始你有一个空的数组 nums ，你需要 从左到右 遍历 instructions 中的元素，将它们
 * 依次插入 nums 数组中。每一次插入操作的 代价 是以下两者的 较小值 ：
 *
 * nums 中 严格小于  instructions[i] 的数字数目。
 * nums 中 严格大于  instructions[i] 的数字数目。
 * 比方说，如果要将 3 插入到 nums = [1,2,3,5] ，
 * 那么插入操作的 代价 为 min(2, 1) (元素 1 和  2 小于 3 ，元素 5 大于 3 ），
 * 插入后 nums 变成 [1,2,3,3,5] 。
 *
 * 请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。由于答案会很大，
 * 请将它对 10^9 + 7 取余 后返回。
 * */
public class LeetCode_1649 {
    public int createSortedArray(int[] instructions){
        int MOD = 1000000000 + 7;

        long ans = 0;
        int len = instructions.length;
        List<Integer> sorted = new ArrayList<Integer>();
        for(int i = 0; i < len; i++){
            int lessIndex = findLessIndex(sorted, instructions[i]);
            int moreIndex = findMoreIndex(sorted, instructions[i]);

            int minCount = lessIndex, maxCount = sorted.size() - moreIndex;

            ans += Math.min(minCount, maxCount);
            ans %= MOD;

            sorted.add(moreIndex, instructions[i]);
        }
        return (int) ans;
    }

    public int findMoreIndex(List<Integer> sorted, int target){
        int left = 0, right = sorted.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(sorted.get(mid) <= target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int findLessIndex(List<Integer> sorted, int target){
        int left = 0, right = sorted.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(sorted.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args){
        int[] nums = {1,2,3,6,5,4};
        LeetCode_1649 solution = new LeetCode_1649();
        int ans = solution.createSortedArray(nums);
        System.out.println(ans);
    }
}