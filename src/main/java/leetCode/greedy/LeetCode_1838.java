package leetCode.greedy;

import java.util.Arrays;

/**
 * 1838. 最高频元素的频数
 * */
public class LeetCode_1838 {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int j = 0;
        int ans = 1, sum = 0, maxFrequency = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            maxFrequency = nums[i] * (i - j + 1);
            while(j < n && maxFrequency - sum > k){
                sum -= nums[j];
                maxFrequency -= nums[i];
                j++;
            }
            ans = Math.max(ans, (i - j + 1));
        }

        return ans;
    }

    public static void main(String[] args){
        /*int[] nums = {1,4,8,13};
        int k = 5;*/
        int[] nums = {3,9,6};
        int k = 2;

        /*int[] nums = {9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966};
        int k = 3056;
*/
        LeetCode_1838 solution = new LeetCode_1838();
        int ans = solution.maxFrequency(nums, k);
        System.out.println(ans);
    }
}
