package y2021.m9;

import java.util.HashMap;

/**
 * @ClassName : SubarraySumEqualsK
 * @Description: 560. 和为 K 的子数组
 * @Author zhaooo
 * @Date 2021/9/12/18:36
 * 给你一个整数数组 nums 和一个整数k ，请你统计并返回该数组中和为k的连续子数组的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int end = 0; end < nums.length; ++end) {
            int sum = 0;
            // 当前序列从后往前计算
            for (int start = end; start >= 0; --start) {
                sum += nums[start];
                // 一直遍历到最后才能结束
                // nums[i]有正有负
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀优化
     * https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        // map<pre,count>
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int num : nums) {
            // pre记录0-i的前缀和
            pre += num;
            // 存在key为pre - k的pre 即两个前缀和的差数组之和为k
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
