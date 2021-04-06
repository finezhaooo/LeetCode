package y2021.m4;

/**
 * @ClassName : Day4EditDistance
 * @Description: 72. 编辑距离
 * @Author zhaooo
 * @Date 2021/4/4/16:17
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day4EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m * n == 0) {
            return m + n;
        }
        //int[][] dp = new int[m][n];
        //return getDistance(dp, m - 1, n - 1, word1, word2);

        //--------------------------------------------------
        // 动态规划2
        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    // 动态规划1
    public int getDistance(int[][] dp, int i, int j, String word1, String word2) {
        // 如果word1="";word2="aa" -> i = -1   j = 1
        if (i < 0) {
            return j + 1;
        } else if (j < 0) {
            return i + 1;
        }
        if (dp[i][j] == 0) {
            int insertWord1 = getDistance(dp, i - 1, j, word1, word2);
            int insertWord2 = getDistance(dp, i, j - 1, word1, word2);
            int changeWord1 = getDistance(dp, i - 1, j - 1, word1, word2);
            if (word1.charAt(i) == word2.charAt(j)) {
                dp[i][j] = Math.min(changeWord1, Math.min(insertWord1 + 1, insertWord2 + 1));
            } else {
                dp[i][j] = Math.min(changeWord1, Math.min(insertWord1, insertWord2)) + 1;
            }
        }
        return dp[i][j];
    }
}
