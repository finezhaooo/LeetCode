package y2021.m3;

import java.util.Arrays;

/**
 * @ClassName : Day29NextRermutation
 * @Description: 31. 下一个排列
 * @Author zhaooo
 * @Date 2021/3/29/12:32
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day29NextPermutation {
    static class Solution {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 1;
            int j = i;
            // 找到从后往前数 第一个升序元素的大的一个index
            for (; i > 0; i--) {
                if (nums[i - 1] < nums[i]) {
                    break;
                }
            }
            if (i == 0) {
                Arrays.sort(nums);
            } else {
                // 找到从后往前第一个大于nums[i-1]的数的index
                for (; j >= i; j--) {
                    if (nums[j] > nums[i - 1]) {
                        break;
                    }
                }
                int tmp = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = tmp;
                Arrays.sort(nums, i, nums.length);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nextPermutation(new int[]{1,2,3,8,5,7,6,4});
    }
}
