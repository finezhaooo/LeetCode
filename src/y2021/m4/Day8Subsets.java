package y2021.m4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Day8Subsets
 * @Description: 78. 子集
 * @Author zhaooo
 * @Date 2021/4/8/13:05
 * 
 * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day8Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        result.add(list);
        for (int i = 0; i < nums.length; i++) {
            getSet(list,i,nums,result);
        }
        return result;
    }

    // 从该节点开始添加
    public void getSet(List<Integer> list, int i, int[] nums, List<List<Integer>> result) {
        if (i < nums.length) {
            LinkedList<Integer> newList = new LinkedList<>(list);
            newList.add(nums[i]);
            result.add(newList);
            for (int j = i + 1; j < nums.length; j++) {
                getSet(newList, j, nums, result);
            }
        }
    }
}
