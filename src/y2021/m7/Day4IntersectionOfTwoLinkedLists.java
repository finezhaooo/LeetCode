package y2021.m7;

import dependecy.ListNode;

/**
 * @ClassName : Day4IntersectionOfTwoLinkedLists
 * @Description: 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 提示：
 *
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 *
 *
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/4/18:48
 */
public class Day4IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 0, lenB = 0;
        ListNode tmpLong = headA;
        ListNode tmpShort = headB;
        while (tmpLong != null) {
            tmpLong = tmpLong.next;
            lenA++;
        }
        while (tmpShort != null) {
            tmpShort = tmpShort.next;
            lenB++;
        }
        if (lenA > lenB) {
            tmpLong = headA;
            tmpShort = headB;
        } else {
            tmpLong = headB;
            tmpShort = headA;
        }
        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            tmpLong = tmpLong.next;
        }
        while (tmpLong != tmpShort) {
            tmpLong = tmpLong.next;
            tmpShort = tmpShort.next;
        }
        return tmpLong;
    }

    /**
     * 简化版
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        // pa 和 pb 会回到同一起点
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
