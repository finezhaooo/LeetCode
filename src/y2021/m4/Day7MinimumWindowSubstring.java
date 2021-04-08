package y2021.m4;

import java.util.HashMap;

/**
 * @ClassName : Day7MinimumWindowSubstring
 * @Description: 76. 最小覆盖子串
 * @Author zhaooo
 * @Date 2021/4/7/14:46
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day7MinimumWindowSubstring {
    // map版
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        if (s.equals(t)) {
            return s;
        }
        int left = 0;
        int right = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        int count = 0;
        HashMap<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map.containsKey(ch)) {
                int[] nums = map.get(ch);
                nums[1]++;
            } else {
                map.put(ch, new int[]{0, 1});
            }
        }
        while (right < s.length()) {
            char rChar = s.charAt(right);
            right++;
            if (map.containsKey(rChar)) {
                int[] nums = map.get(rChar);
                if (++nums[0] == nums[1]) {
                    count++;
                }
            }
            while (count == map.size()) {
                if (right - left == s.length()) {
                    return s.substring(left, right);
                }
                if (right - left < len) {
                    len = right - left;
                    start = left;
                }
                char lChar = s.charAt(left);
                left++;
                if (map.containsKey(lChar)) {
                    int[] nums = map.get(lChar);
                    if (--nums[0] < nums[1]) {
                        count--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    // 数组版
    public String minWindow2(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        if (s.equals(t)) {
            return s;
        }
        //统计词频
        int[] need = new int[58];
        int[] window = new int[58];
        char[] sArr = s.toCharArray();
        int needSize = 0;
        for (char ch : t.toCharArray()) {
            if (++need[ch - 'A'] == 1) {
                needSize++;
            }
            ;
        }
        int valid = 0;
        int len = Integer.MAX_VALUE;
        int start = 0;
        int left = 0, right = 0;

        while (right < sArr.length) {
            char c1 = sArr[right];
            right++;
            //need中有某个字符
            if (need[c1 - 'A'] != 0) {
                if (need[c1 - 'A'] == ++window[c1 - 'A']) {
                    valid++;
                }
            }
            while (valid == needSize) {
                if (right - left < len) {
                    //记录与答案有关的数据
                    start = left;
                    len = right - left;
                }
                char c2 = sArr[left];
                left++;

                //缩小窗口
                if (need[c2 - 'A'] != 0) {
                    if (need[c2 - 'A'] == window[c2 - 'A']--) {
                        valid--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Day7MinimumWindowSubstring day7 = new Day7MinimumWindowSubstring();
        System.out.println(day7.minWindow(s, t));
    }
}
