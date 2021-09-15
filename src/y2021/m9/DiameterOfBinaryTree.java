package y2021.m9;

import dependecy.TreeNode;

/**
 * @ClassName : DiameterOfBinaryTree
 * @Description: 543. 二叉树的直径
 * @Author zhaooo
 * @Date 2021/9/14/14:39
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \     
 *       4   5    
 * 返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DiameterOfBinaryTree {
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        high(root);
        return res;
    }

    /**
     * 求包含当前节点的高度
     * @param treeNode
     * @return
     */
    public int high(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int l = high(treeNode.left);
        int r = high(treeNode.right);
        res = Math.max(res, l + r);
        return Math.max(l, r) + 1;
    }
}
