package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 序列化和反序列化二叉搜索树
 *
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 *
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * */
public class LeetCode_449 {
    /**
     * Encodes a tree to single string.
     * */
    public String serialize(TreeNode root){
        if(root == null){
            return "[]";
        }

        String ans = "";
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (!stack.isEmpty() || root != null){
            while(root != null){
                ans += root.val + ",";
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            root = root.right;
        }
        ans = ans.substring(0, ans.length() - 1);
        return "[" + ans + "]";
    }

    /**
     * Decodes your encoded data to tree
     * */
    public TreeNode deserialize(String data){
        if(data.equals("[]")){
            return null;
        }

        if(!data.startsWith("[") || !data.endsWith("]")){
            return null;
        }

        String[] nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(getNum(nodes[0]));

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        int index = 1;
        while(!queue.isEmpty()){
            if(index >= nodes.length){
                break;
            }
            TreeNode curNode = queue.pop();
            Integer curLeftVal = getNum(nodes[index]);
            if(curLeftVal != null && curLeftVal < curNode.val){
                curNode.left = new TreeNode(curLeftVal);
            } else {
                curNode.left = null;
            }
            index++;

            if(index >= nodes.length){
                break;
            }

            Integer curRightVal = getNum(nodes[index]);
            if(curRightVal != null && curRightVal > curNode.val){
                curNode.right = new TreeNode(curRightVal);
            } else {
                curNode.right = null;
            }
            index++;

            if(curNode.left != null){
                queue.offer(curNode.left);
            }

            if(curNode.right != null){
                queue.offer(curNode.right);
            }
        }
        return root;
    }

    private Integer getNum(String numStr){
        numStr = numStr.trim();
        if("null".equals(numStr)){
            return null;
        }

        return Integer.valueOf(numStr);
    }

    public static void main(String[] args){
        //String trees = "[2,1,3]";
        String trees = "[1,null,2]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_449 solution = new LeetCode_449();
        String ansString = solution.serialize(root);
        System.out.println(ansString);
        TreeNode ansNode = solution.deserialize(ansString);

        System.out.println(TreeUtil.covertTreeNodeToString(ansNode));
    }
}