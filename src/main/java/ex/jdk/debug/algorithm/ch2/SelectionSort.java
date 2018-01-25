package ex.jdk.debug.algorithm.ch2;

public class SelectionSort {
	public static void sort(Comparable[] a) {
		int length = a.length;
		for(int i = 0; i < length; i++) {
			int min = i;
			for(int j = i + 1;j < length;j++) {
				if(SortUtils.less(a[j], a[min])) min = j;
				SortUtils.exch(a,j,min);
			}
		}
	}
	
	public static void main(String args[]) {
		//String[] a = In.readStrings();
		String[] a = {"S","O","R","T","E","X","A","N","M","P","L","E"};
		sort(a);
		assert SortUtils.isSorted(a);
		SortUtils.show(a);
	}
}
