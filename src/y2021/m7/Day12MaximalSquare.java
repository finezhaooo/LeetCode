package y2021.m7;

/**
 * @ClassName : Day12MaximalSquare
 * @Description: 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 *
 *
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/12/17:45
 */
public class Day12MaximalSquare {
    /**
     * 暴力法
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int maxLen = 0;
        int len = 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '1') {
                    do {
                        maxLen = Math.max(maxLen, len);
                    } while ((judge(matrix, i, j, len++)));
                    len = 1;
                }
            }
        }
        return maxLen * maxLen;
    }

    public boolean judge(char[][] matrix, int i, int j, int len) {
        if (i + len >= matrix.length || j + len >= matrix[0].length) {
            return false;
        }
        for (int k = 0; k <= len; k++) {
            if (matrix[i + len][k + j] != '1') {
                return false;
            }
        }
        for (int k = 0; k <= len; k++) {
            if (matrix[k + i][j + len] != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态规划
     * dp[i][j]为以（i,j）为右下角的正方向最大边长
     * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     * 证明：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-2/
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        int maxSide = 0;
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 初始化
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
