package ex.jdk.debug.algorithm.ch31;

/**
 * @Description
 * @auther liujx8
 * @create 2019-07-20 下午 03:11
 */
public class ModularLinearEquationSolver {
    public static void modularLinearEquationSolver(int a, int b, int n){
        int d, _x, _y;
        int t[] = ExtendedEuclid.extendedEuclid(a, n);
        d = t[0]; _x = t[1]; _y = t[2];
        if(b % d == 0){
            int x_0 = (_x * (b/d)) % n;
            for(int i=0; i <= d - 1; i++){
                int s = (x_0 + i * (n/d)) % n;
                System.out.println(s);
            }
        }else{
            System.out.println("no solutions");
        }
    }

    public static void main(String args[]){
        modularLinearEquationSolver(35, 10, 50);
    }
}
