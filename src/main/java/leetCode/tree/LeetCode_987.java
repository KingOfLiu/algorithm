package leetCode.tree;

import common.TreeNode;
import common.TreeUtil;

import java.util.*;

/**
 * 二叉树的垂序遍历
 * 给定二叉树，按垂序遍历返回其结点值。
 *
 * 对位于 (X, Y) 的每个结点而言，其左右子结点分别位于 (X-1, Y-1) 和 (X+1, Y-1)。
 *
 * 把一条垂线从 X = -infinity 移动到 X = +infinity ，每当该垂线与结点接触时，我们按从上到下的顺序报告结点的值（ Y 坐标递减）。
 *
 * 如果两个结点位置相同，则首先报告的结点值较小。
 *
 * 按 X 坐标顺序返回非空报告的列表。每个报告都有一个结点值列表。
 * */
public class LeetCode_987 {
    private static class LocateTreeNode implements Comparable<LocateTreeNode>{
        TreeNode node;
        int x;
        int y;
        LocateTreeNode(TreeNode node, int x,int y){
            this.node = node;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(LocateTreeNode that) {
            if(this.x != that.x){
                return Integer.compare(this.x, that.x);
            } else if(this.y != that.y){
                return Integer.compare(this.y, that.y);
            } else {
                return Integer.compare(this.node.val, that.node.val);
            }
        }

        @Override
        public String toString(){
            return "val = " + this.node.val + " x = " + x + " y = " + y;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(root == null){
            return ans;
        }

        List<LocateTreeNode> locateTreeNodes = new ArrayList<>();

        LocateTreeNode rootLocate = new LocateTreeNode(root, 0, 0);
        LinkedList<LocateTreeNode> queue = new LinkedList<LocateTreeNode>();
        queue.offer(rootLocate);
        while(!queue.isEmpty()){
            LocateTreeNode curLocate = queue.pop();
            TreeNode curNode = curLocate.node;
            int x = curLocate.x;
            int y = curLocate.y;

            locateTreeNodes.add(curLocate);

            if(curNode.left != null){
                queue.offer(new LocateTreeNode(curNode.left, x - 1, y + 1));
            }

            if(curNode.right != null){
                queue.offer(new LocateTreeNode(curNode.right, x + 1, y + 1));
            }
        }

        Collections.sort(locateTreeNodes);
        System.out.println(locateTreeNodes);

        ans.add(new ArrayList<Integer>());

        int prevX = locateTreeNodes.get(0).x;
        for(LocateTreeNode locate : locateTreeNodes){
            if(locate.x != prevX){
                prevX = locate.x;
                ans.add(new ArrayList<Integer>());
            }

            ans.get(ans.size() - 1).add(locate.node.val);
        }
        return ans;
    }

    public static void main(String[] args){
        String trees = "[3,9,20,null,null,15,7]";
        TreeNode root = TreeUtil.covertToTreeNode(trees);

        LeetCode_987 solution = new LeetCode_987();
        List ans = solution.verticalTraversal(root);
        System.out.println(ans);
    }
}
