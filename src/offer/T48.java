package offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : T48
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/15:33
 */
public class T48 {
    static class Solution {
        //只能是小写字母
        public int lengthOfLongestSubstring_1(String s) {
            if (s.length() < 2) {
                return s.length();
            }
            int[] alphabet = new int[26];
            int left = 0;
            int right = 0;
            int result = 0;
            byte charIndex;
            do {
                charIndex = (byte) (s.charAt(right) - 97);
                if (alphabet[charIndex] < 1) {
                    //因为 alphabet初始化为全0,令alphabet[charIndex]为在s中的index+1,即从1开始
                    alphabet[charIndex] = ++right;
                    result = Math.max(result, right - left);
                } else {
                    left = Math.max(alphabet[charIndex], left);
                    right++;
                }
            } while (left < right && right < s.length());
            return result;
        }

        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> dic = new HashMap<>();
            int i = -1, res = 0;
            for (int j = 0; j < s.length(); j++) {
                if (dic.containsKey(s.charAt(j))) {
                    i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
                }
                dic.put(s.charAt(j), j); // 哈希表记录
                res = Math.max(res, j - i); // 更新结果
            }
            return res;
        }
    }
}
