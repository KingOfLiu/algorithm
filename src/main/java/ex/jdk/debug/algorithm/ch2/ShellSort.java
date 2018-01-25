package ex.jdk.debug.algorithm.ch2;

public class ShellSort {
	static void swap(Comparable[] a, int x, int y) {
		Comparable temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	/**
	 * a < b 返回true
	 * */
	static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	static void shellSort(Comparable[] a) {
		for(int grap = a.length/2; grap > 0; grap/=2) {
			for(int i = grap; i < a.length; i++) {
				for(int j = i; (j > 0 && less(a[j], a[j-1])); j--) {
					swap(a, j, j - 1);
				}
			}
		}
	}
	
	static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		String a[] = {"L","E","E","A","M","H","L","E","P","S","O","L","T","S","X","R"};
		show(a);
		shellSort(a);
		show(a);
	}
}
