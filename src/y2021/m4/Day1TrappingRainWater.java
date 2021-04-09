package y2021.m4;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : Day1TrappingRainWater
 * @Description: 42. 接雨水
 * @Author zhaooo
 * @Date 2021/4/1/12:28
 * <p>
 * 给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1TrappingRainWater {
    static class Solution {
        public int trap(int[] height) {
            int result = 0;
            int left = 0;
            int right = height.length - 1;
            while (left < height.length - 1 && height[left] <= height[left + 1]) {
                left++;
            }
            while (right > left && height[right] <= height[right - 1]) {
                right--;
            }
            while (left < right) {
                // left较小
                if (height[left] <= height[right]) {
                    int solid = 0;
                    int min = height[left];
                    int leftIndex = left;
                    do {
                        solid += height[left];
                        left++;
                    } while (left < right && height[left] < min);
                    result += (left - leftIndex) * min - solid;
                    //  right较小
                } else {
                    int solid = 0;
                    int min = height[right];
                    int rightIndex = right;
                    do {
                        solid += height[right];
                        right--;
                    } while (left < right && height[right] < min);
                    result += (rightIndex - right) * min - solid;
                }
            }
            return result;
        }

        // 单调栈
        public int trap2(int[] height) {
            int result = 0;
            int len = height.length;
            Deque<Integer> stack = new ArrayDeque<>(len);
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && height[i] > height[stack.peekLast()]) {
                    int top = stack.removeLast();
                    if (stack.isEmpty()) {
                        break;
                    }
                    result += (i - stack.peekLast() - 1) *
                            (Math.min(height[i], height[stack.peekLast()]) - height[top]);
                }
                stack.addLast(i);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.trap(new int[]{3, 0, 4, 0, 2});
        System.out.println(result);
    }
}
