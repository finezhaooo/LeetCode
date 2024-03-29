package y2021.m3;

import dependecy.ListNode;

/**
 * @ClassName : Day1MiddleOfTheLinkedList
 * @Description: 876. 链表的中间结点
 * @Author zhaooo
 * @Date 2021/3/1/17:38
 * <p>
 * 给定一个头结点为head的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 示例1:
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点3(序列化形式：[3,4,5])
 * 返回的结点值为3。（测评系统对该结点序列化表述是[3,4,5])。
 * 注意，我们返回了一个ListNode类型的对象ans,这样：
 * ans.val=3,ans.next.val=4,ans.next.next.val=5,以及ans.next.next.next=NULL.
 * 示例2:
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点4(序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为3和4,我们返回第二个结点。
 * 提示：给定链表的结点数介于1和100之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1MiddleOfTheLinkedList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    static class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode[] A = new ListNode[100];
            int t = 0;
            while (head != null) {
                A[t++] = head;
                head = head.next;
            }
            return A[t / 2];
        }
    }
}
