package y2021.m7;

import dependecy.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : Day15LowestCommonAncestorOfABinaryTree
 * @Description: 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/15/17:18
 */
public class Day15LowestCommonAncestorOfABinaryTree {

    /**
     * 判断左右孩子
     */
    TreeNode ans = null;

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        // 先递归
        boolean lDfs = dfs(root.left, p, q);
        boolean rDfs = dfs(root.right, p, q);
        // 递归再返回 只用遍历一次
        if (lDfs && rDfs || ((lDfs || rDfs) && root.val == p.val || root.val == q.val)) {
            ans = root;
        }
        return lDfs || rDfs || p.val == root.val || q.val == root.val;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    /**
     * 使用父节点
     */
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        //从p往上标记
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        //两者相交点及为最近公共祖先
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }


    /**
     * 递归方法1
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // 边界条件
        // 空节点或者该节点是公共祖先的字节的
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        // 后序遍历
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        if (right != null && left != null) {
            return root;
        }
        // 如果左右子树中有一个子问题没得到结果，则返回得到结果的子问题
        return right == null ? left : right;
    }

    /**
     * 递归方法2
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        //这里是思考难点。定义规则：比如在某一棵子树上先找到了p，则无需继续遍历这棵子树，因为即使这棵子树有q，p也一定是q的祖先，也就是它们两个的最近公共祖先。
        if(null == root || root.val == p.val || root.val == q.val) return root;
        //按照上述规则，找到root的左子树的最近公共祖先。
        TreeNode left = lowestCommonAncestor4(root.left, p, q);
        //按照上述规则，找到root的右子树的最近公共祖先。
        TreeNode right = lowestCommonAncestor4(root.right, p, q);
        //一边找到了，一边没找到，根据上述规则，找到的就是最近公共祖先。
        if(null == left) return right;
        if(null == right) return left;
        //如果在左右子树分别找到了p和q，则说明root是它们两个的最近公共祖先。
        return root;
    }
}
