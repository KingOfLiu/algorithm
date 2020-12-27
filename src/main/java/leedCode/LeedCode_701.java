package leedCode;

import common.TreeNode;
import common.TreeUtil;

/**
 * 二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
 * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * */
public class LeedCode_701 {
    public TreeNode insertIntoBst(TreeNode root, int val){
        if(root == null){
            return null;
        }

        TreeNode curNode = root;
        while(curNode != null){
            if(curNode.val > val){
                if(curNode.left == null){
                    curNode.left = new TreeNode(val);
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else if(curNode.val < val){
                if(curNode.right == null){
                    curNode.right = new TreeNode(val);
                    break;
                } else {
                    curNode = curNode.right;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) throws Exception {
        String trees = "[4,2,7,1,3]";
        int val = 5;

        LeedCode_701 solution = new LeedCode_701();
        TreeNode ans = solution.insertIntoBst(TreeUtil.covertToTreeNode(trees), val);
        System.out.println(TreeUtil.covertTreeNodeToString(ans));
    }
}
