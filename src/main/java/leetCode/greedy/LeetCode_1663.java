package leetCode.greedy;

/**
 * 1663. 具有给定数值的最小字符串
 * */
public class LeetCode_1663 {
    public String getSmallestString(int n, int k) {
        char[] chars = new char[n];
        for(int i = 0; i < n; i++){
            chars[i] = 'a';
        }
        k -= n;

        int lastIndex = n - 1;
        while(k > 25){
            chars[lastIndex] += 25;
            k -= 25;
            lastIndex -= 1;
        }

        chars[lastIndex] += k;
        return new String(chars);
    }

    public static void main(String[] args){
        int n = 3, k = 50;
        LeetCode_1663 solution = new LeetCode_1663();
        String ans = solution.getSmallestString(n, k);
        System.out.println(ans);
    }
}
