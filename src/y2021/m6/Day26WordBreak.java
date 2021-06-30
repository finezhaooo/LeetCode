package y2021.m6;

import java.util.List;

/**
 * @ClassName : Day26WordBreak
 * @Description: 139. 单词拆分
 * @Author zhaooo
 * @Date 2021/6/27/9:52
 */
public class Day26WordBreak {
    /**
     * 动态规划dp[i]=wordDict[0].equals(s.subString)&&dp[i-wordDict[0].len]...
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        // 第0位为true
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (String word : wordDict) {
                // 先判断startWith不用考虑后面dp[i - word.length()]中下标小于0的情况
                if (s.startsWith(word, i - word.length()) && dp[i - word.length()]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
