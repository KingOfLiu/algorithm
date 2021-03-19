package leetCode.binary_search;

import java.util.Arrays;

/**
 * 454. 四数相加 II
 *
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * */
public class LeetCode_454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D){
        int n = A.length, N = n * n;

        // 预处理处AB间、CD间任意两个数的和
        int[] AB = new int[N];
        int[] CD = new int[N];
        int cntAB = 0, cntCD = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                AB[cntAB++] = A[i] + B[j];
                CD[cntCD++] = C[i] + D[j];
            }
        }

        // 对预处理出的AB进行排序
        Arrays.sort(AB);

        // CD中的每个值通过二分查找其在AB中相反数的个数
        int ans = 0;
        for(int i = 0; i < cntCD; i++){
            // findIndex返回第一个小于等于待查找的数的位置
            int abIndex = findIndex(AB, -CD[i] + 1);
            int cdIndex = findIndex(AB, -CD[i]);

            ans += (abIndex - cdIndex);
        }
        return ans;
    }

    public int findIndex(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args){
        int[]A = { 1, 2};
        int[]B = {-2,-1};
        int[]C = {-1, 2};
        int[]D = {0, 2};

        LeetCode_454 solution = new LeetCode_454();
        int ans = solution.fourSumCount(A, B, C, D);
        System.out.println(ans);
    }
}
