package leetCode;

import common.TreeNode;
import common.TreeUtil;

/**
 *  删除二叉搜索树中的节点
 *  给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 *  返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * */
public class LeetCode_450 {
    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null){
            return null;
        }

        if(root.left == null && root.right == null && root.val == key){
            return null;
        }

        TreeNode preNode = null;
        TreeNode curNode = root;
        while(curNode != null){
            if(curNode.val == key){
                if(curNode.left == null && curNode.right == null){
                    if(preNode != null && preNode.left == curNode){
                        preNode.left = null;
                    } else if(preNode != null && preNode.right == curNode){
                        preNode.right = null;
                    }
                } else {
                    TreeNode temp = curNode;
                    TreeNode tempLeft = temp.left;
                    TreeNode tempRight = temp.right;

                    curNode = null;
                    curNode = tempRight;
                    curNode.left = tempLeft;
                    curNode.right = tempRight.right;

                    if(preNode != null && preNode.left == temp){
                        preNode.left = curNode;
                    } else if(preNode != null && preNode.right == temp){
                        preNode.right = curNode;
                    }
                }
                break;
            } else if(curNode.val > key){
                preNode = curNode;
                curNode = curNode.left;
            } else {
                preNode = curNode;
                curNode = curNode.right;
            }
        }
        return root;
    }

    public static void main(String[] args) throws Exception {
        int k = 5;
        String trees = "[5,3,6,2,4,null,7]";
        //String trees = "[7]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_450 solution = new LeetCode_450();
        TreeNode ans = solution.deleteNode(root, k);
        System.out.println(TreeUtil.covertTreeNodeToString(ans));
    }
}
