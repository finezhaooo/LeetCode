package y2021.m3;

import java.util.Arrays;

/**
 * @ClassName : Day25LongestPalindromicSubstring
 * @Description: 5. 最长回文子串
 * @Author zhaooo
 * @Date 2021/3/25/12:41
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day25LongestPalindromicSubstring {
    static class Solution {
        public String longestPalindrome(String s) {
            int[] result1 = new int[]{0, 0};
            int[] result2 = new int[]{0, 0};
            char[] chars = s.toCharArray();
            // 中间是一个单独的
            for (int i = 0; i < s.length(); i++) {
                getNext(i, i, chars, result1);
            }
            // 中间是两个相同的
            for (int i = 0; i < s.length() - 1; i++) {
                getNext(i, i + 1, chars, result2);
            }
            // 保证result1最长
            if (result2[1] - result2[0] > result1[1] - result1[0]) {
                result1 = result2;
            }
            return String.copyValueOf(Arrays.copyOfRange(chars, result1[0], result1[1]+1));
        }

        public void getNext(int start, int end, char[] chars, int[] result) {
            if (chars[start] == chars[end]) {
                if (end - start > result[1] - result[0]) {
                    result[0] = start;
                    result[1] = end;
                }
                if (start - 1 >= 0 && end + 1 <= chars.length - 1) {
                    getNext(start - 1, end + 1, chars, result);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.copyOfRange(new char[]{'a','b','c'},0,1));
    }
}
