package y2021.m10;

import dependecy.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : PathSumIII
 * @Description: 437. 路径总和 III
 * @Author zhaooo
 * @Date 2021/10/23/21:31
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 示例 1：
 *
 * https://assets.leetcode.com/uploads/2021/04/09/pathsum3-1-tree.jpg
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109<= Node.val <= 109
 * -1000<= targetSum<= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PathSumIII {

    int ret = 0;

    /**
     * DFS
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = rootSum(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return ret;
    }

    public int rootSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }

    /**
     * 错误DFS
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        if (targetSum == root.val) {
            return pathSum(root.left, 0) + pathSum(root.right, 0) + 1;
        }
        return pathSum(root.left, targetSum) + pathSum(root.right, targetSum)
                //下层会调用pathSum(root.left, targetSum) + pathSum(root.right, targetSum)导致跳层
                + pathSum(root.left, targetSum - root.val) + pathSum(root.right, targetSum - root.val);

    }

    /**
     * 前缀和
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum3(TreeNode root, int targetSum) {
        // 记录路径中某个前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 防止包含根节点的时候找不到
        map.put(0, 1);
        // 开始搜索
        dfs(root, map, 0, targetSum);
        // 返回值
        return ret;
    }

    private void dfs(TreeNode node, Map<Integer, Integer> map, int currSum, int targetSum) {
        // 递归退出条件
        if (node == null) {
            return;
        }

        // 判断是否存在符合条件的前缀和
        currSum += node.val;
        ret += map.getOrDefault(currSum - targetSum, 0);

        // 将当前前缀和记录下来
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // 继续往下递归   
        dfs(node.left, map, currSum, targetSum);
        dfs(node.right, map, currSum, targetSum);

        // 回溯，恢复状态
        map.put(currSum, map.getOrDefault(currSum, 0) - 1);
    }

    Map<Integer, Integer> map = new HashMap<>();
    int ans, t;

    /**
     * 前缀和
     * @param root
     * @param _t
     * @return
     */
    public int pathSum4(TreeNode root, int _t) {
        if (root == null) {
            return 0;
        }
        t = _t;
        map.put(0, 1);
        dfs(root, root.val);
        return ans;
    }
    void dfs(TreeNode root, int val) {
        if (map.containsKey(val - t)) {
            ans += map.get(val - t);
        }
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) {
            dfs(root.left, val + root.left.val);
        }
        if (root.right != null) {
            dfs(root.right, val + root.right.val);
        }
        map.put(val, map.getOrDefault(val, 0) - 1);
    }
}
