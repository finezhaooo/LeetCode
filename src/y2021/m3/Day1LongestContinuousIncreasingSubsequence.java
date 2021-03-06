package y2021.m3;

/**
 * @ClassName : Day1LongestContinuousIncreasingSubsequence
 * @Description: 674. 最长连续递增序列
 * @Author zhaooo
 * @Date 2021/3/1/17:12
 *
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开
 *
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1LongestContinuousIncreasingSubsequence {
    static class Solution {
        public int findLengthOfLCIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int left = 0;
            int ans = 1;
            while (left < nums.length) {
                int right = left;
                // 先判断right+1不会越界
                while (right < nums.length - 1 && nums[right + 1] > nums[right]) {
                    right++;
                }
                ans = Math.max(ans, right - left + 1);
                left = right + 1;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}));
    }
}
