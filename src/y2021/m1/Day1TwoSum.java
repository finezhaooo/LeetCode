package y2021.m1;

/**
 * @ClassName : Day1_TwoSum
 * @Description: 1. 两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * @Author zhaooo
 * @Date 2021/1/29/23:42
 */
public class Day1TwoSum {
    public static void main(String[] args) {
        class Solution {
            public int[] twoSum(int[] nums, int target) {
                for (int i = 0; i < nums.length; i++) {
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] + nums[i] == target) {
                            return new int[]{i, j};
                        }
                    }
                }
                return null;
            }
        }
    }
}
