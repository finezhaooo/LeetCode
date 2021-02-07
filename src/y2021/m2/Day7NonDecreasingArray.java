package y2021.m2;

/**
 * @ClassName : Day7NonDecreasingArray
 * @Description: 665. 非递减数列
 * <p>
 * 给你一个长度为n的整数数组，请你判断在最多改变1个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的：对于数组中所有的i(0<=i<=n-2),总满足nums[i]<=nums[i+1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * <p>
 * 题解：https://leetcode-cn.com/problems/non-decreasing-array/solution/tu-jie-zheng-ming-zui-duo-yi-ge-feng-gu-6l32k/
 * 将数列看做折线段 出现一个逆序 左侧为峰 右侧为谷
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/2/7/14:57
 */
public class Day7NonDecreasingArray {
    static class Solution {
        public boolean checkPossibility(int[] nums) {
            int index = 0;
            int counter = 0;
            // 逆序出现次数大于等于2 就不能只改变一次变成非递减序列
            while (index < nums.length - 1 && counter < 2) {
                if (nums[index] > nums[index + 1]) {
                    // 逆序出现 counter++
                    counter++;
                    //    3       4       2       3
                    //       index(峰) index+1(谷)
                    // 若 index = 0 即峰为最左侧元素 则改变最左侧元素小于右侧即可 不可能出现不能变为非逆序情况
                    if (index > 0) {
                        // 峰位于中间情况
                        // 如果峰左侧大于峰右侧 则不可能改变峰来变成逆序
                        if (nums[index - 1] > nums[index + 1]) {
                            // 若 index + 1 = num.length - 1 即谷为最右侧元素 则改变最右侧元素大于左侧即可 不可能出现不能变为非逆序情况
                            if (index + 1 < nums.length - 1) {
                                // 谷位于中间情况
                                // 如果谷左侧大于峰右侧 则不可能改变谷来变成逆序
                                if (nums[index] > nums[index + 2]) {
                                    // counter++ 跳出循环 不能变为逆序
                                    counter++;
                                }
                            }
                        }
                    }
                }
                index++;
            }
            return counter < 2;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{5, 7, 1, 8};
        System.out.println(solution.checkPossibility(nums));
    }
}
