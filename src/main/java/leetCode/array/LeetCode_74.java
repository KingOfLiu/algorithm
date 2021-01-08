package leetCode.array;

/**
 * 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * */
public class LeetCode_74 {
    public boolean searchMatrix(int[][] matrix, int target){
        int row = 0;
        while(row < matrix.length - 1){
            if(matrix[row][0] <= target){
                int[] rowArr = matrix[row];

                // 二分查找
                int left = 0;
                int right = rowArr.length - 1;
                while(left <= right){
                    int mid = (right + left) / 2;
                    if(rowArr[mid] == target){
                        return true;
                    } else if(rowArr[mid] < target){
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            row++;
        }

        return false;
    }

    public static void main(String[] args){
        int[][] matrix = {{1}};
        int target = 1;
        LeetCode_74 solution = new LeetCode_74();
        boolean ans = solution.searchMatrix(matrix, target);
        System.out.println(ans);
    }
}
