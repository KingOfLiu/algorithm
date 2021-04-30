package leetCode.differential_array;

/**
 * 370. 区间加法
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k​​​​​​​ 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：
 * [startIndex, endIndex, inc]，
 * 你需要将子数组 A[startIndex ... endIndex]
 * （包括 startIndex 和 endIndex）增加 inc。
 *
 * 请你返回 k 次操作后的数组。
 *
 * 示例:
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 *
 * 解释:
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 * */
public class LeetCode_370 {
    public int[] getModifiedArray(int length, int[][] updates) {
        /*int[] ans = new int[length];
        for(int[] u : updates){
            int start = u[0];
            int end = u[1];
            int inc = u[2];
            for(int i = start; i <= end; i++){
                ans[i] += inc;
            }
        }
        return ans;*/

        // 差分优化

        int[] diff = new int[length];
        for(int i = 0; i < updates.length; i++){
            int start = updates[i][0];
            int end = updates[i][1];
            int inc = updates[i][2];

            diff[start] += inc;
            if(end + 1 < length){
                diff[end + 1] -= inc;
            }
        }

        for(int i = 1; i < length; i++){
            diff[i] += diff[i - 1];
        }

        return diff;
    }

    public static void main(String[] args){
        int length = 5;
        int[][] update = {{1,3,2},{2,4,3},{0,2,-2}};
        /*int length = 1;
        int[][] update = {};*/
        LeetCode_370 solution = new LeetCode_370();
        int[] ans = solution.getModifiedArray(length, update);
        for(int n : ans){
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
