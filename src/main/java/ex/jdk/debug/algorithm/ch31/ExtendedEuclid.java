package ex.jdk.debug.algorithm.ch31;

import java.util.Arrays;

/**
 * @Description
 * @auther liujx8
 * @create 2019-07-20 下午 02:23
 */
public class ExtendedEuclid {
    public static int[] extendedEuclid(int a, int b){
        int d, x, y;
        if(b == 0){
            d = a;x = 1;y = 0;
            return new int[]{d, x, y};
        }else{
            int _d, _x, _y;
            int t[] = extendedEuclid(b, a%b);
            _d = t[0]; _x = t[1]; _y = t[2];

            d = _d;
            x = _y;
            y = _x - a/b * _y;
            return new int[]{d, x, y};
        }
    }

    public static void main(String args[]){
        int arr[] = extendedEuclid(99, 78);
        System.out.println(Arrays.toString(arr));
    }
}
