package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.*;

/**
 *  二叉树中第二小的节点
 * */
public class LeetCode_671 {
    public int findSecondMinimumValue(TreeNode root){
        if(root == null){
            return -1;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        int min = Integer.MAX_VALUE;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            min = Math.min(min, root.val);
            heap.offer(root.val);
            root = root.right;
        }

        while(!heap.isEmpty()){
            if(min < heap.peek()){
                return heap.poll();
            } else {
                heap.poll();
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        //String trees = "[2,2,5,null,null,5,7]";
        String trees = "[5,5,5]";
        //String trees = "[1,2,1,2,2]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);
        LeetCode_671 solution = new LeetCode_671();
        int ans = solution.findSecondMinimumValue(root);
        System.out.println(ans);
    }
}
