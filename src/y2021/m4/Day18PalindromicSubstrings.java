package y2021.m4;

import java.util.Arrays;

/**
 * @ClassName : Day18PalindromicSubstrings
 * @Description: 647. 回文子串
 * @Author zhaooo
 * @Date 2021/4/18/21:56
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入的字符串长度不会超过 1000 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day18PalindromicSubstrings {
    // 动态规划
    public int countSubstrings(String s) {
        int len = s.length();
        int res = len;
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 前面是回文子串
                    // j==i+1 判断是否从2个数开始
                    if (j == i + 1 || dp[i + 1][j - 1]) {
                        res++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return res;
    }

    // dfs+减枝
    public int countSubstrings2(String s) {
        int len = s.length();
        int[] result = new int[1];
        for (int i = 0; i < s.length(); i++) {
            // 中间是一个单独的
            dfs(i, i, s, result);
            // 中间是两个相同的
            dfs(i, i + 1, s, result);
        }
        return result[0];
    }

    public void dfs(int start, int end, String s, int[] result) {
        if (start < 0 || end >= s.length()) {
            return;
        }
        if (s.charAt(start) == s.charAt(end)) {
            result[0]++;
            dfs(--start, ++end, s, result);
        }
    }
}
