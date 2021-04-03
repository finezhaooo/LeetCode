package y2021.m3;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Day28GenerateParentheses
 * @Description: 22. 括号生成
 * @Author zhaooo
 * @Date 2021/3/28/12:45
 * <p>
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day28GenerateParentheses {
    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new LinkedList<String>();
            next(new StringBuilder(1).append('('), n-1, n, result, n * 2);
            return result;
        }

        // left表示剩余左括号个数    right表示剩余右括号个数
        public void next(StringBuilder old, int left, int right, List<String> result, int len) {
            if (left == right) {
                if (left == 0) {
                    result.add(old.toString());
                } else {
                    next(new StringBuilder(len).append(old).append('('), left - 1, right, result, len);
                }
            } else if (left < right) {
                next(new StringBuilder(len).append(old).append(')'), left, right - 1, result, len);
                if (left > 0) {
                    next(new StringBuilder(len).append(old).append('('), left - 1, right, result, len);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateParenthesis(3).forEach(System.out::println);
    }
}
