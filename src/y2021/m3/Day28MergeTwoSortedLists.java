package y2021.m3;

import dependecy.ListNode;

/**
 * @ClassName : Day28MergeTwoSortedLists
 * @Description: 21. 合并两个有序链表
 * @Author zhaooo
 * @Date 2021/3/28/11:54
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day28MergeTwoSortedLists {
    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode newHead = new ListNode(0);
            ListNode result = newHead;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    newHead.next = l1;
                    newHead = newHead.next;
                    l1 = l1.next;
                } else {
                    newHead.next = l2;
                    newHead = newHead.next;
                    l2 = l2.next;
                }
            }
            newHead.next = l1 != null ? l1 : l2;
            return result.next;
        }
    }
}
