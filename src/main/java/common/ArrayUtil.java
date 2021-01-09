package common;

/**
 * 数组工具类
 * */
public class ArrayUtil {
    public static void printArray(int[][] nums){
        if(nums == null){
            return;
        }
        for(int i = 0; i < nums.length; i++){
            int[] row = nums[i];
            for(int j = 0; j < row.length; j++){
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }
}
