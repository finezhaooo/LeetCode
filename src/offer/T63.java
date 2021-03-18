package offer;

/**
 * @ClassName : T63
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/18:45
 */
public class T63 {
    static class Solution {
        public int maxProfit(int[] prices) {
            int result = 0;
            int minPrice = Integer.MAX_VALUE;
            for (int price : prices) {
                if (price < minPrice) {
                    minPrice = price;
                } else {
                    result = Math.max(result, price - minPrice);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices));
    }
}
