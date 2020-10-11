package offer.offer_24;

import java.util.*;

/**
 * 剑指Offer 24题
 * 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * */
public class Solution {
    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode newNode = reverseList(node1);
        printNode(newNode);
    }

    /**
     * 反转链表
     * */
    public static ListNode reverseList(ListNode head) {
        ListNode curNode = head;

        Stack<ListNode> stack = new Stack();
        while(curNode != null){
            stack.push(curNode);
            curNode = curNode.next;
        }

        ListNode newNode = stack.pop();
        ListNode dummy = newNode;
        while(!stack.isEmpty()){
            ListNode tmp = stack.pop();
            newNode.next = tmp;
            newNode = newNode.next;
        }
        newNode.next = null;
        return dummy;
    }

    public static void printNode(ListNode node){
        ListNode cur = node;
        while(cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}