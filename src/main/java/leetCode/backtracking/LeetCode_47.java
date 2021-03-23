package leetCode.backtracking;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * */
public class LeetCode_47 {
    public List<List<Integer>> permuteUnique(int[] nums){
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if(len == 0){
            return ans;
        }

        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        dfs(nums, len, 0, used, new ArrayDeque<>(), ans);
        return ans;
    }

    void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> ans){
        if(len == depth){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int i = 0; i < len; i++){
            if(used[i]){
                continue;
            }

            // 剪枝条件：i > 0 为了保证nums[i - 1]有意义
            // 写 !used[i - 1] 因为nums[i - 1]在深度优先搜索的过程中刚被撤销
            if(i > 0 && nums[i - 1] == nums[i] && !used[i - 1]){
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, used, path, ans);

            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args){
        int[] nums = {1,1,2};
        LeetCode_47 solution = new LeetCode_47();
        List ans = solution.permuteUnique(nums);
        System.out.println(ans);
    }
}
