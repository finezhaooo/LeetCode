package y2021.m2;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName : Day2LongestRepeatingCharacterReplacement
 * @Description: 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换k次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过104。
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/2/2/13:09
 */
public class Day2LongestRepeatingCharacterReplacement {
    static class Solution {
        public int characterReplacement(String s, int k) {
            // 窗口内字母个数
            int[] num = new int[26];
            int n = s.length();
            int maxn = 0;
            int left = 0, right = 0;
            while (right < n) {
                num[s.charAt(right) - 'A']++;
                // 维护窗口内最多元素
                maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
                // 保证不同字母数小于K
                if (right - left + 1 - maxn > k) {
                    num[s.charAt(left) - 'A']--;
                    left++;
                }
                right++;
            }
            return right - left;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.characterReplacement("AASDADAA", 1));
    }
}
