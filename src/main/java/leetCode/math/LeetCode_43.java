package leetCode.math;

/**
 * 43. 字符串相乘
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，
 * 返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * */
public class LeetCode_43 {
    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }

        char[] chs1 = num1.toCharArray(), chs2 = num2.toCharArray();
        int n1 = chs1.length, n2 = chs2.length;

        int dLen = n1 + n2;
        int[] data = new int[dLen];
        for(int j = n2 - 1; j >= 0; j--){
            int c2 = chs2[j] - '0';
            for(int i = n1 - 1; i >= 0; i--){
                int c1 = chs1[i] - '0';
                data[j + i + 1] += c1 * c2;
            }
        }

        int carry = 0;
        StringBuffer ans = new StringBuffer();
        for(int i = data.length - 1; i >= 0; i--){
            int curVal = data[i] + carry;
            ans.insert(0, curVal % 10);
            carry = curVal / 10;
        }

        String str = ans.toString();
        if(str.startsWith("0")){
            int i = 0;
            while(i < str.length() && str.charAt(i) == '0'){
                i++;
            }
            str = str.substring(i);
        }
        return str;
    }

    public static void main(String[] args){
        String num1 = "1", num2 = "1000000000000000000000";
        LeetCode_43 solution = new LeetCode_43();
        String ans = solution.multiply(num1, num2);
        System.out.println(ans);
    }
}
