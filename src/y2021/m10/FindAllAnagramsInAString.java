package y2021.m10;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : FindAllAnagramsInAString
 * @Description: 438. 找到字符串中所有字母异位词
 * @Author zhaooo
 * @Date 2021/10/31/17:16
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class FindAllAnagramsInAString {
    /**
     * 26个字母组成map
     * 2个map
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int len = p.length();
        if (s.length() < p.length()) {
            return res;
        }
        int[] pMap = new int[26];
        int[] tmpMap = new int[26];
        for (char c : p.toCharArray()) {
            pMap[c - 'a']++;
        }
        char[] chars = s.toCharArray();
        // 左指针
        int i = 0;
        // 右指针
        int j = 0;
        // 窗口内符合的元素数
        int count = 0;
        for (; i < len - 1; i++) {
            int index = chars[i] - 'a';
            if (++tmpMap[index] > pMap[index]) {
                count--;
            } else {
                count++;
            }
        }
        for (; i < chars.length; i++, j++) {
            int indexI = chars[i] - 'a';
            int indexJ = chars[j] - 'a';
            if (++tmpMap[indexI] > pMap[indexI]) {
                count--;
            } else {
                count++;
            }
            if (--tmpMap[indexJ] < pMap[indexJ]) {
                count--;
            } else {
                count++;
            }
            if (count == len) {
                res.add(j);
            }
        }
        return res;
    }

    /**
     * 一个map
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int len = p.length();
        if (len > s.length()) {
            return res;
        }
        int[] pMap = new int[26];
        char[] chars = s.toCharArray();
        //i指向下一个要比较的位置
        int i = 0;
        int count = 0;
        for (char c : p.toCharArray()) {
            pMap[c - 'a']++;
        }
        for (; i < len; i++) {
            // 出现相同的减去一个(理解为消消乐)
            if (--pMap[chars[i] - 'a'] < 0) {
                count--;
            } else {
                count++;
            }
        }
        for (; i < chars.length; i++) {
            // count == len 即 map全为 0 (每个元素都符合,消去所有)
            if (count == len) {
                res.add(i - len + 1);
            }
            if (--pMap[chars[i] - 'a'] < 0) {
                count--;
            } else {
                count++;
            }
            // 左边弹出一个,就恢复他在map消去的位置
            if (++pMap[chars[i - len + 1] - 'a'] > 0) {
                count--;
            } else {
                count++;
            }
        }
        // 因为先判断了在循环,最后一个没判断到
        if (count == len) {
            res.add(i - len + 1);
        }
        return res;
    }
}
