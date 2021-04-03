package y2021.m3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName : Day29LongestValidParentheses
 * @Description: 32. 最长有效括号
 * @Author zhaooo
 * @Date 2021/3/29/16:35
 * <p>
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day29LongestValidParentheses {
    static class Solution {
        // 栈
        public int longestValidParentheses(String s) {
            if (s.length() < 2) {
                return 0;
            }
            int max = 0;
            Stack<Integer> stack = new Stack<>();
            // 栈底为上一个未被匹配的'('
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(i - stack.peek(), max);
                    }
                }
            }
            return max;
        }

        // 动态规划
        public int longestValidParentheses2(String s) {
            if (s.length() < 2) {
                return 0;
            }
            int max = 0;
            // 前面添加一个')'防止越界
            StringBuilder builder = new StringBuilder().append(')').append(s);
            int[] dp = new int[s.length() + 1];
            for (int i = 2; i <= s.length(); i++) {
                if (builder.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = dp[i - 2] + 2;
                    } else if (builder.charAt(i - dp[i - 1] - 1) == '('){
                        dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        // 动态规划2
        public int longestValidParentheses3(String s) {
            int maxans = 0;
            int[] dp = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }
    }
}
