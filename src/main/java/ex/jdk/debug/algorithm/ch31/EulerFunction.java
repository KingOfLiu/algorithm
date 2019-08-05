package ex.jdk.debug.algorithm.ch31;

/**
 * @Description
 * @auther liujx8
 * @create 2019-07-24 上午 10:39
 */
public class EulerFunction {
    /**
     * 欧拉函数是指：对于一个正整数 n ，小于 n 且和 n 互质的正整数（包括 1）的个数，记作 φ(n) 。
     * */
    public static int eulerFunction(int n){
        if(n == 0) return -1;

        int count = 0;
        for(int i = 1;i < n; i++){
            if((n % i) == 1){
                System.out.println(i);
                count ++;
            }
        }
        return count;
    }

    public static void main(String args[]){
        System.out.println("res:" + eulerFunction(45));
    }
}
