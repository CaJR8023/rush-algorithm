package per.cajr.leetcode.linklist.no206;

import per.cajr.leetcode.linklist.ListNode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * @author CAJR
 */
public class ReverseLinkedList {
    /**
     * 迭代
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    /**
     * 递归
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 后驱节点
     */
    ListNode successor = null;

    /**
     * 递归 反转前N个节点
     */
    public ListNode reverseTopN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }

        //递归反转前n-1个节点 并返回翻转后的新头节点
        ListNode newHead = reverseTopN(head.next, n - 1);
        //反转当前的节点
        head.next.next = head;
        head.next = successor;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        node3.next = new ListNode(5);
        node2.next = node3;
        node1.next = node2;
        head.next = node1;

        System.out.println(new ReverseLinkedList().reverseTopN(head, 2).toString());
    }
}
