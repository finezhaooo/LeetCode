package y2021.m3;

/**
 * @ClassName : Day31FindFirstAndLastPositionOfElementInSortedArray
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
 * @Author zhaooo
 * @Date 2021/3/31/21:53
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109<= nums[i]<= 109
 * nums是一个非递减数组
 * -109<= target<= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day31FindFirstAndLastPositionOfElementInSortedArray {
    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[]{-1, -1};
            int left = 0;
            int right = nums.length - 1;
            if (nums.length == 0 || target < nums[left] || target > nums[right]) {
                return result;
            }
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    searchBoundary(nums, left, mid, target, true, result);
                    searchBoundary(nums, mid, right, target, false, result);
                    break;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        // 搜索边界
        public void searchBoundary(int[] nums, int left, int right, int target, boolean isLeft, int[] result) {
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    if (isLeft) {
                        if (mid == 0 || nums[mid - 1] != target) {
                            result[0] = mid;
                            break;
                        } else {
                            right = mid - 1;
                        }
                    } else {
                        if (mid == nums.length - 1 || nums[mid + 1] != target) {
                            result[1] = mid;
                            break;
                        } else {
                            left = mid + 1;
                        }
                    }
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
    }
}
