package offer;

/**
 * @ClassName : T14_1
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/15:24
 */
public class T14_1 {
    static class Solution {
        public int cuttingRope(int n) {
            if (n <= 3) {
                return n - 1;
            } else {
                int count = n / 3;
                int remainder = n % 3;
                switch (remainder) {
                    case 0: {
                        return (int) Math.pow(3, count);
                    }
                    case 1: {
                        return (int) Math.pow(3, count - 1) * 4;
                    }
                    case 2: {
                        return (int) (Math.pow(3, count) * 2);
                    }
                    default:
                        break;
                }
            }
            return -1;
        }
    }

    // 动态规划
    public int cuttingRope(int n) {
        // dp[i]表示长度为i能剪出的积最大值
        int[] dp = new int[n + 1];
        // 长度为2最长为1
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                // 剪了第一段后，剩下(i - j)长度可以剪也可以不剪。
                // 如果不剪的话长度乘积即为j * (i - j)；
                // 如果剪的话长度乘积即为j * dp[i - j]。
                // 取两者最大值max(j * (i - j), j * dp[i - j])
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
