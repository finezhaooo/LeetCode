package y2021.m8;

import dependecy.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : HouseRobberIII
 * @Description: 337. 打家劫舍 III
 * @Author zhaooo
 * @Date 2021/8/26/13:00
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \ 
 *      3   1
 *
 * 输出: 7 
 * 解释:小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \ 
 *  1   3   1
 *
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额= 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HouseRobberIII {
    /**
     * 一个哈希表表示
     */
    HashMap<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        return Math.max(maxWithCurNode(root), maxWithoutCurNode(root));
    }

    //包含当前节点的最大值
    public int maxWithCurNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        int left = maxWithoutCurNode(node.left);
        int right = maxWithoutCurNode(node.right);
        map.put(node, node.val + left + right);
        return node.val + left + right;
    }

    //不包含当前节点的最大值
    public int maxWithoutCurNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxWithCurNode(node.left), maxWithoutCurNode(node.left))
                + Math.max(maxWithCurNode(node.right), maxWithoutCurNode(node.right));
    }

    /**
     * 动态规划
     * 2个哈希表表示
     */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob2(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /**
     * 动态规划优化
     * 只与2个状态有关
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        int[] rootStatus = dfs2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }
}
