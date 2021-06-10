package y2021.m5;

import dependecy.TreeNode;

/**
 * @ClassName : Day22FlattenBinaryTreeToLinkedList
 * @Description: 114. 二叉树展开为链表
 * @Author zhaooo
 * @Date 2021/5/22/14:11
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day22FlattenBinaryTreeToLinkedList {

    /**
     * 解法一
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     * //将 1 的左子树插入到右子树的地方
     *     1
     *      \
     *       2         5
     *      / \         \
     *     3   4         6
     * //将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *      / \
     *     3   4
     *          \
     *           5
     *            \
     *             6
     *
     *  //将 2 的左子树插入到右子树的地方
     *     1
     *      \
     *       2
     *        \
     *         3       4
     *                  \
     *                   5
     *                    \
     *                     6
     *
     *  //将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *        \
     *         3
     *          \
     *           4
     *            \
     *             5
     *              \
     *               6
     *
     *   ......
     *
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            // 左子树为空
            if (root.left == null) {
                root = root.right;
            }
            // 有左子树
            else {
                TreeNode pre = root.left;
                // 找到最右节点
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 右子树放在pre后面
                pre.right = root.right;
                // 左子树放在右边
                root.right = root.left;
                // 左子树指向bull
                root.left = null;
                root = root.right;
            }
        }
    }

    /**
     * 解法2递归
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 的先序遍历为 1->2->3->4->5->6
     * 如果遍历到2时令2的right为pre,遍历到3时令3的right为pre可以得到链表，但是会丢失正在遍历节点的right
     * 如果逆序遍历 6->5->4->3->2->1 遍历到5时,令5的right为pre即可避免未遍历节点的改变。
     * @param root
     */

    TreeNode pre;
    public void flatten2(TreeNode root) {
        if (root==null){
            return;
        }
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}

