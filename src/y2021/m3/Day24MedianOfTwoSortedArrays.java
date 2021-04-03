package y2021.m3;

/**
 * @ClassName : Day24MedianOfTwoSortedArrays
 * @Description: 4. 寻找两个正序数组的中位数
 * @Author zhaooo
 * @Date 2021/3/24/13:50
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day24MedianOfTwoSortedArrays {
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            int k1 = (n + m + 1) / 2;
            int k2 = (n + m + 2) / 2;
            // 奇偶合并为一种情况
            return (getMedian(nums1, 0, n - 1, nums2, 0, m - 1, k1) +
                    getMedian(nums1, 0, n - 1, nums2, 0, m - 1, k2)) / 2.0;
        }

        public int getMedian(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            // 保证一定是len1先为0
            if (len1 > len2) {
                return getMedian(nums2, start2, end2, nums1, start1, end1, k);
            }
            // 返回第 k 小
            if (len1 == 0) {
                return nums2[start2 + k - 1];
            }
            // 返回第1小
            if (k == 1) {
                return Math.min(nums1[start1], nums2[start2]);
            }
            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;
            if (nums1[i] > nums2[j]) {
                return getMedian(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            } else {
                return getMedian(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }
    }
}
