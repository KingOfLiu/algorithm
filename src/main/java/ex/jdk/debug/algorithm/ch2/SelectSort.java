package ex.jdk.debug.algorithm.ch2;

/**
 * 实现：
 * 1.找到数组中最小的元素
 * 2.将它和第一个元素交换位置（如果第一个元素就是最小元素那么它和自己交换）
 * 3.在剩余的元素中找到最小的元素，将它和第二个元素交换位置
 * 
 * i min 0 1 2 3 4 5 6 7 8 9
 *       9 1 2 5 7 4 8 6 3 5
 *
 * 0  1  9 1 2 5 7 4 8 6 3 5
 * 1  2  1 9 2 5 7 4 8 6 3 5
 * 2  8  1 2 9 5 7 4 8 6 3 5
 * 3  5  1 2 3 5 7 4 8 6 9 5
 * 4  5  1 2 3 4 7 5 8 6 9 5
 * 5  5  1 2 3 4 5 7 8 6 9 5
 * 6  9  1 2 3 4 5 5 8 6 9 7
 * 7  7  1 2 3 4 5 5 6 8 9 7
 * 8  9  1 2 3 4 5 5 6 7 9 8
 * 9  9  1 2 3 4 5 5 6 7 8 9
 * */
public class SelectSort {
	static void selectSort(int[] arr) {
		int length = arr.length;
		for(int i = 0; i < length; i++) {
			int min = arr[i];
			
			for(int j = i+1; j < length; j++) {
				if(arr[j] < min) {
					// 找到最小元素
					int temp = min;
					
					// 将最小元素与第i元素交换位置
					min = arr[j]; 
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		printArray(arr);
	}
	
	private static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = {9,1,2,5,7,4,8,6,3,5};
		selectSort(arr);
	}
}
