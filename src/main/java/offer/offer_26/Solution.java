package offer.offer_26;

import java.util.*;

public class Solution {
    public static void main(String[] args){

        //preorderPrintTree(root3);
        TreeNode root = createTree123456();
        levelPrintTree(root);
    }

    private static TreeNode createTree123456(){
        TreeNode root1 = new TreeNode(1);

        TreeNode left2 = new TreeNode(2);
        TreeNode right3 = new TreeNode(3);
        root1.left = left2;
        root1.right = right3;

        TreeNode left4 = new TreeNode(4);
        TreeNode right5 = new TreeNode(5);

        left2.left = left4;
        left2.right = right5;

        TreeNode right6 = new TreeNode(6);
        right3.right = right6;

        return root1;
    }

    private static TreeNode createTree34512(){
        TreeNode root3 = new TreeNode(3);
        TreeNode left4 = new TreeNode(4);
        TreeNode right5 = new TreeNode(5);

        root3.left = left4;
        root3.right = right5;

        TreeNode left1 = new TreeNode(1);
        TreeNode right2 = new TreeNode(2);

        left4.left = left1;
        left4.right = right2;
        return root3;
    }

    /**
     * 前序遍历二叉树
     * */
    public static void preorderPrintTree(TreeNode root){
        // 递归方式 前序遍历
        /**
        if(root == null){return;}
        System.out.println(root.val);
        preorderPrintTree(root.left);
        preorderPrintTree(root.right);
         */

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            System.out.println(cur.val);

            if(cur.right != null){
                stack.push(cur.right);
            }

            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    public static void levelPrintTree(TreeNode root){
        LinkedList<TreeLevel> queue = new LinkedList<TreeLevel>();

        ArrayList<Integer> watchArray = new ArrayList<Integer>();

        TreeLevel rootLevel = new TreeLevel(root, 0);
        queue.add(rootLevel);

        while(!queue.isEmpty()){
            TreeLevel curLevel = queue.pop();

            if(watchArray.get(curLevel.level) != null){
                watchArray.remove(curLevel.level);
            }
            watchArray.add(curLevel.level, curLevel.treeNode.val);

            int cur = curLevel.level;
            cur += 1;

            TreeNode curNode = curLevel.treeNode;
            if(curNode.left != null){
                queue.add(new TreeLevel(curNode.left, cur));
            }

            if(curNode.right != null){
                queue.add(new TreeLevel(curNode.right, cur));
            }
        }
        System.out.println(watchArray);
    }

    private static class TreeLevel{
        TreeNode treeNode;
        int level;

        TreeLevel(TreeNode node, int e){
            treeNode = node;
            level = e;
        }

        public String toString(){
            return treeNode.val + " - " + level;
        }
    }
}
