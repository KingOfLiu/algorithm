package ex.jdk.debug.algorithm.ch2.practice;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class P_2_1_25 {
	private static void show(Comparable[] a) {
		int length = a.length;
		for(int i = 0; i < length;i++) {
			StdOut.print(a[i] + " ");
		}
		System.out.println();
	}
	
	private static void exchange(Comparable[] a,int x, int y) {
		Comparable temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	private static void sort1(Comparable[] a) {
		int length = a.length;
		for(int i = 1; i < length;i++) {
			for(int j = i; j > 0 && less(a[j],a[j-1]);j--) {
				exchange(a, j, j-1);
			}
		}
	}
	
	private static void sort2(Comparable[] a) {
		int length = a.length;
		
		for(int i = 1; i < length; i++) {
			Comparable t = a[i - 1];
			for(int j = i; j > 0 && less(a[j],a[j-1]);j--) {
				//exchange(a, j, j-1);
				a[j-1] = a[j];
				//a[j] = t;
			} 
			show(a);
		}
	}
	
	public static void main(String[] args) {
		//String[] a = {"S","O","A","T","L","J","X"};
		//show(a);
		//sort2(a);
		//show(a);
		// Aa Bb Cc Dd Ee Ff Gg Hh Ii Jj Kk Ll Mm Nn Oo Pp Qq Rr Ss Tt Uu Vv Ww Xx Yy Zz
	}
}
