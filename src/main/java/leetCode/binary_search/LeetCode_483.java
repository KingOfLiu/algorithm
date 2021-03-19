package leetCode.binary_search;

/**
 * 483. 最小好进制
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，
 * 则称 k（k>=2）是 n 的一个好进制。
 *
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 *
 *
 * 示例 1：
 * 输入："13"
 * 输出："3"
 * 解释：13 的 3 进制是 111。
 *
 * 示例 2：
 *
 * 输入："4681"
 * 输出："8"
 * 解释：4681 的 8 进制是 11111。
 * */
public class LeetCode_483 {
    public String smallestGoodBase(String n){
        // 将字符串解析成long型数据
        long num = Long.parseLong(n);

        // 对m从大到小进行遍历（m的含义是转换为k进制后1的个数）
        for(int m = (int) (Math.log(num + 1) / Math.log(2)); m >= 2; m--){
            // 利用二分法搜索对应的k(k的含义是k进制)
            long left = 2, right = (long) Math.pow(num, 1.0/(m-1)) + 1;
            while(left < right){
                long mid = left + (right - left) / 2;
                long sum = getSum(m, mid);
                if(sum == num){
                    System.out.println(m );
                    return String.valueOf(mid);
                } else if(sum < num){
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return String.valueOf(num - 1);
    }

    // 等比数列求和
    public long getSum(int m, long mid){
        long sum = 0;
        for(int j = 0; j < m; j++){
            sum = sum * mid + 1;
        }
        return sum;
    }

    public static void main(String[] args){
        String n = "4681";
        LeetCode_483 solution = new LeetCode_483();
        String ans = solution.smallestGoodBase(n);
        System.out.println(ans);
    }
}