package y2021.m3;

import java.util.Stack;

/**
 * @ClassName : Day28ValidParentheses
 * @Description: 20. 有效的括号
 * @Author zhaooo
 * @Date 2021/3/28/2:15
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day28ValidParentheses {
    static class Solution {
        public boolean isValid(String s) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                int sChar = s.charAt(i);
                if (sChar == '(' || sChar == '[' || sChar == '{') {
                    stack.push(sChar);
                } else {
                    // ASCII码之差
                    if (stack.isEmpty() || (stack.peek() + 2 != sChar && stack.peek() + 1 != sChar)) {
                        return false;
                    }
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }
}
