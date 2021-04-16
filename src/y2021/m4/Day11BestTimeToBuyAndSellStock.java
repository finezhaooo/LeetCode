package y2021.m4;

import java.util.Arrays;

/**
 * @ClassName : Day11BestTimeToBuyAndSellStock
 * @Description: 121. 买卖股票的最佳时机
 * @Author zhaooo
 * @Date 2021/4/11/14:59
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 */
public class Day11BestTimeToBuyAndSellStock {
    // 动态规划
    public int maxProfit(int[] prices) {
        // 一直未未入
        int dp0 = 0;
        // 买入一次
        int dp1 = -prices[0];
        // 买入一次卖出一次
        int dp2 = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            dp1 = Math.max(dp1, dp0 - prices[i]);
            dp2 = Math.max(dp2, dp1 + prices[i]);
        }
        return Math.max(dp0, dp2);
    }

    // 动态规划2
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        // dp[i][0]表示当前未持股 手中金额
        // dp[i][1]表示当前持股 手中金额
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);
            // 保证只买入一次
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    // 动态规划3
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        // dp[][0]表示当前未持股 手中金额
        // dp[][1]表示当前持股 手中金额
        // 滚动数组优化
        int[][] dp = new int[2][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], -prices[i]);
        }
        return dp[(len - 1) % 2][0];
    }

    // 动态规划4
    public int maxProfit4(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        //状态转移方程里下标为 i 的行只参考下标为 i - 1 的行（即只参考上一行），并且：
        //下标为 i 的行并且状态为 0 的行参考了上一行状态为 0 和 1 的行；
        //下标为 i 的行并且状态为 1 的行只参考了上一行状态为 1 的行。
        //状态方程优化
        int[] dp = new int[2];
        dp[1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }

    //一次遍历，假设在今天卖出能获利多少
    public int maxProfit5(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int price : prices) {
            if (price < minprice) {
                minprice = price;
            } else {
                maxprofit = Math.max(maxprofit, price - minprice);
            }
        }
        return maxprofit;
    }


    // 可以买卖多次
    // 动态规划
    public int maxProfit6(int[] prices) {
        int len = prices.length;
        // dp[i][0]表示当前未持股 手中金额
        // dp[i][1]表示当前持股 手中金额
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    // 最多买卖2次
    // 动态规划
    public int maxProfit7(int[] prices) {
        int len = prices.length;
        // dp[0]未买入 手中金额
        // dp[1]买入1次 手中金额
        // dp[2]买入1次卖出1次 手中金额
        // dp[3]买入2次卖出1次手中金额
        // dp[4]买入2次卖出2次 手中金额
        int[] dp = new int[5];
        // 初始化 dp[1]之后都是不可达的
        dp[1] = -prices[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < prices.length; i++) {
            dp[1] = Math.max(dp[1], -prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return Math.max(dp[0], Math.max(dp[2], dp[4]));
    }

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[] dp = new int[k * 2 + 1];
        int result = 0;
        // dp[0] 买入0次
        // do[i] 买入i/2 + 1次 卖出i/2次 （持有股票）
        // dp[i+1] 买入i/2 + 1次 卖出i/2 + 1 （未持有股票）
        // 奇数持有股票
        // 初始化 dp[1]之后都是不可达的
        dp[1] = -prices[0];
        Arrays.fill(dp, 2, dp.length, Integer.MIN_VALUE);
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (j % 2 == 0) {
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);
                    result = Math.max(result, dp[j]);
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                }
            }
        }
        return result;
    }
}
