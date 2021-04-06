package y2021.m1;


import dependecy.ListNode;

/**
 * @ClassName : Day2AddTwoNumbers
 * @Description: 2. 两数相加
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。  你可以假设除了数字 0 之外，这两个数都不会以0开头
 * 输入
 * [2,4,3]
 * [5,6,4]
 * 输出
 * [7,0,8]  342 + 465 = 807.
 * 输入：
 * [9,9,9,9,9,9,9]
 * [9,9,9,9]
 * 预期结果：
 * [8,9,9,9,0,0,0,1]
 *
 *
 *
 * class Solution {
 *     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
 *         ListNode pre = new ListNode(0);
 *         ListNode cur = pre;
 *         int carry = 0;
 *         while (l1 != null || l2 != null) {
 *
 *             int v1 = (l1 == null) ? 0 : l1.val;
 *             int v2 = (l2 == null) ? 0 : l2.val;
 *             int sum = v1 + v2 + carry;
 *
 *             cur.next = new ListNode(sum % 10);
 *
 *             carry = sum > 9 ? 1 : 0;
 *             cur = cur.next;
 *             if (l1 != null) {
 *                 l1 = l1.next;
 *             }
 *             if (l2 != null) {
 *                 l2 = l2.next;
 *             }
 *         }
 *
 *         if (carry == 1) {
 *             cur.next = new ListNode(1);
 *         }
 *         return pre.next;
 *     }
 * }
 *
 *
 * @Author zhaooo
 * @Date 2021/1/30/22:43
 */
public class Day2AddTwoNumbers {

    static class Solution {

        /**
         * 从此节点开始向后+temp
         *
         * @param listNode
         * @param temp
         */
        public void addTemp(ListNode listNode, int temp) {
            int sumTemp;
            ListNode prev = listNode;
            while (listNode != null && temp != 0) {
                sumTemp = listNode.val + temp;
                temp = sumTemp / 10;
                listNode.val = sumTemp % 10;
                prev = listNode;
                listNode = listNode.next;
            }
            if (temp > 0) {
                prev.next = new ListNode(temp);
            }
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int temp = 0;
            int sumTemp;
            ListNode result = l1;
            while (true) {
                sumTemp = l1.val + l2.val + temp;
                temp = sumTemp / 10;
                l1.val = sumTemp % 10;
                //保证l1 l2 同步
                if (l1.next != null&&l2.next != null){
                    l1 = l1.next;
                    l2 = l2.next;
                }else {
                    if (l1.next == null){
                        if (l2.next != null){
                            l1.next = l2.next;
                            addTemp(l1.next,temp);
                        }else {
                            if (temp>0){
                                l1.next = new ListNode(temp);
                            }
                        }
                    }else {
                        addTemp(l1.next,temp);
                    }
                    return result;
                }
            }
        }
    }

    static public ListNode newNodes(int[] array) {
        ListNode listNode = new ListNode();
        ListNode result = listNode;
        for (int i : array) {
            listNode.next = new ListNode(i);
            listNode = listNode.next;
        }
        return result.next;
    }

    public static void main(String[] args) {
        int[] a = {2,4,9};
        int[] b = {5,6,4,9};
        Solution solution = new Solution();
        ListNode l1 = newNodes(a);
        ListNode l2 = newNodes(b);
        ListNode l3 = solution.addTwoNumbers(l1, l2);
        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }
}
