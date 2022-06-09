package per.cajr.leetcode.linklist.no92;

import per.cajr.leetcode.linklist.ListNode;

/**
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * <p>
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * <p>
 * 输出：[5]
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-linked-list-ii
 *
 * @author CAJR
 */
public class ReverseBetweenLinkedList {

    ListNode successor;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        //base case
        if (left == 1) {
            return reverseN(head, right);
        }
        //前进到起点触发 base case (妙)
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private ListNode reverseN(ListNode node, int n) {
        if (n == 1) {
            successor = node.next;
            return node;
        }

        ListNode last = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = successor;

        return last;
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

        System.out.println(new ReverseBetweenLinkedList().reverseBetween(head, 3, 4).toString());
    }
}
