package y2021.m4;

/**
 * @ClassName : Day19copyListWithRandomPointer
 * @Description: 138. 复制带随机指针的链表
 * @Author zhaooo
 * @Date 2021/4/19/14:46
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * 用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 * <p>
 * val：一个表示Node.val的整数。
 * random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 1000
 * -10000 <= Node.val <= 10000
 * Node.random为空（null）或指向链表中的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day19CopyListWithRandomPointer {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        // 新建节点
        while (node != null) {
            Node tmp = new Node(node.val);
            tmp.next = node.next;
            node.next = tmp;
            node = tmp.next;
        }
        node = head;
        // 连接random
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        Node res = new Node(0);
        res.next = head;
        Node cur = res;
        node = head;
        // 分离节点
        while (node != null) {
            cur.next = node.next;
            cur = cur.next;
            node.next = cur.next;
            node = node.next;
        }
        return res.next;
    }
}
