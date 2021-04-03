package y2021.m3;

/**
 * @ClassName : Day31SearchInRotatedSortedArray
 * @Description: 33. 搜索旋转排序数组
 * @Author zhaooo
 * @Date 2021/3/31/13:15
 * <p>
 * <p>
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 * <p>
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 */
public class Day31SearchInRotatedSortedArray {
    static class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (right + left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[left] < nums[right]) {
                    return binarySearch(nums, left, right, target);
                } else if (nums[left] > nums[right]) {
                    // 交界 > mid
                    if (nums[mid] > nums[right]) {
                        if (target < nums[mid] && target > nums[right]) {
                            return binarySearch(nums, left, mid - 1, target);
                        } else {
                            left = mid + 1;
                        }
                    //  交界 < mid
                    } else if (nums[mid] < nums[right]) {
                        if (target > nums[mid] && target < nums[left]) {
                            return binarySearch(nums, mid + 1, right, target);
                        } else {
                            right = mid - 1;
                        }
                    }
                } else {
                    // nums[left] = nums[right] 即只有一个元素
                    return nums[left] == target ? left : -1;
                }
            }
            return -1;
        }

        public int binarySearch(int[] nums, int left, int right, int target) {
            while (left <= right) {
                int mid = (right + left) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }
    }
}
