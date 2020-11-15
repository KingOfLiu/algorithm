package offer.offer_37;

import java.util.*;

public class Solution {
    private static TreeNode ROOT_1;
    static {
        ROOT_1 = new TreeNode(1);

        TreeNode ROOT_LEFT_2 = new TreeNode(2);
        TreeNode ROOT_RIGHT_3 = new TreeNode(3);
        ROOT_1.left = ROOT_LEFT_2;
        ROOT_1.right = ROOT_RIGHT_3;

        TreeNode LEFT_4 = new TreeNode(4);
        TreeNode RIGHT_5 = new TreeNode(5);
        ROOT_RIGHT_3.right = RIGHT_5;
        ROOT_RIGHT_3.left = LEFT_4;
    }

    /**
     * 序列化使用层次遍历
     * */
    public static String serialize(TreeNode root){
        if(null == root){
            return "[]";
        }

        LinkedList<TreeNode> heap = new LinkedList<TreeNode>();
        heap.add(root);

        String res = "[";
        while(!heap.isEmpty()){
            TreeNode curNode = heap.poll();

            String valStr = "null";
            if(null != curNode){
                valStr = String.valueOf(curNode.val);
                heap.add(curNode.left);
                heap.add(curNode.right);
            }
            res += valStr + ",";
        }

        res = res.substring(0, res.length() - 1);
        res += "]";
        return res;
    }

    /**
     * 反序列化通过下列公式：
     * 1. left = (2 * i) + 1
     * 2. right = 2 * (i + 1)
     * */
    public static TreeNode deserialize(String data){
        if(null == data || data.length() == 0){
            return null;
        }

        String[] nodeVals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodeVals[0]));

        LinkedList<TreeNode> heap = new LinkedList<TreeNode>();
        heap.add(root);

        int i = 1;
        while(!heap.isEmpty() && i < nodeVals.length){
            TreeNode curNode = heap.poll();
            if(!"null".equals(nodeVals[i])){
                curNode.left = new TreeNode(Integer.parseInt(nodeVals[i]));
                heap.add(curNode.left);
            }
            i++;
            if(!"null".equals(nodeVals[i])){
                curNode.right = new TreeNode(Integer.parseInt(nodeVals[i]));
                heap.add(curNode.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args){
        String str = "[1,2,3,null,null,4,5]";

        //System.out.println(deserialize(str));

        String res = serialize(deserialize(str));
        System.out.println(res);
    }
}