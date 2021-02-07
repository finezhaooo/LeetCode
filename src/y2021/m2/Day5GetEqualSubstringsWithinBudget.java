package y2021.m2;

/**
 * @ClassName : Day5GetEqualSubstringsWithinBudget
 * @Description: 1208. 尽可能使字符串相等
 * 给你两个长度相同的字符串，s和t。
 * 将s中的第1个字符变到t中的第1个字符需要|s[i]-t[i]的开销|（开销可能为0),也就是两个字符的ASCII码值的差的绝对值。
 * 用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 如果你可以将s的子字符串转化为它在t中对应的子字符串，则返回可以转化的最大长度。
 * 如果s中没有子字符串可以转化成t中对应的子字符串，则返回0。
 * @Author zhaooo
 * @Date 2021/2/5/13:06
 */
public class Day5GetEqualSubstringsWithinBudget {
    static class Solution {
        public int equalSubstring(String s, String t, int maxCost) {
            // start end 均是下一次开始比较时的下标
            int start = 0;
            int end = 0;
            int maxLength = 0;
            int sumCost = 0;
            while (start <= end && end < s.length()) {
                if (sumCost <= maxCost) {
                    sumCost += Math.abs(s.charAt(end) - t.charAt(end));
                    end++;
                }
                if (sumCost > maxCost) {
                    sumCost -= Math.abs(s.charAt(start) - t.charAt(start));
                    start++;
                }
                maxLength = Math.max(maxLength, end - start);
            }
            return maxLength;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = new String("anryddgaqpjdw");
        String t = new String("zjhotgdlmadcf");
        System.out.println(solution.equalSubstring(s, t, 5));
        for (int i = 0; i < s.length(); i++) {
            System.out.print(i + ":");
            System.out.println(Math.abs(s.charAt(i) - t.charAt(i)));
        }
    }
}
