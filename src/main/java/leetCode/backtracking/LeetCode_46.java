package leetCode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * */
public class LeetCode_46 {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length == 0){
            return res;
        }

        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();

        dfs(nums, 0, path, used, res);
        return res;
    }

    void dfs(int[] nums, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res){
        int len = nums.length;
        if(depth == len){
            res.add(new ArrayList<>(path));
            return ;
        }

        for(int i = 0; i < len; i++){
            if(!used[i]){
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, depth + 1, path, used, res);

                // 回溯逻辑
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 3};
        LeetCode_46 solution = new LeetCode_46();
        List ans = solution.permute(nums);
        System.out.println(ans);
    }
}
