package leetCode;

import common.TreeNode;
import common.TreeUtil;

/**
 * 在二叉树中分配硬币
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，
 * 并且总共有 N 枚硬币。
 *
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。
 * (移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * */
public class LeetCode_979 {
    private int ans = 0;
    public int distributeCoins(TreeNode root){
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);
        ans += Math.abs(l) + Math.abs(r);
        return root.val + l + r - 1;
    }

    public static void main(String[] args) throws Exception {
        String trees = "[1,0,0,null,3]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);
        LeetCode_979 solution = new LeetCode_979();
        int ans = solution.distributeCoins(root);
        System.out.println(ans);
    }
}
