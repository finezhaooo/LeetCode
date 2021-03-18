package offer;

import dependecy.TreeNode;

/**
 * @ClassName : T54
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/11/23:33
 */
public class T54 {

    static class Solution {
        int result,counter;
        public int kthLargest(TreeNode root, int k) {
            counter = k;
            dfs(root);
            return result;
        }
        public void dfs(TreeNode node){
            if (node == null){
                return;
            }
            dfs(node.right);
            if (--counter == 0){
                result = node.val;
            }
            dfs(node.left);
        }
    }
}
