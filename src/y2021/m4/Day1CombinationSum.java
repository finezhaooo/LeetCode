package y2021.m4;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Day1CombinationSum
 * @Description: 39. 组合总和
 * @Author zhaooo
 * @Date 2021/4/1/11:19
 * <p>
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1CombinationSum {
    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new LinkedList<>();
            next(candidates, target, result, new LinkedList<>(), 0);
            return result;
        }

        public void next(int[] candidates, int target, List<List<Integer>> result, LinkedList<Integer> tmp, int i) {
            if (target == 0) {
                result.add(tmp);
                return;
            }
            for (; i < candidates.length; i++) {
                if (target - candidates[i] >= 0) {
                    LinkedList<Integer> list = new LinkedList<>(tmp);
                    list.add(candidates[i]);
                    next(candidates, target - candidates[i], result, list, i);
                }
            }
        }
    }
}
