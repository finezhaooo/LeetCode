package y2021.m3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : Day1_3Sum
 * @Description: 15. 三数之和
 * @Author zhaooo
 * @Date 2021/3/1/16:30
 * <p>
 * 给你一个包含n个整数的数组nums,判断nums中是否存在三个元素a,b,c,使得a+b+C=0?请你找出所有和为0且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例1:
 * 输入：nums=[-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例2:
 * 输入：nums=[]
 * 输出：[]
 * 示例3:
 * 输入：nums=[0]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1_3Sum {
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length <= 2) {
                return ans;
            }

            Arrays.sort(nums); // O(nlogn)

            for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
                if (nums[i] > 0) {
                    break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue; // 去掉重复情况
                }
                int target = -nums[i];
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                        // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3]
                        // i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                        left++; right--; // 首先无论如何先要进行加减操作
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {  // nums[left] + nums[right] > target
                        right--;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {

    }
}
