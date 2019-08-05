package ex.jdk.debug.algorithm.ch31;

import java.util.Arrays;

/**
 * @Description 反复平方法求元素的幂
 * @auther liujx8
 * @create 2019-07-29 上午 08:25
 */
public class ModularExponentiation {
    /**
     * (a^b) % n
     * */
    public static int modularExponentition(int a, int b, int n){
        int c = 0, d = 1;

        // 计算b二进制数组
        String bBinaryStr = Integer.toBinaryString(b);
        char bBinary[] = bBinaryStr.toCharArray();
        System.out.println(Arrays.toString(bBinary));
        for(int i = 0; i < bBinary.length; i++)
        {
            c = 2 * c;
            d = (d * d) % n;
            //System.out.println(i + " i " + " i_value: " + bBinary[i] + " " + (bBinary[i] == '1'));
            if(bBinary[i] == '1'){
                c = c + 1;
                d = (d * a) % n;
            }
            System.out.println("d:"+d);
        }
        return d;
    }

    public static void main(String args[]){
        /*String result = Integer.toBinaryString(2);
        System.out.println(result);*/

        int a = 2;
        int b = 5;
        int n = 5;
        int res = modularExponentition(a, b, n);
        System.out.println(res);

        /*int a = 17;
        int n = 7;
        int r = a % n;
        System.out.println(r);*/
    }
}
