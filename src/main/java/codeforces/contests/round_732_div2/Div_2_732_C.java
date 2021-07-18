package codeforces.contests.round_732_div2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * C. AquaMoon and Strange Sort
 *
 * link: https://codeforces.com/contest/1546/problem/C
 * */
public class Div_2_732_C {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();

            int[][] cnt = new int[100001][2];
            int[] a = new int[n];
            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();
                cnt[a[i]][i % 2]++;
            }

            Arrays.sort(a);
            for(int i = 0; i < n; i++){
                cnt[a[i]][i % 2]--;
            }

            boolean flag = true;
            for(int i = 0; i < n; i++){
                if(cnt[a[i]][0] != 0 || cnt[a[i]][1] != 0){
                    System.out.println("NO");
                    flag = false;
                    break;
                }
            }

            if(flag){
                System.out.println("YES");
            }
        }
    }
}
