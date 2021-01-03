package leedCode;

import common.TreeNode;
import common.TreeUtil;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 655. 输出二叉树
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 *
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 * */
public class LeedCode_655 {
    private static class TNode{
        TreeNode node;
        int leftIndex;
        int rightIndex;
        int index;
        int height;

        TNode(TreeNode node, int leftIndex, int rightIndex, int index, int height){
            this.node = node;
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString(){
            return //"val = " + node.val +
                    " index = " + index
                    + " leftIndex = " + leftIndex + " rightIndex = " + rightIndex
                    + " height = " + height;
        }
    }
    public static List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<List<String>>();
        if(root == null){
            return ans;
        }

        // 计算出高度和宽度（宽度=2^height次方 -1 ）
        int height = depth(root);
        int width = (1 << height) - 1;

        String[][] outputs = new String[height][width];
        for(String[] s : outputs){
            Arrays.fill(s, "");
        }

        LinkedList<TNode> queue = new LinkedList<TNode>();
        queue.offer(new TNode(root, 0, width, width, 0));

        while(!queue.isEmpty()){
            TNode curNode = queue.pop();
            TreeNode node = curNode.node;
            int leftIndex = curNode.leftIndex;
            int rightIndex = curNode.rightIndex;
            int depth = curNode.height;

            int index = (leftIndex + rightIndex)/2;
            outputs[depth][index] = String.valueOf(node.val);

            depth++;

            if(node.left != null){
                queue.offer(
                        new TNode(
                                node.left,
                                leftIndex,
                                index,
                                (leftIndex + rightIndex)/2,
                                depth
                        ));
            }

            if(node.right != null){
                queue.offer(
                        new TNode(
                                node.right,
                                index + 1,
                                rightIndex,
                                (leftIndex + rightIndex + 1)/2,
                                depth
                        ));
            }
        }

        for(String[] col : outputs){
            List<String> outputRow = new ArrayList<String>();
            for(String row : col){
                outputRow.add(row);
            }
            ans.add(outputRow);
        }
        return ans;
    }

    private static int depth(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        int depth = 0;
        while(!queue.isEmpty()){
            depth++;
            int size = queue.size();
            while(size > 0){
                TreeNode cur = queue.pop();
                if(cur.left != null){
                    queue.offer(cur.left);
                }

                if(cur.right != null){
                    queue.offer(cur.right);
                }
                size--;
            }
        }
        return depth;
    }

    public static void main(String[] args) throws Exception {
        String json = "[10,5,15,null,null,6,20]";
        TreeNode root = TreeUtil.covertToTreeNode(json);
        List ans = printTree(root);
        System.out.println(ans);
    }
}
