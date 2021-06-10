package y2021.m5;

import dependecy.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName : Day17CousinsInBinaryTree
 * @Description: 993. 二叉树的堂兄弟节点
 * @Author zhaooo
 * @Date 2021/5/17/14:57
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点数介于2 到100之间。
 * 每个节点的值都是唯一的、范围为1 到100的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day17CousinsInBinaryTree {
    public int deepNode(TreeNode root, int n, int deep) {
        if(root != null){
            if(root.val == n){
                return deep;
            }
            int l = deepNode(root.left, n , deep + 1);
            int r = deepNode(root.right, n , deep + 1);
            return l == -1 ? r : l;
        }
        return -1;
    }

    public boolean fatherNodeVal(TreeNode root, int x, int y){
        if(root != null){
            if(root.left != null && root.right != null){
                if((root.left.val == x && root.right.val == y) || (root.left.val == y && root.right.val == x)){
                    return false;
                }
            }
            return fatherNodeVal(root.left, x, y) && fatherNodeVal(root.right, x, y);
        }
        return true;
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        return deepNode(root, x, 0) == deepNode(root, y, 0) && fatherNodeVal(root, x, y);
    }
}
