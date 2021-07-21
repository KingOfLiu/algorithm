package codeforces.problemset.constructive_algorithms;

import java.util.Scanner;

/**
 * B. Nastia and a Good Array
 * link: https://codeforces.com/problemset/problem/1521/B
 * */
public class Div_2_720_B {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];

            int x = (int) 1e9 + 7, pos = -1;
            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();
                if(a[i] < x){
                    x = a[i];
                    pos = i;
                }
            }

            System.out.println(n - 1);
            for(int i = 0; i < n; i++){
                if(i == pos){
                    continue;
                }

                System.out.println((pos + 1) + " " + (i + 1) + " " + x + " " + (x + Math.abs(i - pos)) + " ");
            }
        }
    }
}
