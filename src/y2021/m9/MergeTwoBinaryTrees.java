package y2021.m9;

import dependecy.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName : mergeTwoBinaryTrees
 * @Description: 617. 合并二叉树
 * @Author zhaooo
 * @Date 2021/9/10/16:44
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例1:
 *
 * 输入: 
 * 	Tree 1                     Tree 2                  
 *           1                         2                             
 *          / \                       / \                            
 *         3   2                     1   3                        
 *        /                           \   \                      
 *       5                             4   7                  
 * 输出: 
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \ 
 * 	 5   4   7
 * 注意:合 并必须从两个树的根节点开始。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoBinaryTrees {

    /**
     * 递归
     * 全部建立新节点
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
        TreeNode newNode = null;
        if (node1 != null && node2 != null) {
            newNode = new TreeNode(node2.val + node1.val);
            newNode.left = mergeTrees(node1.left, node2.left);
            newNode.right = mergeTrees(node1.right, node2.right);
        } else {
            if (node2 != null) {
                newNode = new TreeNode(node2.val);
                newNode.left = mergeTrees(null, node2.left);
                newNode.right = mergeTrees(null, node2.right);
            } else if (node1 != null) {
                newNode = new TreeNode(node1.val);
                newNode.left = mergeTrees(node1.left, null);
                newNode.right = mergeTrees(node1.right, null);
            }
        }
        return newNode;
    }

    /**
     * dfs
     * 使用了原节点,改变了原二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        //剪枝
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees2(t1.left, t2.left);
        merged.right = mergeTrees2(t1.right, t2.right);
        return merged;
    }

    /**
     * bfs
     * 初始时将每个二叉树的根节点分别加入相应的队列。每次从每个队列中取出一个节点，判断两个原始二叉树的节点的左右子节点是否为空。
     * 如果两个原始二叉树的当前节点中至少有一个节点的左子节点不为空，则合并后的二叉树的对应节点的左子节点也不为空。对于右子节点同理。
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue.offer(merged);
        queue1.offer(t1);
        queue2.offer(t2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else if (left2 != null) {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return merged;
    }

}
