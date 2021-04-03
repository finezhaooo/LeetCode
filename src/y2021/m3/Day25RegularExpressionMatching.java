package y2021.m3;

import java.util.*;

/**
 * @ClassName : Day25RegularExpressionMatching
 * @Description: 10. 正则表达式匹配
 * @Author zhaooo
 * @Date 2021/3/25/21:26
 * <p>
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length<= 20
 * 0 <= p.length<= 30
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 保证每次出现字符* 时，前面都匹配到有效的字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day25RegularExpressionMatching {
    static class Solution {
        public boolean isMatch(String s, String p) {
            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
            // 空字符匹配
            dp[0][0] = true;
            //"" 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串，其他的是都是false。
            //  奇数位不管什么字符都是false，偶数位为* 时则: dp[0][i] = dp[0][i - 2]
            for (int i = 2; i <= p.length(); i+= 2) {
                if (p.charAt(i - 1) == '*') {
                    dp[0][i] = dp[0][i - 2];
                }
            }
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < p.length(); j++) {
                    // 单个成功匹配
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i + 1][j + 1] = dp[i][j];
                    }
                    else if (p.charAt(j) == '*') {
                        // '*'的前一个和s的当前匹配
                        if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                            dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j - 1];
                        } else {
                            dp[i + 1][j + 1] = dp[i + 1][j - 1];
                        }
                    }
                }
            }
            return dp[s.length()][p.length()];
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        Solution solution = new Solution();
        System.out.println(solution.isMatch(s, p));
    }
}
