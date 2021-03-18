package offer;

import dependecy.TreeNode;

import java.util.ArrayList;

/**
 * @ClassName : T68_1
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/11:47
 */
public class T68_1 {
    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(p.val > q.val) { // 保证 p.val < q.val
                TreeNode tmp = p;
                p = q;
                q = tmp;
            }
            while(root != null) {
                if(root.val < p.val) // p,q 都在 root 的右子树中
                {
                    root = root.right; // 遍历至右子节点
                } else if(root.val > q.val) // p,q 都在 root 的左子树中
                {
                    root = root.left; // 遍历至左子节点
                } else {
                    break;
                }
            }
            return root;
        }
    }
}
