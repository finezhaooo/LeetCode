package newCoder;

import dependecy.TreeNode;

import java.util.LinkedList;
import java.util.List;

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
        LinkedList<Integer> mid = new LinkedList<>();
        LinkedList<Integer> last = new LinkedList<>();
        pre(pre, root);
        mid(mid, root);
        last(last, root);
        int[][] result = new int[3][pre.size()];
        result[0] = pre.stream().mapToInt(Integer::valueOf).toArray();
        result[1] = mid.stream().mapToInt(Integer::valueOf).toArray();
        result[2] = last.stream().mapToInt(Integer::valueOf).toArray();
        return result;
    }

    public void pre(LinkedList<Integer> result, TreeNode node) {
        if (node==null){
            return;
        }
        result.add(node.val);
        pre(result, node.left);
        pre(result, node.right);
    }

    public void mid(LinkedList<Integer> result, TreeNode node) {
        if (node==null){
            return;
        }
        mid(result, node.left);
        result.add(node.val);
        mid(result, node.right);
    }

    public void last(LinkedList<Integer> result, TreeNode node) {
        if (node==null){
            return;
        }
        last(result, node.left);
        last(result, node.right);
        result.add(node.val);
    }
}
