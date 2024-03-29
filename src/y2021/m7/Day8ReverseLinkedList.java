package y2021.m7;

import dependecy.ListNode;

/**
 * @ClassName : Day8ReverseLinkedList
 * @Description: 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * @Author zhaooo
 * @Date 2021/7/8/19:16
 */
public class Day8ReverseLinkedList {
    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 1->2<-3<-4-<5
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 头插法
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode res = new ListNode();
        while (head != null) {
            ListNode tmp = head.next;
            head.next = res.next;
            res.next = head;
            head = tmp;
        }
        return res.next;
    }

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
