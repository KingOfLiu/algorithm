package common;

import java.util.*;

/**
 * 序列化与反序列化二叉树
 * */
public class TreeUtil {
    public static TreeNode covertToTreeNode(String json) {
        if(isBlank(json)){
            return null;
        }

        if(!json.startsWith("[") && !json.endsWith("]")){
            return null;
        }

        String[] data = json.substring(1, json.length() - 1).split(",");

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.valueOf(data[0]));
        queue.offer(root);

        int len = data.length, index = 0, left = 0, right = 0;
        while(!queue.isEmpty()){
            TreeNode curNode = queue.pop();

            left = (2 * index) + 1;

            if(left >= len){
                //System.out.println("left = " + left + " queue = " + queue);
                break;
            }

            TreeNode leftNode = createNodeByNum(data[left]);
            curNode.left = leftNode;
            if(null != leftNode){
                queue.offer(leftNode);
            }

            right = (index + 1) * 2;
            if(right >= len){
                //System.out.println("right = " + right + " queue = " + queue);
                break;
            }
            TreeNode rightNode = createNodeByNum(data[right]);

            if(null != rightNode){
                queue.offer(rightNode);
            }
            curNode.right = rightNode;

            index++;
        }
        return root;
    }

    public static String covertTreeNodeToString(TreeNode root){
        if(root == null){
            return "[]";
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        String ans = "";
        while (!queue.isEmpty()){
            int size = queue.size();
            if(nullQueue(queue)){
                queue.removeAll(queue);
                continue;
            }

            while(size > 0){
                TreeNode curNode = queue.pollFirst();
                if(curNode == null && !queue.isEmpty()){
                    ans += "null,";
                    size--;
                    continue;
                } if(curNode != null) {
                    ans += curNode.val + ",";

                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }

                size--;
            }
        }

        if(ans.length() > 0){
            ans = ans.substring(0, ans.length() - 1);
        }
        return "[" + ans + "]";
    }

    private static boolean nullQueue(LinkedList<TreeNode> queue){
        for(TreeNode node : queue){
            if(node != null){
               return false;
            }
        }
        return true;
    }

    private static TreeNode createNodeByNum(String num){
        if(isBlank(num) || num.equals("null")){
            return null;
        }

        return new TreeNode(Integer.valueOf(num));
    }

    private static boolean isBlank(String str){
        if(str == null || str.length() == 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        //String treeJson = "[4,2,7,1,null,6,9]";
        String treeJson = "[4,-7,-3,null,null,-4,null,6,null,-2]";
        TreeNode root = covertToTreeNode(treeJson);
        String ans = covertTreeNodeToString(root);
        System.out.println(ans);
    }
}
