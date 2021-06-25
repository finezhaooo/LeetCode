package y2021.m6;

import dependecy.TreeNode;

import java.util.HashMap;

/**
 * @ClassName : Day20BinaryTreeMaximumPathSum
 * @Description: 124. 二叉树中的最大路径和
 * @Author zhaooo
 * @Date 2021/6/20/1:39
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 *
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day20BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumWithRoot(root);
        return max;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public int maxPathSumWithRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumWithRoot(root.left);
        int right = maxPathSumWithRoot(root.right);
        int current = root.val;
        // 路径不可以有分叉，只能包含左孩子或右孩子其中一个
        int tmp = Math.max(Math.max(current + left, current + right), current);
        // 最大值可以同时包含左右孩子 表示已经结束该路径，不可能向上返回
        max = Math.max(Math.max(current + left + right, tmp), max);
        return tmp;
    }
}
