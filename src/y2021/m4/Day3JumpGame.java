package y2021.m4;

/**
 * @ClassName : Day3JumpGame
 * @Description: 55. 跳跃游戏
 * @Author zhaooo
 * @Date 2021/4/3/22:01
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day3JumpGame {
    static class Solution {
        public boolean canJump(int[] nums) {
            //byte[] pd = new byte[nums.length];
            //pd[nums.length - 1] = 1;
            //return next(0, nums, pd);

            // 贪心算法
            int n = nums.length;
            int rightmost = 0;
            for (int i = 0; i < n; ++i) {
                if (i <= rightmost) {
                    rightmost = Math.max(rightmost, i + nums[i]);
                    if (rightmost >= n - 1) {
                        return true;
                    }
                }
            }
            return false;
        }

        // 动态规划
        public boolean next(int i, int[] nums, byte[] pd) {
            if (pd[i] == 1) {
                return true;
            } else if (pd[i] == -1) {
                return false;
            }
            int j = Math.min(nums.length - 1, i + nums[i]);
            for (; j > i; j--) {
                if (next(j, nums, pd)) {
                    pd[i] = 1;
                    return true;
                } else {
                    pd[i] = -1;
                }
            }
            return false;
        }
    }
}
