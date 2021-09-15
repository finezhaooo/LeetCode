package newCoder;

import dependecy.TreeNode;

import java.util.*;

/**
 * @ClassName : ThreeOrders
 * @Description:
 * @Author zhaooo
 * @Date 2021/4/10/0:26
 * <p>
 * 题目描述
 * 分别按照二叉树先序，中序和后序打印所有的节点。
 * 示例1
 * 输入
 * 复制
 * {1,2,3}
 * 返回值
 * 复制
 * [[1,2,3],[2,1,3],[2,3,1]]
 * 备注:
 * n \leq 10^6n≤10
 * 6
 */
public class ThreeOrders {
    public int[][] threeOrders(TreeNode root) {
        // write code here
        LinkedList<Integer> pre = new LinkedList<>();
        LinkedList<Integer> in = new LinkedList<>();
        LinkedList<Integer> post = new LinkedList<>();
        pre(pre, root);
        in(in, root);
        post(post, root);
        int[][] result = new int[3][pre.size()];
        result[0] = pre.stream().mapToInt(Integer::valueOf).toArray();
        result[1] = in.stream().mapToInt(Integer::valueOf).toArray();
        result[2] = post.stream().mapToInt(Integer::valueOf).toArray();
        return result;
    }

    public void pre(LinkedList<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        pre(result, node.left);
        pre(result, node.right);
    }

    /**
     * 非递归 栈
     * @param result
     * @param node
     */
    public void pre2(LinkedList<Integer> result, TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(node);
        while (!stack.isEmpty()) {
            TreeNode tmpNode = stack.removeLast();
            result.add(tmpNode.val);
            if (tmpNode.right != null) {
                stack.addLast(tmpNode.right);
            }
            if (tmpNode.left != null) {
                stack.addLast(tmpNode.left);
            }
        }
    }

    public void in(LinkedList<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        in(result, node.left);
        result.add(node.val);
        in(result, node.right);
    }

    /**
     * 非递归
     * @param result
     * @param node
     */
    public void in2(LinkedList<Integer> result, TreeNode node) {
        //1、申请一个栈stack，初始时令cur=head
        //2、先把cur压入栈中，依次把左边界压入栈中，即不停的令cur=cur.left，重复步骤2
        //3、不断重复2，直到为null，从stack中弹出一个节点，记为node，打印node的值，并令cur=node.right,重复步骤2
        //4、当stack为空且cur为空时，整个过程停止。
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
            // 栈中node肯定不为null
            node = stack.removeLast();
            result.add(node.val);
            // 左节点已经访问了
            node = node.right;
        }
    }


    public void post(LinkedList<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        post(result, node.left);
        post(result, node.right);
        result.add(node.val);
    }

    /**
     * 非递归
     * @param result
     * @param node
     */
    public void post2(LinkedList<Integer> result, TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(node);
        while (!stack.isEmpty()) {
            TreeNode tmpNode = stack.removeLast();
            result.add(tmpNode.val);
            if (tmpNode.left != null) {
                stack.addLast(tmpNode.left);
            }
            if (tmpNode.right != null) {
                stack.addLast(tmpNode.right);
            }
        }
    }

    /**
     * 层序遍历
     * @param result
     * @param node
     */
    public void sequenceTraversal(LinkedList<Integer> result, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            result.add(node.val);
            if (node.right != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.left);
            }
        }
    }
}
