package y2021.m4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName : Day1LlongestPalindromicSubsequence
 * @Description: 516. 最长回文子序列
 * @Author zhaooo
 * @Date 2021/4/18/17:43
 * <p>
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * <p>
 * <p>
 * 示例 1:
 * 输入:
 * <p>
 * "bbbab"
 * 输出:
 * <p>
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 * <p>
 * 示例 2:
 * 输入:
 * <p>
 * "cbbd"
 * 输出:
 * <p>
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day18LongestPalindromicSubsequence {
    // https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/shu-ju-jie-gou-he-suan-fa-dong-tai-gui-h-3oqf/
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        // dp[i][j]表示s从i到j的最长回文子序列长度
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
