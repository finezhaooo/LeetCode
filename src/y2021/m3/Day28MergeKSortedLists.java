package y2021.m3;

import dependecy.ListNode;

import java.util.*;

/**
 * @ClassName : Day28MergeKSortedLists
 * @Description: 23. 合并K个升序链表
 * @Author zhaooo
 * @Date 2021/3/28/15:36
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day28MergeKSortedLists {
    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            ListNode head = new ListNode(0);
            ListNode result = head;
            // 排序想到优先队列
            PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length,
                    (Comparator.comparingInt(o -> o.val)));
            for (ListNode list : lists) {
                if (list != null) {
                    queue.add(list);
                }
            }
            while (!queue.isEmpty()) {
                head.next = queue.poll();
                head = head.next;
                if (head.next != null) {
                    queue.add(head.next);
                }
            }
            return result.next;
        }
    }
}
