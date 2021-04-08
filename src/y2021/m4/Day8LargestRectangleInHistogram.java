package y2021.m4;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : Day8LargestRectangleInHistogram
 * @Description: 84. 柱状图中最大的矩形
 * @Author zhaooo
 * @Date 2021/4/8/18:31
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为'[2,1,5,6,2,3]。
 * <p>
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为'10'个单位。
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day8LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int[] newHeights = new int[len + 2];
        // 增加哨兵
        System.arraycopy(heights, 0, newHeights, 1, len);
        int result = 0;
        // 单调栈
        Deque<Integer> stack = new ArrayDeque<>(len + 2);
        stack.addLast(0);
        for (int i = 1; i < len + 2; i++) {
            while (newHeights[i] < newHeights[stack.peekLast()]) {
                int curHeight = newHeights[stack.removeLast()];
                int curWidth = i - stack.peekLast() - 1;
                result = Math.max(result, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return result;
    }
}
