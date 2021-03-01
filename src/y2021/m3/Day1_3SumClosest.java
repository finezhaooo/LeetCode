package y2021.m3;

import java.util.Arrays;

/**
 * @ClassName : Day1_3SumClosest
 * @Description: 16. 最接近的三数之和
 * @Author zhaooo
 * @Date 2021/3/1/14:23
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3<= nums[i]<= 10^3
 * -10^4<= target<= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1_3SumClosest {
    static class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length;
            int best = 10000000;

            // 枚举 a
            for (int i = 0; i < n; ++i) {
                // 保证和上一次枚举的元素不相等
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 使用双指针枚举 b 和 c
                int j = i + 1, k = n - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    // 如果和为 target 直接返回答案
                    if (sum == target) {
                        return target;
                    }
                    // 根据差值的绝对值来更新答案
                    if (Math.abs(sum - target) < Math.abs(best - target)) {
                        best = sum;
                    }
                    if (sum > target) {
                        // 如果和大于 target，移动 c 对应的指针
                        int k0 = k - 1;
                        // 移动到下一个不相等的元素
                        while (j < k0 && nums[k0] == nums[k]) {
                            --k0;
                        }
                        k = k0;
                    } else {
                        // 如果和小于 target，移动 b 对应的指针
                        int j0 = j + 1;
                        // 移动到下一个不相等的元素
                        while (j0 < k && nums[j0] == nums[j]) {
                            ++j0;
                        }
                        j = j0;
                    }
                }
            }
            return best;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 2, 1, -4};
        System.out.println(solution.threeSumClosest(nums, 1));
    }
}
