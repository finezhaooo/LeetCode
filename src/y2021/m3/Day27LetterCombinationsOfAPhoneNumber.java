package y2021.m3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Day25LetterCombinationsOfAPhoneNumber
 * @Description: 17. 电话号码的字母组合
 * @Author zhaooo
 * @Date 2021/3/27/21:37
 * <p>
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day27LetterCombinationsOfAPhoneNumber {
    static class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> list = new ArrayList<>();
            if (digits.length() == 0) {
                return list;
            }
            next(new StringBuilder(digits.length()), digits, 0, list);
            return list;
        }

        public void next(StringBuilder builder, String digits, int index, List<String> list) {
            if (index >= digits.length()) {
                list.add(builder.toString());
                return;
            }
            int num = digits.charAt(index) - 48;
            if (num < 8) {
                // 'a' = 97
                for (int i = 97 + (num - 2) * 3; i < 100 + (num - 2) * 3; i++) {
                    StringBuilder sb = new StringBuilder(digits.length());
                    sb.append(builder).append((char) i);
                    next(sb, digits, index + 1, list);
                }
                if (num == 7) {
                    StringBuilder sb = new StringBuilder(digits.length());
                    sb.append(builder).append('s');
                    next(sb, digits, index + 1, list);
                }
            } else {
                // 't' = 116
                for (int i = 116 + (num - 8) * 3; i < 119 + (num - 8) * 3; i++) {
                    StringBuilder sb = new StringBuilder(digits.length());
                    sb.append(builder).append((char) i);
                    next(sb, digits, index + 1, list);
                }
                if (num == 9) {
                    StringBuilder sb = new StringBuilder(digits.length());
                    sb.append(builder).append('z');
                    next(sb, digits, index + 1, list);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (String s : solution.letterCombinations("97")) {
            System.out.println(s);
        }
    }
}
