package y2021.m8;

/**
 * @ClassName : LongestIncreasingSubsequence
 * @Description: 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：
 *
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到O(n log(n)) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/8/1/19:20
 */
public class LongestIncreasingSubsequence {
    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int res = 1;
        for (int i = 1; i < len; i++) {
            int tmp = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    tmp = Math.max(tmp, dp[j] + 1);
                }
            }
            dp[i] = tmp;
            res = Math.max(res, tmp);
        }
        return res;
    }

    /**
     * 动态规划+二分查找
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        //其中每个元素 tails[k] 的值代表 长度为 k+1 的子序列尾部元素的值。
        int[] tails = new int[len];
        int res = 0;
        for (int num : nums) {
            // res：区间长度
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                // 左区间都小于num
                if (tails[m] < num) {
                    //区间右移
                    i = m + 1;
                    // 左区间都大于num
                } else
                    //区间左移
                    j = m;
            }
            // 第一个大于num的数或者区间内无大于num的数
            // 退出循环条件 i = j
            // tails[(i+1)-1] = num
            tails[i] = num;
            // i = j = res
            // 即num大于tails最右元素
            if (res == j) {
                res++;
            }
        }
        return res;
    }
}
