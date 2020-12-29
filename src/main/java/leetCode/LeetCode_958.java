package leetCode;

import common.TreeNode;
import common.TreeUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉树的完全性检验
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
 * */
public class LeetCode_958 {
    /**
     * 思路：
     * 1、当前深度为D,左子节点的位置为2*D + 1, 右子节点为2*D
     * 2、判断每一层是否有间隔
     * */
    public boolean isCompleteTree(TreeNode root){
        if(root == null){
            return true;
        }

        LinkedList<TNode> queue = new LinkedList<TNode>();
        queue.offer(new TNode(0, 1, root));

        Deque<Integer> data = new ArrayDeque<Integer>();
        while(!queue.isEmpty()){
            int size = queue.size();

            // 定义起始位置
            while(size > 0){
                TNode curNode = queue.pop();
                int curDepth = curNode.depth;
                curDepth++;
                int curPos = curNode.position;
                if(!data.isEmpty() && curPos - data.peek() > 1){
                    return false;
                }
                data.push(curPos);

                if(curNode.node.left != null){
                    queue.offer(new TNode(curDepth,curPos << 1, curNode.node.left));
                }

                if(curNode.node.right != null){
                    queue.offer(new TNode(curDepth,(curPos << 1) + 1, curNode.node.right));
                }
                size--;
            }
        }
        return true;
    }

    private static class TNode{
        int depth = 0;
        int position = 0;
        TreeNode node;

        TNode(int depth, int position, TreeNode node){
            this.depth = depth;
            this.position = position;
            this.node = node;
        }
    }

    public static void main(String[] args) throws Exception {
        String trees = "[1,null,2]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_958 solution = new LeetCode_958();
        boolean ans = solution.isCompleteTree(root);
        System.out.println(ans);
    }
}
