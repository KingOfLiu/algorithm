package common;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * */
public class TreeUtil {
    private static final String PREFIX_JSON = "[";
    private final static String SUBFIX_JSON = "]";

    public static TreeNode covertToTreeNode(String json) throws Exception {
        if(StringUtils.isBlank(json)){
            return null;
        }

        if(!json.startsWith(PREFIX_JSON) && !json.endsWith(SUBFIX_JSON)){
            throw new Exception("格式不正确");
        }

        String[] data = json.substring(1, json.length() - 1).split(",");

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.valueOf(data[0]));
        stack.offer(root);

        int len = data.length, index = 0, left = 0, right = 0;
        while(!stack.isEmpty() && index < len){
            TreeNode curNode = stack.pop();

            left = (2 * index) + 1;

            if(left >= len){
                continue;
            }

            TreeNode leftNode = createNodeByNum(data[left]);
            curNode.left = leftNode;
            if(null != leftNode){
                stack.offer(leftNode);
            }

            right = (index + 1) * 2;
            if(right >= len){
                continue;
            }
            TreeNode rightNode = createNodeByNum(data[right]);

            if(null != rightNode){
                stack.offer(rightNode);
            }
            curNode.right = rightNode;

            index++;
        }
        return root;
    }

    private static TreeNode createNodeByNum(String num){
        if(StringUtils.isBlank(num) || num.equals("null")){
            return null;
        }

        return new TreeNode(Integer.valueOf(num));
    }

    public static void main(String[] args) throws Exception {
        String treeJson = "[4,2,7,1,null,6,9]";
        TreeNode root = covertToTreeNode(treeJson);

        inOrder(root);
    }

    public static void inOrder(TreeNode root){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!stack.isEmpty() || null != root){
            while(null != root){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            System.out.print(root.val + " ");

            root = root.right;
        }
        System.out.println();
    }
}
