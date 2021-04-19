package offer;

import java.util.*;

/**
 * @ClassName : T49
 * @Description: 剑指 Offer 49. 丑数
 * @Author zhaooo
 * @Date 2021/4/18/12:39
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 * <p>
 * 1是丑数。
 * n不超过1690。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class T49 {
    // 堆排序
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        Queue<Long> heap = new PriorityQueue<>();
        int[] factors = {2, 3, 5};
        set.add(1L);
        heap.add(1L);
        int res = 0;
        for (int i = 0; i < n; i++) {
            long tmp = heap.poll();
            res = (int) tmp;
            for (int factor : factors) {
                if (!set.contains(tmp * factor)) {
                    set.add(tmp * factor);
                    heap.add(tmp * factor);
                }
            }
        }
        return res;
    }

    // 动态规划
    public int nthUglyNumber2(int n) {
        // dp[i]表示第i个丑数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
