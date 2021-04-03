package y2021.m3;

import dependecy.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @ClassName : Day28RemoveNthBodeFromEndOfList
 * @Description: 19. 删除链表的倒数第 N 个结点
 * !倒数想到快慢指针
 * @Author zhaooo
 * @Date 2021/3/27/23:57
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day28RemoveNthBodeFromEndOfList {
    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 新头结点防止删除第一个元素
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            ListNode fast = newHead, slow = newHead;
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
            return newHead.next;
        }
    }
}
