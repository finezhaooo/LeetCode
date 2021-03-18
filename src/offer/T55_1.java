package offer;

import dependecy.TreeNode;

/**
 * @ClassName : T55_1
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/11/23:56
 */
public class T55_1 {
    static class Solution {
        int result = 0;

        public int maxDepth(TreeNode root) {
            //length: 0+1 = 1;
            dfs(root, 1);
            return result;
        }

        public void dfs(TreeNode node, int length) {
            if (node == null) {
                return;
            }
            dfs(node.right, length + 1);
            result = Math.max(result, length);
            dfs(node.left, length + 1);
        }
    }
}
