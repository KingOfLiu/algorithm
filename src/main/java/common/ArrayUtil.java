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

    public static void printArray(boolean[] nums){
        printArray(nums, true);
    }

    public static void printArray(boolean[] nums, boolean lienFeed){
        if(nums == null || nums.length == 0){
            return;
        }

        for(boolean b : nums){
            System.out.print(b + " ");
        }

        if(lienFeed){
            System.out.println();
        }
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

    public static String arrayToString(int[] nums){
        StringBuffer res = new StringBuffer();
        for(int num : nums){
            res.append(num).append(" ");
        }
        return res.toString();
    }
}
