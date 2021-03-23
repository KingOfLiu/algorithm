package leetCode.backtracking;

import java.util.*;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 *
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * */
public class LeetCode_39 {
    public List<List<Integer>> combinationSumOptimization(int[] candidates, int target){
        int len = candidates.length;
        List<List<Integer>> ans = new ArrayList<>();
        if(len == 0){
            return ans;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);

        dfs(candidates, 0, len, target, new ArrayDeque<Integer>(), ans);
        return ans;
    }

    /**
     * 剪枝优化dfs
     * */
    void dfsOptimization(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> ans){
        // 由于进入更深层的时候，小于0的部分被剪枝，因此递归终止条件值只判断等于0的情况
        if(target == 0){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int i = begin; i < len; i++){
            // 重点理解剪枝：前提数组已经排序
            if(target - candidates[i] < 0){
                break;
            }

            path.addLast(candidates[i]);

            dfsOptimization(candidates, i, len, target - candidates[i], path, ans);

            path.removeLast();
        }
    }

    /** ######################## */
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        int len = candidates.length;
        List<List<Integer>> ans = new ArrayList<>();
        if(len == 0){
            return ans;
        }

        dfs(candidates, 0, len, target, new ArrayDeque<Integer>(), ans);
        return ans;
    }

    /**
     * @param candidates 候选数组
     * @param begin 搜索起点
     * @param len 冗余字段候选数组长度（可不传）
     * @param target 每减一个元素，目标值变小
     * @param path 从根节点到目标节点的路径，一个栈
     * @param ans 结果集
     * */
    void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> ans){
        // target为负数和0，不再产生新的孩子节点
        if(target < 0){
            return ;
        }

        if(target == 0){
            ans.add(new ArrayList<>(path));
            return ;
        }

        // 从begin开始
        for(int i = begin; i < len; i++){
            path.addLast(candidates[i]);

            // 注意：由于每个元素可以重复，下一轮搜索的起点依然是i
            dfs(candidates, i, len, target - candidates[i], path, ans);

            // 状态重置
            path.removeLast();
        }
    }

    public static void main(String[] args){
        int[] candidates = {2,3,5};
        int target = 8;
        LeetCode_39 solution = new LeetCode_39();
        List ans = solution.combinationSumOptimization(candidates, target);
        System.out.println(ans);
    }
}
