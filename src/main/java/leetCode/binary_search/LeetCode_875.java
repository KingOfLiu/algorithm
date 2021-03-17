package leetCode.binary_search;

/**
 * 875.爱吃香蕉的珂珂
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * */
public class LeetCode_875 {
    public int minEatingSpeed(int[] piles, int h) {
        int len = piles.length;
        int left = 1, right = 1000000000;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(!check(piles, mid, h)){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int speed, int h){
        int cnt = 0;
        for(int n : nums){
            // 因为如果吃了k根香蕉后，如果还有剩余，是要算一次吃的，次数要+1
            // 就相当于对n除以speed向上取整。
            // 代码里对除法向上取整相当于除数+被除数-1 即等价于： cnt += (n - 1) / speed + 1;
            cnt += n / speed + (n % speed == 0 ? 0 : 1);
        }
        return cnt <= h;
    }

    public static void main(String[] args){
        int[] nums = {1000000000};
        int h = 2;
        LeetCode_875 solution = new LeetCode_875();
        int ans = solution.minEatingSpeed(nums, h);
        System.out.println(ans);
    }
}
