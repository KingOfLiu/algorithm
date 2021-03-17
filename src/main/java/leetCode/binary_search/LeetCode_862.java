package leetCode.binary_search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 862. 和至少为 K 的最短子数组
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 *
 * 示例 1：
 *
 * 输入：A = [1], K = 1
 * 输出：1
 *
 * 示例 2：
 *
 * 输入：A = [1,2], K = 4
 * 输出：-1
 *
 * 示例 3：
 *
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 * */
public class LeetCode_862 {
    public int shortestSubarray(int[] A, int k){
        if(A.length == 0){
            return -1;
        }

        int ans = Integer.MAX_VALUE;
        ArrayList<int[]> stack = new ArrayList<int[]>();
        stack.add(new int[]{0, -1});

        int sum = 0;
        for(int i = 0; i < A.length; i++){
            sum += A[i];

            while(stack.size() > 0 && stack.get(stack.size() - 1)[0] >= sum){
                stack.remove(stack.size() - 1);
            }

            int idx = binarySearch(stack, sum - k);
            System.out.println(i + " # " + idx);
            if(idx > 0){
                ans = Math.min(ans, i - stack.get(idx - 1)[1]);
            }
            stack.add(stack.size(), new int[]{sum, i});
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int binarySearch(ArrayList<int[]> stack, int target){
        int left = 0, right = stack.size();
        while(left < right){
            int mid = left + (right - left) / 2;
            if(stack.get(mid)[0] > target){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args){
        int[] arr = {48,99,37,4,-31};
        int k = 140;
        LeetCode_862 solution = new LeetCode_862();
        int ans = solution.shortestSubarray(arr, k);
        System.out.println(ans);
    }
}
