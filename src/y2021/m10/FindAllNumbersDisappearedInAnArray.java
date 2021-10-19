package y2021.m10;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : FindAllNumbersDisappearedInAnArray
 * @Description: 448. 找到所有数组中消失的数字
 * @Author zhaooo
 * @Date 2021/10/2/11:00
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindAllNumbersDisappearedInAnArray {
    /**
     * 使用额外空间
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        boolean[] count = new boolean[len + 1];
        LinkedList<Integer> res = new LinkedList<>();
        for (int num : nums) {
            count[num] = true;
        }
        for (int i = 1; i < count.length; i++) {
            if (!count[i]) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 利用nums
     * 找到就在nums[i-1]上加n
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
