package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

/**
 * 平衡二叉树
 * 一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * */
public class LeetCode_110 {
    public boolean isBalanced(TreeNode root){
        if(root == null){
            return true;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return (Math.abs(leftHeight - rightHeight) <= 1) && (isBalanced(root.left) && isBalanced(root.right));
    }

    private int height(TreeNode node){
        if(node == null){
            return 0;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void main(String[] args) throws Exception {
        //String trees = "[1,2,2,3,3,null,null,4,4]";
        String trees = "[1,2,2,3,null,null,3,4,null,null,4]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_110 solution = new LeetCode_110();
        boolean ans = solution.isBalanced(root);
        System.out.println(ans);
    }
}
