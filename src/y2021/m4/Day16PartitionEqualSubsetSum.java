package y2021.m4;

import java.util.Arrays;

/**
 * @ClassName : Day16PartitionEqualSubsetSum
 * @Description: 416. 分割等和子集
 * @Author zhaooo
 * @Date 2021/4/16/19:23
 * <p>
 * <p>
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class Day16PartitionEqualSubsetSum {
    // 转换为01背包问题
    // 看看是否有和能为 sum/2
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        // dp[i][j] 表示在nums[0,i]中是否能有和等于j
        boolean[][] dp = new boolean[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < target + 1; j++) {
                dp[i][j] = nums[i] <= j ? dp[i - 1][j] || dp[i - 1][j - nums[i]] : dp[i - 1][j];
            }
        }
        return dp[nums.length - 1][target];
    }
}
