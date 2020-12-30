package leetCode;

import common.ListNode;
import common.ListNodeUtil;
import common.TreeNode;
import common.TreeUtil;

import java.util.LinkedList;

/**
 * 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5]
 * */
public class LeetCode_109 {
    public TreeNode sortedListToBST(ListNode node){
        if(node == null){
            return null;
        }

        return buildTree(node, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right){
        if(left == right){
            return null;
        }

        ListNode node = getMedian(left, right);
        TreeNode root = new TreeNode(node.val);
        root.left = buildTree(left, node);
        root.right = buildTree(node.next, right);
        return root;
    }

    /**
     * 获取链表中位数（快慢指针法）
     * */
    private ListNode getMedian(ListNode left, ListNode right){
        ListNode fast = left;
        ListNode slow = left;
        while(fast != right && fast.next != right){
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args){
        String listNodes = "[-10, -3, 0, 5, 9]";
        ListNode listnode = ListNodeUtil.stringToListNode(listNodes);

        LeetCode_109 solution = new LeetCode_109();
        TreeNode ans = solution.sortedListToBST(listnode);
        System.out.println(TreeUtil.covertTreeNodeToString(ans));
    }
}