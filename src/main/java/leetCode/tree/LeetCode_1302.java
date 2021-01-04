package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 层数最深叶子节点的和
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 * */
public class LeetCode_1302 {
    private Map<Integer, Integer> ansMap = new HashMap<Integer, Integer>();
    private int maxDepth = Integer.MIN_VALUE;
    public int deepestLeavesSum(TreeNode root){
        if(root == null){
            return 0;
        }
        dfs(root, new LinkedList<Integer>());

        int deepestLevel = 0;
        for(Map.Entry<Integer, Integer> entry : ansMap.entrySet()){
            if(entry.getKey() > deepestLevel){
                deepestLevel = entry.getKey();
            }
        }
        return ansMap.get(deepestLevel);
    }

    private void dfs(TreeNode node, LinkedList<Integer> data){
        if(node == null){
            return;
        }
        data.add(node.val);
        if(node.left == null && node.right == null){
            LinkedList<Integer> sumData = new LinkedList<Integer>(data);
            int size = sumData.size();
            if(size > maxDepth){
                maxDepth = Math.max(maxDepth, size);
                int sum = ansMap.getOrDefault(size, 0);
                ansMap.put(size, sumData.pollLast() + sum);
            } else if(size == maxDepth){
                int sum = ansMap.getOrDefault(size, 0);
                ansMap.put(size, sumData.pollLast() + sum);
            } else {
                ansMap.remove(size);
            }
        }

        dfs(node.left, data);
        dfs(node.right, data);
        data.removeLast();
    }

    public static void main(String[] args){
        String trees = "[50,null,54,98,6,null,null,null,34]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_1302 solution = new LeetCode_1302();
        int ans = solution.deepestLeavesSum(root);
        System.out.println(ans);
    }
}
