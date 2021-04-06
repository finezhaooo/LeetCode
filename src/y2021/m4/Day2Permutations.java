package y2021.m4;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Day2Permutations
 * @Description: 46. 全排列
 * @Author zhaooo
 * @Date 2021/4/2/16:25
 * <p>
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day2Permutations {
    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new LinkedList<>();
            boolean[] used = new boolean[nums.length];
            next(new LinkedList<Integer>(), nums, used, result);
            return result;
        }

        public void next(List<Integer> tmp, int[] nums, boolean[] used, List<List<Integer>> result) {
            if (tmp.size() == nums.length) {
                result.add(tmp);
                return;
            }
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    LinkedList<Integer> list = new LinkedList<>(tmp);
                    list.add(nums[i]);
                    boolean[] newUsed = Arrays.copyOf(used, used.length);
                    newUsed[i] = true;
                    next(list, nums, newUsed, result);
                }
            }
        }
    }
}
