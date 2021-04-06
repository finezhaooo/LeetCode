package y2021.m4;

/**
 * @ClassName : Day4ClimbingStairs
 * @Description: 70. 爬楼梯
 * @Author zhaooo
 * @Date 2021/4/4/14:10
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Day4ClimbingStairs {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // 初始化第0级台阶为0
        dp[0] = 1;
        return getNum(dp, n);
    }

    public int getNum(int[] dp, int n) {
        if (n < 0) {
            return 0;
        }
        if (dp[n] == 0) {
            dp[n] = getNum(dp, n - 1) + getNum(dp, n - 2);
        }
        return dp[n];
    }
}
