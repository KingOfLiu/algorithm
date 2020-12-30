package leetCode;

import common.TreeNode;
import common.TreeUtil;

/**
 * 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * */
public class LeetCode_938 {
    private int sum = 0;
    public int rangeSumBst(TreeNode root, int low, int high){
        if(root == null){
            return 0;
        }

        /*LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        int sum = 0;
        while(!queue.isEmpty()){
            TreeNode curNode = queue.pop();

            if(low <= curNode.val && curNode.val <= high){
                sum += curNode.val;
            }

            if(curNode.left != null){
                queue.offer(curNode.left);
            }

            if(curNode.right != null){
                queue.offer(curNode.right);
            }
        }*/
        dfs(root, low, high);
        return sum;
    }

    private void dfs(TreeNode root, int low, int high){
        if(root == null){
            return ;
        }

        if(low <= root.val && root.val <= high){
            sum += root.val;
        }

        dfs(root.left, low, high);
        dfs(root.right, low, high);
    }

    public static void main(String[] args) throws Exception {
        String trees = "[10,5,15,3,7,13,18,1,null,6]";
        int low = 6;
        int high = 10;
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_938 solution = new LeetCode_938();
        int ans = solution.rangeSumBst(root, low, high);
        System.out.println(ans);
    }
}
