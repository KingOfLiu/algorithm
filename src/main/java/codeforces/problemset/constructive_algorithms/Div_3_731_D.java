package codeforces.problemset.constructive_algorithms;

import java.util.Scanner;

/**
 * D. Co-growing Sequence
 * link: https://codeforces.com/problemset/problem/1547/D
 * */
public class Div_3_731_D {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] x = new int[n];
            for(int i = 0; i < n; i++){
                x[i] = sc.nextInt();
            }

            int[] ans = new int[n];
            System.out.print(ans[0] + " ");
            for(int i = 1; i < n; i++){
                ans[i] = (ans[i - 1] ^ x[i - 1]) & ~x[i];
                System.out.print(ans[i] + " ");
            }
            System.out.println();
        }
    }
}
