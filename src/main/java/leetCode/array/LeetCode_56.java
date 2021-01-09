package leetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 * */
public class LeetCode_56 {
    public int[][] marge(int[][] intervals){
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o, int[] m) {
                return o[0] - m[0];
            }
        });

        //printArray(intervals);

        List<int[]> merged = new ArrayList<int[]>();
        for(int i = 0; i < intervals.length; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < left){
                merged.add(new int[]{left, right});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args){
        int[][] nums = {
                {1, 9}, {2, 5}, {19, 20},
                {10, 11}, {12, 20}, {0, 3},
                {0, 1}, {0, 2}
        };

        LeetCode_56 solution = new LeetCode_56();
        int[][] ans = solution.marge(nums);
        printArray(ans);
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
}
