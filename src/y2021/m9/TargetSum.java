package y2021.m9;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : TargetSum
 * @Description: 494. 目标和
 * @Author zhaooo
 * @Date 2021/9/15/19:42
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TargetSum {

    int res = 0;
    Map<String, Integer> cache = new HashMap<>();

    /**
     * dfs  全局变量
     * dfs2 接受返回值
     * dfs3 记忆化搜索
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        //dfs(0, nums, 0, target);
        //return res;

        //return dfs2(0, nums, 0, target);

        return dfs3(nums, target, 0, 0);
    }

    public void dfs(int sum, int[] nums, int k, int target) {
        // k数组下标
        if (k == nums.length - 1) {
            if (sum == target) {
                res++;
            }
            return;
        }
        dfs(sum + nums[k], nums, k + 1, target);
        dfs(sum - nums[k], nums, k + 1, target);
    }

    public int dfs2(int sum, int[] nums, int k, int target) {
        if (k == nums.length - 1) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        return dfs2(sum + nums[k], nums, k + 1, target) + dfs2(sum - nums[k], nums, k + 1, target);
    }

    int dfs3(int[] nums, int target, int k, int sum) {
        // sum可能有负值,不能使用数组
        String key = k + "_" + sum;
        // 包含当前下标计算和
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (k == nums.length) {
            cache.put(key, sum == target ? 1 : 0);
            return cache.get(key);
        }
        int left = dfs3(nums, target, k + 1, sum + nums[k]);
        int right = dfs3(nums, target, k + 1, sum - nums[k]);
        // 当前下标与sum的结果数存入
        cache.put(key, left + right);
        return cache.get(key);
    }

    /**
     * 全量dp
     * dp[i][j]=dp[i−1][j−nums[i−1]]+dp[i−1][j+nums[i−1]]
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        // 确定dp第二维的范围
        for (int i : nums) {
            s += Math.abs(i);
        }
        if (Math.abs(target) > s) {
            return 0;
        }
        // 2*s:将[-s,s]映射到[0,2*s]
        // +1避免边界判断
        int[][] dp = new int[n + 1][2 * s + 1];
        // 相当于dp[0][0] = 1;即在取0个数的情况下sum为0的方法只有一种
        dp[0][0 + s] = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 0; j <= 2 * s; j++) {
                if (j + x >= 0 && j + x <= 2 * s) {
                    dp[i + 1][j + x] += dp[i][j];
                }
                if (j - x >= 0 && j - x <= 2 * s) {
                    dp[i + 1][j - x] += dp[i][j];
                }
            }
        }
        return dp[n][target + s];
    }

    /**
     * dp 优化
     * 
     * 我们可以从哪些数值使用哪种符号来分析，即划分为「负值部分」&「非负值部分」，令「负值部分」的绝对值总和为 m，即可得：
     * (s - m) - m = s - 2 * m = target
     * s - m:正值部分,即正值部分减负值部分=target
     * 变形得：
     * m = (s - target)/2
     *
     * dp[n][m],dp[0][0]=1 为起始条件：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种。
     * 每个数值有「选」和「不选」两种决策，转移方程为：
     * dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]]
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        for (int i : nums) {
            s += Math.abs(i);
        }
        if (Math.abs(target) > s || (s - target) % 2 != 0) {
            return 0;
        }
        int m = (s - target) / 2;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= m; j++) {
                dp[i][j] += dp[i - 1][j];
                if (j >= x) {
                    dp[i][j] += dp[i - 1][j - x];
                }
            }
        }
        return dp[n][m];
    }
}
