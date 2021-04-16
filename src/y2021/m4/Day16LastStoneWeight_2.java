package y2021.m4;

import java.util.Arrays;

/**
 * @ClassName : Day16LastStoneWeightII
 * @Description: 1049. 最后一块石头的重量 II
 * @Author zhaooo
 * @Date 2021/4/16/21:02
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class Day16LastStoneWeight_2 {
    // 转化为01背包
    // 分为2半 背包能接近一半的最大值 和 剩下
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        // dp[i]=容量为i的背包能装到的最大石头重量
        int[] dp = new int[sum / 2 + 1];
        for (int stone : stones) {
            for (int j = dp.length - 1; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[sum / 2];
    }
}
