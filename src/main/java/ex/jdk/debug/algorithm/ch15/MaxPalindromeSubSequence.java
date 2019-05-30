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
     * */
    public static int maxPalindromeSubSequence(String sequence){
        logger.info("sequence : {}", sequence);
        if(null == sequence || "".equals(sequence) || sequence.length() == 0){
            return -1;
        }

        int len = sequence.length();

        int [][] dp = new int[len][len];
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

        //printArray(dp);

        return dp[0][len-1];
    }

    private static void printArray(int [][] dp){
        for(int i = 0;i < dp.length; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    public static void main(String args[]){
        String sequence = "character";
        int length = maxPalindromeSubSequence(sequence);
        System.out.println(length);
    }
}
