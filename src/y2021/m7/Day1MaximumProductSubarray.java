package y2021.m7;

/**
 * @ClassName : Day1MaximumProductSubarra
 * @Description: 152. 乘积最大子数组
 * @Author zhaooo
 * @Date 2021/7/1/17:47
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 以前一个结尾的最大正数值
        int prePos = 0;
        // 以前一个结尾的最小负数值
        int preNeg = 0;
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            // 防止prePos先更新影响preNeg
            int tmpPos;
            if (num > 0) {
                tmpPos = Math.max(prePos * num, num);
                // preNeg为0表示下一次preNeg不参与计算；即下一个子数组不包含num
                preNeg = Math.min(preNeg * num, 0);
            } else {
                // prePos同理
                tmpPos = Math.max(preNeg * num, 0);
                preNeg = Math.min(prePos * num, num);
            }
            prePos = tmpPos;
            res = Math.max(res, prePos);
        }
        return res;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            // 只与i-1有关 可以优化为使用一个变量
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    /**
     * 简化
     * @param nums
     * @return
     */
    public int maxProduct3(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}
