package ex.jdk.debug.algorithm.ch15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Description 最长回文子序列
 * @auther LJX
 * @create 2019-05-25 下午 03:15
 */
public class MaxPalindromeSubSequence {
    private static Logger logger = LoggerFactory.getLogger(MaxPalindromeSubSequence.class);

    /**
     * 获取最长回文子序列长度
    */
    public static int maxPalindromeSubSequenceLength(String sequence){
        logger.info("sequence : {}", sequence);
        if(null == sequence || "".equals(sequence) || sequence.length() == 0){
            return -1;
        }

        int len = sequence.length();

        int [][] dp = new int[len][len];
        char [][] s = new char[len][len];

        for(int i = 0;i < len;i++){
            for(int j = 0;j < len;j ++){
                s[i][j] = '*';
            }
        }

        for(int i = len - 1; i >= 0; i--){
            dp[i][i] = 1;
            for(int j = i + 1; j <= len - 1; j++){
                if(sequence.charAt(i) == sequence.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    logger.info("i = {}, j = {} sequence[i] = {}",i ,j, sequence.charAt(i));
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len-1];
    }

    /** 最长回文子序列 */
    public static String maxPalindromeSubSequence(String sequence){
        if(null == sequence || "".equals(sequence) || sequence.length() == 0){
            return null;
        }

        int len = sequence.length();
        int dp[][] = new int[len][len];

        for(int i = len - 1; i >= 0; i--){
            dp[i][i] = 1;
            for(int j = i + 1; j <= len - 1; j++){
                if(sequence.charAt(i) == sequence.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        int mLength = dp[0][len - 1];
        char res[] = new char[mLength];

        int i = 0, j = len - 1;
        int m = 0, n = mLength - 1;
        int index = mLength - 1;
        while(index >= 0){
            if(i < len && dp[i][j] == dp[i + 1][j]){
                i++;
            }else if(j >= 0 && dp[i][j] == dp[i][j - 1]){
                j--;
            }else{
                res[m] = sequence.charAt(i);
                res[n] = sequence.charAt(j);

                m++;
                n--;
                i++;
                j--;
                index = index - 2;
            }
        }
        return new String(res);
    }

    private static void printArray(char [][] s){
        for(int i = 0; i < s.length; i++){
            System.out.println(s[i]);
        }
    }

    private static void printArray(int [][] dp){
        for(int i = 0;i < dp.length; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
    }


    /** ####################################### */
    /** ########## 获取最长回文子序列 ########### */
    public static void maxPalindromeSubSequencePrint(String str){
        int dp[][] = new int[str.length()][str.length()];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp.length; j++){
                dp[i][j] = getResult(str.toCharArray(), i, j);
            }
        }

        char[] res = new char[dp[0][str.length()-1]]; // 回文序列长度
        int m = 0;
        int n = res.length - 1;

        int i = 0;
        int j = str.length() - 1;
        int index = res.length - 1;
        while(index >= 0){
            if(i < str.length() && dp[i][j] == dp[i + 1][j]){
                i++;
            }else if(j >= 0 && dp[i][j] == dp[i][j - 1]){
                j--;
            }else{
                res[m] = str.charAt(i);
                res[n] = str.charAt(j);
                i++;
                j--;
                m++;
                n--;
                index = index - 2;
            }
        }

        logger.debug("i = {}, j = {}, m = {}, n = {}, index = {}, count = {}", i, j, m, n, index);

        System.out.println(String.valueOf(res));
    }

    /**
     * 递归方式获取最长回文子序列长度
     * */
    public static int getResult(char[] con, int i, int j){
        if(i == j){
            return 1;
        }

        if(i > j){
            return 0;
        }

        if(con[i] == con[j]){
            return getResult(con, i + 1, j - 1) + 2;
        }else{
            return Math.max(getResult(con, i + 1, j), getResult(con,i, j - 1));
        }
    }

    public static void main(String args[]){
        //String sequence = "accca";
        String sequence = "character";
        //String sequence = "aibohphobia";

        //int length = maxPalindromeSubSequence(sequence);
        //System.out.println(length);
        String string = maxPalindromeSubSequence(sequence);
        System.out.println(string);
    }
}
