package common;

/**
 * 链表工具类
 * */
public class ListNodeUtil {
    public static ListNode stringToListNode(String string){
        if(string == null || string.length() == 0){
            return null;
        }

        String[] data = string.substring(1, string.length() - 1).split(",");
        ListNode head = new ListNode(Integer.parseInt(data[0]));
        ListNode curNode = head;
        for(int i = 1; i < data.length; i++){
            curNode.next = new ListNode(Integer.parseInt(data[i].trim()));
            curNode = curNode.next;
        }
        return head;
    }

    public static void main(String[] args){
        String listNodes = "[-10, -3, 0, 5, 9]";
        ListNode listNode = stringToListNode(listNodes);
        System.out.println(listNode);
    }
}
