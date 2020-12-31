package leetCode;

import common.TreeNode;
import common.TreeUtil;

import java.util.LinkedList;

/**
 * 奇偶树
 *
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 * ①.二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * ②.偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * ③.奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 * */
public class LeetCode_1609 {
    public boolean isEvenOddTree(TreeNode root){
        if(root == null){
            return true;
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> checkData = new LinkedList<Integer>();
            while(size > 0){
                TreeNode curNode = queue.pop();
                // 偶数层 判断节点值是否为奇数，且是否递减
                if(level % 2 == 0){
                    if(curNode.val % 2 != 1){
                        return false;
                    }

                    // 偶数层 从左到右 递增
                    if(!checkData.isEmpty()){
                        int diff = curNode.val - checkData.peekLast();
                        if(diff <= 0 || diff % 2 != 0){
                            return false;
                        }
                    }
                }
                // 奇数层 判断节点值否为偶数，且是否递减
                else if(level % 2 == 1){
                    if(curNode.val % 2 != 0){
                        return false;
                    }

                    // 奇数层 从左到右 递减
                    if(!checkData.isEmpty()){
                        int diff = curNode.val - checkData.peekLast();
                        if(diff >= 0 || diff % 2 != 0){
                            return false;
                        }
                    }
                }
                checkData.offer(curNode.val);

                if(curNode.left != null){
                    queue.offer(curNode.left);
                }

                if(curNode.right != null){
                    queue.offer(curNode.right);
                }
                size --;
            }
            checkData.clear();
            level++;
        }
        return true;
    }

    public static void main(String[] args){
        String trees = "[13,34,32,23,25,27,29,44,40,36,34,30,30,28,26,3,7,9,11,15,17,21,25,null,null,27,31,35,null,37,null,30,null,26,null,null,null,24,null,20,16,12,10,null,null,8,null,null,null,null,null,6,null,null,null,null,null,15,19,null,null,null,null,23,null,27,29,33,37,null,null,null,null,null,null,48,null,null,null,46,null,null,null,42,38,34,32,null,null,null,null,19]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_1609 solution = new LeetCode_1609();
        boolean ans = solution.isEvenOddTree(root);
        System.out.println(ans);

        System.out.println(0 % 2);
    }
}
