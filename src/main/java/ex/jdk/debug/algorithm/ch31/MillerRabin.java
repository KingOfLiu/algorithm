package ex.jdk.debug.algorithm.ch31;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description Miller-Rabin 素性测试
 * @auther liujx8
 * @create 2019-08-04 下午 08:50
 */
public class MillerRabin {
    private static Logger logger = LoggerFactory.getLogger(MillerRabin.class);

    /* 获取指定数字的最高位 */
    private static int getHightDigit(long n){
        int _hd = 0;
        long _n = n;
        while(_n > 0){
            _n = _n / 2;
            _hd ++;
        }
        return _hd;
    }

    /** 求出指定数字的二进制byte数组 */
    private static byte[] getBinary(int n){
        int h = getHightDigit(n);

        int _n = n;

        // 获取数字n的最高位
        byte[] binArr = new byte[h];

        int _base = 2;
        int i = binArr.length-1;
        while(_n > 0){
            binArr[i] = (byte) (_n % 2);
            i--;

            _n = _n / 2;
        }
        return binArr;
    }

    private static boolean witness(int a, int n){
        int[] arr = getBinaryAndOdd(n-1);
        int t = arr[0];
        int X[] = new int[t];
        X[0] = ModularExponentiation.modularExponentition(a, arr[1], n);
        for(int i = 1; i < t; i++){
            X[i] = (X[i - 1] * X[i - 1]) % n;
            logger.debug(Arrays.toString(X));
            if(X[i] == 1 && X[i - 1] != 1 && X[i - 1] != n-1){
                return true;
            }
        }

        if(X[t-1] != 1) return true;
        return false;
    }

    /**
     * 获取n-1 = (2^t)u的值
     * */
    private static int[] getBinaryAndOdd(int n){
        int t = 0;
        int u = 1;
        int _n = n;
        while(_n % 2 == 0){
            _n = _n / 2;
            t++;
        }
        u = _n;

        //logger.debug("2 ^ {} * {}", t, u);
        return new int[]{t, u};
    }

    public static String millerRabin(int n, int s){
        Random random = new Random();
        for(int j = 1; j < s;j++){
            int a = random.nextInt(9) + 1;
            if(witness(a, n)){
                return "COMPOSITE";
            }
        }
        return "PRIME";
    }

    public static void main(String args[]){
        String string = millerRabin(563, 15);
        logger.debug(string);
    }
}
