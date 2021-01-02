package leetCode;

import common.TreeNode;
import common.TreeUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 前序遍历构造二叉搜索树
 *
 * 返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 *
 * (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。此外，前序遍历首先显示节点 node 的值，然后遍历 node.left，接着遍历 node.right。）
 *
 * 题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。
 * */
public class LeetCode_1008 {
    public TreeNode bstFromPreorder(int[] preorder){
        if(preorder == null || preorder.length == 0){
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.push(root);
        for(int i = 1; i < preorder.length; i++){
            TreeNode node = stack.peek();
            TreeNode child = new TreeNode(preorder[i]);
            while(!stack.isEmpty() && stack.peek().val < child.val){
                node = stack.poll();
            }

            if(node.val < child.val){
                node.right = child;
            } else {
                node.left = child;
            }
            stack.push(child);
        }
        return root;
    }

    public static void main(String[] args){
        int[] preorder = {8,5,1,7,10,12};

        LeetCode_1008 solution = new LeetCode_1008();
        TreeNode ans = solution.bstFromPreorder(preorder);
        System.out.println(TreeUtil.covertTreeNodeToString(ans));
    }
}
