package y2021.m4;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @ClassName : Day4MinimumPathSum
 * @Description: 64. 最小路径和
 * @Author zhaooo
 * @Date 2021/4/4/14:25
 * <p>
 * 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day4MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        dp[0][0] = grid[0][0];
        return getSum(grid, dp, grid.length - 1, grid[0].length - 1);
    }

    public int getSum(int[][] grid, int[][] dp, int i, int j) {
        if (dp[i][j] == -1) {
            if (i == 0) {
                dp[i][j] = getSum(grid, dp, i, j - 1) + grid[i][j];
            } else if (j == 0) {
                dp[i][j] = getSum(grid, dp, i - 1, j) + grid[i][j];
            } else {
                dp[i][j] = Math.min(getSum(grid, dp, i - 1, j) + grid[i][j], getSum(grid, dp, i, j - 1) + grid[i][j]);
            }
        }
        return dp[i][j];
    }
}
