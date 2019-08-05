package ex.jdk.debug.algorithm.ch31;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

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
    private static byte[] getBinary(long n){
        int h = getHightDigit(n);

        long _n = n;

        // 获取数字n的最高位
        byte[] binArr = new byte[h];

        long _base = 2;
        int i = binArr.length-1;
        while(_n > 0){
            binArr[i] = (byte) (_n % 2);
            i--;

            _n = _n / 2;
        }
        return binArr;
    }

    public static void main(String args[]){
        byte[] arr = getBinary(2L);
        logger.debug(Arrays.toString(arr));
    }
}
