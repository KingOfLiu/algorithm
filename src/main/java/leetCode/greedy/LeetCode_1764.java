package leetCode.greedy;

/**
 * 1764. 通过连接另一个数组的子数组得到一个数组
 * 给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。
 * 你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] 
 * （下标从 0 开始）完全相同，且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现
 * 的位置在第 i 个子数组前面。
 * （也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
 * 如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。
 * 如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组
 * 是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
 *
 * 示例 1：
 * 输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
 * 输出：true
 * 解释：你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
 * 这两个子数组是不相交的，因为它们没有任何共同的元素。
 *
 *
 * 示例 2：
 * 输入：groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
 * 输出：false
 * 解释：选择子数组 [1,2,3,4,10,-2] 和 [1,2,3,4,10,-2] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。
 * [10,-2] 必须出现在 [1,2,3,4] 之前。
 *
 *
 * 示例 3：
 * 输入：groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
 * 输出：false
 * 解释：选择子数组 [7,7,1,2,3,4,7,7] 和 [7,7,1,2,3,4,7,7] 是不正确的，因为它们不是不相交子数组。
 * 它们有一个共同的元素 nums[4] （下标从 0 开始）。
 *
 * 提示：
 *
 * groups.length == n
 * 1 <= n <= 10^3
 * 1 <= groups[i].length, sum(groups[i].length) <= 10^3
 * 1 <= nums.length <= 10^3
 * -10^7 <= groups[i][j], nums[k] <= 10^7
 * */
public class LeetCode_1764 {
    public boolean canChoose(int[][] groups, int[] nums) {
        int startIndex = 0, cnt = 0;
        for(int j = 0; j < groups.length; j++){
            int[] rows = groups[j];
            startIndex = check(startIndex, nums, rows);
            if(startIndex != 0){
                cnt += 1;
            }
        }
        return cnt == groups.length;
    }

    int check(int startIdx, int[] nums, int[] target){
        int cnt = 0;
        for(int i = startIdx; i < nums.length; i++){
            if(nums[i] == target[0]){
                int x = i, j = 0;
                while(x < nums.length && j < target.length && nums[x] == target[j]){
                    x ++;
                    j ++;
                    cnt ++;
                }
                if(cnt == target.length){
                    return x;
                }
            }
            cnt = 0;
        }
        return 0;
    }

    public static void main(String[] args){
        int[][] groups = {{10,-2},{1,2,3,4}};
        int[] nums = {1,2,3,4,10,-2};
        /*int[][] groups = {{1,-1,-1}, {3,-2,0}};
        int[] nums = {1,-1,0,1,-1,-1,3,-2,0};*/
        /*int[][] groups = {{21,22,21,22,21,30}};
        int[] nums = {21,22,21,22,21,22,21,30};*/
        /*int[][] groups = {{1,2}};
        int[] nums = {1,3,2};*/

        /*int[][] groups = {{-5,0}};
        int[] nums = {2,0,-2,5,-1,2,4,3,4,-5,-5};*/

        LeetCode_1764 solution = new LeetCode_1764();

        boolean ans = solution.canChoose(groups, nums);
        System.out.println(ans);

        //String str = "1234444412366666123";
        //System.out.println(str.indexOf("123"));
    }
}
