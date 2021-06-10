package y2021.m5;

import dependecy.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Day19ValidateBinarySearchTree
 * @Description: 98. 验证二叉搜索树
 * @Author zhaooo
 * @Date 2021/5/19/16:51
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day19ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        mid(root, res);
        Integer[] resArr = res.toArray(new Integer[0]);
        for (int i = 1; i < res.size(); i++) {
            if (resArr[i - 1] >= resArr[i]) {
                return false;
            }
        }
        return true;
    }

    public void mid(TreeNode treeNode, LinkedList<Integer> res) {
        if (treeNode == null) {
            return;
        }
        mid(treeNode.left, res);
        res.add(treeNode.val);
        mid(treeNode.right, res);
    }
}
