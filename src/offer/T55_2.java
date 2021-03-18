package offer;

import dependecy.TreeNode;

/**
 * @ClassName : T55_2
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/0:05
 */
public class T55_2 {
    static class Solution {

        public boolean isBalanced(TreeNode root) {
            return recur(root) != -1;
        }

        public int recur(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = recur(node.left);
            if (left == -1) {
                return -1;
            }
            int right = recur(node.right);
            if (right == -1) {
                return -1;
            }
            return Math.abs(right - left) > 1 ? -1 : Math.max(right, left) + 1;
        }
    }
}
