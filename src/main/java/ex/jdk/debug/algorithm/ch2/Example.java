package ex.jdk.debug.algorithm.ch2;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Example {
	public static void sort(Comparable[] a) {
		/** 请见算法2.1、算法2.2、算法2.3、算法2.4、算法2.5、或者算法2.7*/
	}
	
	/** 比较元素 */
	private static boolean less(Comparable v,Comparable w){
		return v.compareTo(w) < 0;
	}
	
	/** 交换元素 */
	private static void exch(Comparable[] a, int i ,int j) {
		Comparable t = a[i]; 
		a[i] = a[j]; 
		a[j] = t;
	}
	
	/** 显示数组元素 */
	private static void show(Comparable[] a) {
		for(int i = 0;i < a.length;i++) 
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for(int i = 1;i < a.length;i++)
			if(less(a[i], a[i-1])) return false;
		return true;
	}
	
	public static void main(String args[]) {
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
