package y2021.m7;

/**
 * @ClassName : Day26FindTheDuplicateNumber
 * @Description: 287. 寻找重复数
 * 给定一个包含n + 1 个整数的数组nums ，其数字都在 1 到 n之间（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 *
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3：
 *
 * 输入：nums = [1,1]
 * 输出：1
 * 示例 4：
 *
 * 输入：nums = [1,1,2]
 * 输出：1
 * 
 *
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * 
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/26/18:20
 */
public class Day26FindTheDuplicateNumber {
    /**
     * 二分查找
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        // 循环退出条件
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            // 寻找小于mid的个数cut
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            // cut<=mid 说明重复数在mid右边
            if (cnt <= mid) {
                l = mid + 1;
                // cut>mid 说明重复数在mid左边
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 二进制
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        // 找到length的位数
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                // 该位为1 x++
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                // 该位为1 y++
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            // x大于y的位数是结果的一部分
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * 寻找环的入口（快慢指针）
     * Floyd 判圈算法
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        // 找到起点
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
