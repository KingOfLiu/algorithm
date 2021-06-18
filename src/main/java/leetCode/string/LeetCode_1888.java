package leetCode.string;

/**
 * 1888. 使二进制字符串字符交替的最少反转次数
 * 给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：
 *  类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
 *  类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
 * 请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。
 * 我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。
 *  比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。
 *
 *
 * 示例 1：
 * 输入：s = "111000"
 * 输出：2
 * 解释：执行第一种操作两次，得到 s = "100011" 。
 * 然后对第三个和第六个字符执行第二种操作，得到 s = "101010" 。
 *
 * 示例 2：
 * 输入：s = "010"
 * 输出：0
 * 解释：字符串已经是交替的。
 *
 * 示例 3：
 * 输入：s = "1110"
 * 输出：1
 * 解释：对第二个字符执行第二种操作，得到 s = "1010" 。
 *
 *
 * 提示：
 *  1 <= s.length <= 10^5
 *  s[i] 要么是 '0' ，要么是 '1' 。
 * */
public class LeetCode_1888 {
    public int minFlips(String s) {
        int len = s.length();
        char[] target = {'0', '1'};
        int cnt = 0;
        for(int i = 0; i < len; i++){
            cnt += (s.charAt(i) != target[i % 2] ? 1 : 0);
        }

        int ans = Math.min(cnt, len - cnt);
        for(int i = 0; i < len; i++){
            cnt -= (s.charAt(i) != target[i % 2] ? 1 : 0);
            cnt += (s.charAt(i) != target[(i + len) % 2]) ? 1 : 0;
            ans = Math.min(ans, cnt);
            ans = Math.min(ans, len - cnt);
        }
        return ans;
    }

    public static void main(String[] args){
        String s = "111000";
        LeetCode_1888 solution = new LeetCode_1888();
        int ans = solution.minFlips(s);
        System.out.println(ans);
    }
}