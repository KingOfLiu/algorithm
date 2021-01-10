package common;

/**
 * 数组工具类
 * */
public class ArrayUtil {
    public static void printArray(int[] nums, boolean lineFeed){
        if(nums == null){
            return;
        }
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }

        if(lineFeed){
            System.out.println();
        }
    }

    public static void printArray(int[] nums){
        printArray(nums, true);
    }

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

    public static void printArray(char[][] nums){
        if(nums == null){
            return;
        }
        for(int i = 0; i < nums.length; i++){
            char[] row = nums[i];
            for(int j = 0; j < row.length; j++){
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }
}
