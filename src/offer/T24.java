package offer;

import dependecy.ListNode;

/**
 * @ClassName : T25
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/18/21:07
 */
public class T24 {
    static class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }
}
