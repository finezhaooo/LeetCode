package y2021.m5;

import dependecy.TreeNode;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName : Day21ConstructBinaryTreeFromPreorderAndInorderTraversal
 * @Description: 105. 从前序与中序遍历序列构造二叉树
 * @Author zhaooo
 * @Date 2021/5/21/13:39
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder =[3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * !对于任意一颗树而言，前序遍历的形式总是
 * ![ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
 * !即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是
 * ![ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 */
public class Day21ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        int inRoot = indexMap.get(preorder[preLeft]);
        TreeNode node = new TreeNode(preorder[preLeft]);
        int leftTreeSize = inRoot - inLeft;
        node.left = myBuildTree(preorder, inorder, preLeft + 1, preLeft + leftTreeSize, inLeft, inRoot - 1);
        node.right = myBuildTree(preorder, inorder, preLeft + leftTreeSize + 1, preRight, inRoot + 1, inRight);
        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
