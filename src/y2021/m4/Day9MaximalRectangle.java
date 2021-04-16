package y2021.m4;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : Day9MaximalRectangle
 * @Description: 85. 最大矩形
 * @Author zhaooo
 * @Date 2021/4/9/13:53
 * <p>
 * 给定一个仅包含0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * <p>
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day9MaximalRectangle {
    // 单调栈
    // 以第i排为底 循环
    // 与Day8LargestRectangleInHistogram同一个解法
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] height = new int[matrix[0].length + 2];
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;
        // 哨兵
        Deque<Integer> stack = new ArrayDeque<>(cols + 2);
        for (int i = 0; i < rows; i++) {
            stack.clear();
            stack.addLast(0);
            for (int j = 0; j <= cols; ) {
                if (j == cols || matrix[i][j] == '0') {
                    height[++j] = 0;
                } else {
                    height[++j]++;
                }
                // j = 在matrix的下标+1 因为前后都有一个哨兵
                while (height[stack.peekLast()] > height[j]) {
                    int curHeight = height[stack.removeLast()];
                    int curWidth = j - stack.peekLast() - 1;
                    max = Math.max(max, curHeight * curWidth);
                }
                stack.addLast(j);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Day9MaximalRectangle day = new Day9MaximalRectangle();
        char[][] Matrix = new char[][]{{'1', '1'}, {'1', '1'}};
        System.out.println(day.maximalRectangle(Matrix));
        //day.maximalRectangle(Matrix);
    }
}
