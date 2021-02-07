package y2021.m2;

import java.util.HashMap;

/**
 * @ClassName : Day1LongestSubstringWithoutRepeatingCharacters
 * @Description: 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * @Author zhaooo
 * @Date 2021/2/1/12:05
 */
public class Day1LongestSubstringWithoutRepeatingCharacters {
    static class Solution {
        HashMap<Character, Integer> characters = new HashMap<>();
        int max = 0;
        int sum = 0;
        int star = 0;
        char aChar;

        public int lengthOfLongestSubstring(String s) {
            for (int i = 0; i < s.length(); i++) {
                aChar =s.charAt(i);
                if (characters.containsKey(aChar)) {
                    //在起点之前sum++
                    if (characters.get(aChar) < star){
                        max = Math.max(++sum,max);
                    }else {
                        //在起点之后更新star,sum
                        star = characters.get(aChar);
                        sum = i - star;
                    }
                } else {
                    max = Math.max(++sum,max);
                }
                characters.put(aChar,i);
            }
            return max;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        }
    }
}
