package leetCode;

import common.TreeNode;
import common.TreeUtil;

/**
 * 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 * */
public class LeetCode_111 {
    private int ans = Integer.MAX_VALUE;
    public int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth){
        if(node == null){
            return;
        }

        depth ++;

        if(node.left == null && node.right == null){
            ans = Math.min(ans, depth);
        }

        dfs(node.left, depth);
        dfs(node.right, depth);
    }

    public static void main(String[] args) throws Exception {
        String trees = "[2,null,3,null,4,null,5,null,6]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_111 solution = new LeetCode_111();
        int ans = solution.minDepth(root);
        System.out.println(ans);
    }
}
