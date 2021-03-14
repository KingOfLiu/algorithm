package leetCode.binary_search;

/**
 * 878. 第 N 个神奇数字
 *
 * 如果正整数可以被 A 或 B 整除，那么它是神奇的。
 *
 * 返回第 N 个神奇数字。由于答案可能非常大，返回它模 10^9 + 7 的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：N = 1, A = 2, B = 3
 * 输出：2
 * 示例 2：
 *
 * 输入：N = 4, A = 2, B = 3
 * 输出：6
 * 示例 3：
 *
 * 输入：N = 5, A = 2, B = 4
 * 输出：10
 * */
public class LeetCode_878 {
    public int nthMagicalNumber(int n, int a, int b){
        int MOD = 1000000000 + 7;
        int L = a / gcd(a, b) * b;
        long left = 0, right = (long) 1e15;
        while(left < right){
            long mid = left + (right - left) / 2;
            if(mid/a + mid/b - mid/L < n){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return (int) (left % MOD);
    }

    public int gcd(int x, int y){
        if(x == 0){
            return y;
        }
        return gcd(y % x, x);
    }

    public static void main(String[] args){
        LeetCode_878 solution = new LeetCode_878();
        //1000000000
        //40000
        //40000
        int ans = solution.nthMagicalNumber(1000000000, 40000, 40000);
        System.out.println(ans);
    }
}
