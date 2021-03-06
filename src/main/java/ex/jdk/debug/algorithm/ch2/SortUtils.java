package ex.jdk.debug.algorithm.ch2;

import edu.princeton.cs.introcs.StdOut;

public class SortUtils {
	/** 比较元素 */
	public static boolean less(Comparable v,Comparable w){
		return v.compareTo(w) < 0;
	}
	
	/** 交换元素 */
	public static void exch(Comparable[] a, int i ,int j) {
		Comparable t = a[i]; 
		a[i] = a[j]; 
		a[j] = t;
	}
	
	/** 显示数组元素 */
	public static void show(Comparable[] a) {
		for(int i = 0;i < a.length;i++) 
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for(int i = 1;i < a.length;i++)
			if(less(a[i], a[i-1])) return false;
		return true;
	}
}
