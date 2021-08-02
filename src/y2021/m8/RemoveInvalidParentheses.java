package y2021.m8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : RemoveInvalidParentheses
 * @Description: 301. 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 * 示例 2：
 *
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 * 示例 3：
 *
 * 输入：s = ")("
 * 输出：[""]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/8/2/19:01
 */
public class RemoveInvalidParentheses {

    int len;
    char[] chArr;
    private final Set<String> validExpressions = new HashSet<>();

    /**
     * dfs+剪枝
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        // 多余的左右括号
        int left = 0, right = 0;
        chArr = s.toCharArray();
        len = chArr.length;
        for (char c : chArr) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        dfs(0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<>(this.validExpressions);
    }

    // 深度优先
    private void dfs(int index, int leftNum, int rightNum, int leftRm, int rightRm, StringBuilder path) {
        if (index == len) {
            if (leftRm == 0 && rightRm == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }
        char ch = chArr[index];
        if (ch == '(' && leftRm > 0) {
            dfs(index + 1, leftNum, rightNum, leftRm - 1, rightRm, new StringBuilder(path));
        }
        if (ch == ')' && rightRm > 0) {
            dfs(index + 1, leftNum, rightNum, leftRm, rightRm - 1, new StringBuilder(path));
        }
        //不移除
        path.append(ch);
        if (ch == '(') {
            dfs(index + 1, leftNum + 1, rightNum, leftRm, rightRm, new StringBuilder(path));
        } else if (ch == ')') {
            // 保证左括号大于右括号，才是合法匹配
            if (leftNum > rightNum) {
                dfs(index + 1, leftNum, rightNum + 1, leftRm, rightRm, new StringBuilder(path));
            } else {
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            dfs(index + 1, leftNum, rightNum, leftRm, rightRm, new StringBuilder(path));
        }
    }

    /**
     * dfs 改进版
     * 不在每次遍历时 new StringBuilder(path)
     * @param index       当前遍历到的下标
     * @param leftCount   已经遍历到的左括号的个数
     * @param rightCount  已经遍历到的右括号的个数
     * @param leftRemove  最少应该删除的左括号的个数
     * @param rightRemove 最少应该删除的右括号的个数
     * @param path        一个可能的结果
     */
    private void dfs2(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        char character = chArr[index];
        // 可能的操作 1：删除当前遍历到的字符
        if (character == '(' && leftRemove > 0) {
            // 由于 leftRemove > 0，并且当前遇到的是左括号，因此可以尝试删除当前遇到的左括号
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (character == ')' && rightRemove > 0) {
            // 由于 rightRemove > 0，并且当前遇到的是右括号，因此可以尝试删除当前遇到的右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }

        // 可能的操作 2：保留当前遍历到的字符
        path.append(character);
        if (character != '(' && character != ')') {
            // 如果不是括号，继续深度优先遍历
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (character == '(') {
            // 考虑左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) {
            // 考虑右括号
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        // 遍历完成，回溯
        // 每dfs一次完成，删除添加的元素，可以进行下一次不同的dfs
        path.deleteCharAt(path.length() - 1);
    }
}
