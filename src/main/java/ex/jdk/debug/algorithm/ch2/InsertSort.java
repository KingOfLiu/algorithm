package ex.jdk.debug.algorithm.ch2;

public class InsertSort {
	static void print(int[] arr) {
		for(int i = 0;i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	static void insertSort(int[] arr) {
		for(int i = 0;i < arr.length; i++) {
			int num = arr[i];
			for(int j = 0; j < arr.length; j++) {
				if(arr[j] > num) {
					exchange(arr, i, j);
				}
			}
		}
	}
	
	static void exchange(int[] arr, int x, int y) {
//		int temp = arr[x];
//		arr[x] = arr[y];
//		arr[y] = temp;
		
		int temp = arr[x]^arr[y];
		arr[x] = temp^arr[x];
		arr[y] = temp^arr[y];
	}
	
	public static void main(String[] args) {
		int arr[] = {6,5,3,1,8,7,2,4};
		print(arr);
		insertSort(arr);
		print(arr);
	}
}
