package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 计算给定二叉树的所有左叶子之和。
 * */
public class LeetCode_404 {
    public int sumOfLeftLeaves(TreeNode root){
        if(root == null){
            return 0;
        }

        int ans = 0;
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode preNode = null;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            preNode = stack.peek();

            if(root.left == null && root.right == null && preNode.left == root){
                ans += root.val;
            }

            root = root.right;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        String trees = "[4,-7,-3,null,null,9,-7,-4,null,-2]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_404 solution = new LeetCode_404();
        int ans = solution.sumOfLeftLeaves(root);
        System.out.println(ans);
    }
}