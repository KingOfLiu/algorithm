package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 最大层内元素和
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 *
 * 请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
 *
 * 输入：root = [1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 * */
public class LeetCode_1161 {
    public int maxLevelSum(TreeNode root){
        if(root == null){
            return -1;
        }

        Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();
        int maxSum = Integer.MIN_VALUE;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int level = 1;
        while(!queue.isEmpty()){
            int sum = 0;
            int size = queue.size();
            while(size > 0){
                TreeNode curNode = queue.pop();
                sum += curNode.val;

                if(curNode.left != null){
                    queue.offer(curNode.left);
                }

                if(curNode.right != null){
                    queue.offer(curNode.right);
                }
                size--;
            }

            maxSum = Math.max(maxSum, sum);
            levelMap.put(level, maxSum);
            level++;
        }

        int ans = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> entry : levelMap.entrySet()){
            if(entry.getValue() == maxSum){
                ans = Math.min(ans, entry.getKey());
            }
        }
        return ans;
    }

    public static void main(String[] args){
        String trees = "[989,null,10250,98693,-89388,null,null,null,-32127]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_1161 solution = new LeetCode_1161();
        int ans = solution.maxLevelSum(root);
        System.out.println(ans);
    }
}
