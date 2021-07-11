package leetCode_contest.weekly_contest_248;

/**
 * 1922. 统计好数字的数目
 * 我们称一个数字字符串是 好数字 当它满足（下标从 0 开始）偶数
 * 下标处的数字为 偶数 且 奇数 下标处的数字为 质数 （2，3，5
 * 或 7）。
 *      比方说，"2582" 是好数字，因为偶数下标处的数字（2 和 8）是偶数且奇数下标处的数字（5 和 2）为质数。但 "3245" 不是 好数字，因为 3 在偶数下标处但不是偶数。
 * 给你一个整数 n ，请你返回长度为 n 且为好数字的数字字符串
 * 总数 。由于答案可能会很大，请你将它对 10^9 + 7 取余后返回。
 * 一个 数字字符串 是每一位都由 0 到 9 组成的字符串，且可能包
 * 含前导 0 。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：5
 * 解释：长度为 1 的好数字包括 "0"，"2"，"4"，"6"，"8" 。
 *
 * 示例 2：
 * 输入：n = 4
 * 输出：400
 *
 * 示例 3：
 * 输入：n = 50
 * 输出：564908303
 *
 * 提示：
 *  - 1 <= n <= 10^15
 * */
public class Contest_1922 {
    int mod = (int) (1e9 + 7);
    public int countGoodNumbers(long n) {
        long even = n / 2;
        long odd = ((n & 1) == 0) ? n / 2 : (n + 1) / 2;

        long ans1 = pow(4, even);
        long ans2 = pow(5, odd);
        long ans = ans1 * ans2;
        return (int)(ans % mod);
    }

    long pow(long a, long b){
        if(b == 0){
            return 1;
        }

        long ans = 1;
        while(b > 0){
            if((b & 1) != 0){
                ans = ans * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }

        return ans;
    }

    public static void main(String[] args){
        long n = 50;
        Contest_1922 solution = new Contest_1922();
        int ans = solution.countGoodNumbers(n);
        System.out.println(ans);
    }
}