package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.LinkedList;

/**
 * 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 * */
public class LeetCode_1022 {
    private int ans = 0;
    public int sumRootToLeaf(TreeNode root){
        if(root == null){
            return 0;
        }

        dfs(root, new LinkedList<Integer>());
        return ans;
    }

    private void dfs(TreeNode node, LinkedList<Integer> data){
        if(node == null){
            return;
        }

        data.add(node.val);
        if(node.left == null && node.right == null){
            ans += binaryStringToInteger(data);
        }

        dfs(node.left, data);
        dfs(node.right, data);
        data.removeLast();
    }

    /**
     * 二进制字符串转为int
     * */
    private int binaryStringToInteger(LinkedList<Integer> data){
        LinkedList<Integer> bitData = new LinkedList<Integer>(data);

        int highBit = bitData.size() - 1;
        int sum = 0;
        while(!bitData.isEmpty()){
            Integer bit = bitData.pop();
            sum += bit * (bit << highBit);
            highBit--;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        //String trees = "[1,0,1,0,1,0,1]";
        String trees = "[1,1]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_1022 solution = new LeetCode_1022();
        int ans = solution.sumRootToLeaf(root);
        System.out.println(ans);
    }
}