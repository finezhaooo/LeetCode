package y2021.m4;

import java.util.Arrays;

/**
 * @ClassName : Day3MaximumSubarray
 * @Description: 53. 最大子序和
 * @Author zhaooo
 * @Date 2021/4/3/14:56
 * <p>
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 * <p>
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day3MaximumSubarray {
    static class Solution {
        public int maxSubArray(int[] nums) {
            //int[] result = new int[]{nums[0]};
            //pd(nums, nums.length - 1, result);
            //return result[0];

            //return max(nums, 0, nums.length - 1)[2];

            // 动态规划
            int pre = 0, maxAns = nums[0];
            for (int x : nums) {
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }

        // 分而治之
        public int[] max(int[] nums, int start, int end) {
            // {以start为左端点的最大子段和，以end为右端点的最大子段和，最大子段和，区间和}
            int[] result = new int[4];
            if (start == end) {
                Arrays.fill(result, nums[start]);
                return result;
            }
            int[] left = max(nums, start, start + (end - start) / 2);
            int[] right = max(nums, start + (end - start) / 2 + 1, end);
            result[0] = Math.max(left[0], left[3] + right[0]);
            result[1] = Math.max(right[1], left[1] + right[3]);
            // 区间最大子段和，右区间最大子段和，或者跨越左右区间的最大子段和
            result[2] = Math.max(left[1] + right[0], Math.max(left[2], right[2]));
            result[3] = left[3] + right[3];
            return result;
        }

        // 动态规划
        public int pd(int[] nums, int i, int[] max) {
            if (i == 0) {
                return nums[0];
            }
            int tmp = pd(nums, i - 1, max);
            int result = Math.max(nums[i], tmp + nums[i]);
            max[0] = Math.max(max[0], result);
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubArray(new int[]{-1, 3, -4, 2}));
    }
}
