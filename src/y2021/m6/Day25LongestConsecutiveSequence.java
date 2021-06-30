package y2021.m6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : Day25LongestConsecutiveSequence
 * @Description: 128. 最长连续序列
 * @Author zhaooo
 * @Date 2021/6/25/14:28
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * maxPathSumDAXdax
 *
 * 进阶：你可以设计并实现时间复杂度为maxPathSumO(n) 的解决方案吗？
 *
 * maxPathSum
 *`
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * maxPathSum
 *
 * 提示：
 *
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day25LongestConsecutiveSequence {
    /**
     * 排序
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int res = Math.min(nums.length, 1);
        int tmp = 1;
        nums = Arrays.stream(nums).distinct().sorted().toArray();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 == nums[i - 1]) {
                tmp++;
                res = Math.max(tmp, res);
            } else {
                tmp = 1;
            }
        }
        return res;
    }

    /**
     * O(n) 哈希表
     * @param nums
     * @return
     * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     */
    public int longestConsecutive1(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }
        int longestStreak = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
