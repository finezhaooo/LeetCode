package newCoder;

import java.util.LinkedList;

/**
 * @ClassName : LCS
 * @Description:
 * @Author zhaooo
 * @Date 2021/4/11/2:03
 */
public class LCS {
    /**
     * longest common subsequence
     *
     * @param s1 string字符串 the string
     * @param s2 string字符串 the string
     * @return string字符串
     */
    public String LCS(String s1, String s2) {
        // write code here
        if(s1 == null || s2 == null){
            return "-1";
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        StringBuilder stringBuilder = new StringBuilder(Math.min(s1.length(), s2.length()));
        for (int i = 1; i <= s1.length(); i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        // 长度
        return String.valueOf(dp[s1.length()][s2.length()]);
    }

    public String LCS2(String s1, String s2) {
        // write code here
        if(s1 == null || s2 == null){
            return "-1";
        }
        int l1 = s1.length();
        int l2 = s2.length();
        StringBuilder sb = new StringBuilder();
        while (l1 > 0 && l2 > 0) {
            if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) {
                sb.append(s1.charAt(l1 - 1));
                l1--;
                l2--;
            } else {
                if (l1 > 1 && l2 > 1) {
                    if (Math.max(s1.charAt(l1 - 2), s2.charAt(l2 - 1)) >= Math.max(s1.charAt(l1 - 1), s2.charAt(l2 - 2))) {
                        l2--;
                    } else {
                        l1--;
                    }
                } else if (l1 == 1) {
                    l2--;
                } else {
                    l1--;
                }
            }
        }
        while (sb.length() == 0) {
            return "-1";
        }
        return sb.reverse().toString();
    }
}
