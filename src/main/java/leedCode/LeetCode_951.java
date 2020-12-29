package leedCode;

import common.TreeNode;
import common.TreeUtil;

import java.util.LinkedList;

/**
 * 翻转等价二叉树
 * */
public class LeetCode_951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2){
        LinkedList<TreeNode> queue1 = new LinkedList<TreeNode>();
        LinkedList<TreeNode> queue2 = new LinkedList<TreeNode>();

        queue1.offer(root1);
        queue2.offer(root2);

        boolean ans = false;
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNode curNode1 = queue1.pop();
            TreeNode curNode2 = queue2.pop();

            if(curNode1.left != null && curNode2.left != null && curNode1.left.val != curNode2.left.val){
                TreeNode temp = curNode1.left;
                curNode1.left = curNode1.right;
                curNode1.right = temp;
            } else if(curNode1.left != null && curNode2.left == null){
                TreeNode temp = curNode1.left;
                curNode1.left = curNode1.right;
                curNode1.right = temp;
            } else if(curNode1.left == null && curNode2.left != null){
                TreeNode temp = curNode1.left;
                curNode1.left = curNode1.right;
                curNode1.right = temp;
            }

            if(curNode1.right != null && curNode2.right != null && curNode1.right.val != curNode2.right.val){
                TreeNode temp = curNode1.right;
                curNode1.right = curNode1.left;
                curNode1.left = temp;
            } else if(curNode1.right != null && curNode2.right == null){
                TreeNode temp = curNode1.right;
                curNode1.right = curNode1.left;
                curNode1.left = temp;
            } else if(curNode1.right == null && curNode2.right != null){
                TreeNode temp = curNode1.right;
                curNode1.right = curNode1.left;
                curNode1.left = temp;
            }

            System.out.println("node1 = " + TreeUtil.covertTreeNodeToString(root1) + " - curNode2 = " + TreeUtil.covertTreeNodeToString(root2));

            if(curNode1.left != null){
                queue1.offer(curNode1.left);
            }

            if(curNode1.right != null){
                queue1.offer(curNode1.right);
            }

            if(curNode2.left != null){
                queue2.offer(curNode2.left);
            }

            if(curNode2.right != null){
                queue2.offer(curNode2.right);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        String trees1 = "[1,2,3,4,5,6,null,null,null,7,8]";
        String trees2 = "[1,3,2,null,6,4,5,null,null,null,null,8,7]";

        TreeNode root1 = TreeUtil.covertToTreeNode(trees1);
        TreeNode root2 = TreeUtil.covertToTreeNode(trees2);
        LeetCode_951 solution = new LeetCode_951();
        boolean ans = solution.flipEquiv(root1, root2);
        System.out.println(ans);
    }
}
