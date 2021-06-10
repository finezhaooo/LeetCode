package y2021.m5;

import dependecy.TreeNode;

import java.util.*;

/**
 * @ClassName : LeafSimilarTrees
 * @Description: 872. 叶子相似的树
 * @Author zhaooo
 * @Date 2021/5/10/16:19
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个叶值序列 。
 * <p>
 * <p>
 * <p>
 * 举个例子，如上图所示，给定一棵叶值序列为(6, 7, 4, 9, 8)的树。
 * <p>
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是叶相似的。
 * <p>
 * 如果给定的两个根结点分别为root1 和root2的树是叶相似的，则返回true；否则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 * 示例 5：
 * <p>
 * <p>
 * <p>
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定的两棵树可能会有1到 200个结点。
 * 给定的两棵树上的值介于 0 到 200 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/leaf-similar-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day10LeafSimilarTrees {
    /**
     * 每次判断一对节点
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.addLast(root1);
        stack2.addLast(root2);
        int val1;
        int val2;
        do {
            val1 = getLeaf(stack1);
            val2 = getLeaf(stack2);
            if (val1 != val2) {
                return false;
            }
        } while (val1 != -1);
        return true;
    }

    /**
     * 获取叶子节点 非递归
     *
     * @param stack
     * @return
     */
    public int getLeaf(Deque<TreeNode> stack) {
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeLast();
            if (node.left == null && node.right == null) {
                System.out.println(node.val);
                return node.val;
            }
            if (node.left != null) {
                stack.addLast(node.left);
            }
            if (node.right != null) {
                stack.addLast(node.right);
            }
        }
        return -1;
    }

    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        // 满二叉树叶子节点最多100个
        ArrayList<Integer> list1 = new ArrayList<>(100);
        ArrayList<Integer> list2 = new ArrayList<>(100);
        dfs(list1,root1);
        dfs(list2,root2);
        if (list1.size()==list2.size()) {
            return Arrays.equals(list1.toArray(),list2.toArray());
        }
        return false;
    }

    public void dfs(List<Integer> res, TreeNode node) {
        if (node==null){
            return;
        }
        if (node.left == null && node.right == null) {
            res.add(node.val);
            return;
        }
        dfs(res,node.left);
        dfs(res,node.right);
    }
}
