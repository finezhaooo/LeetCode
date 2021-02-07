package y2021.m2;

/**
 * @ClassName : Day6MaximumPointsYouCanObtainFromCards
 * @Description: 1423. 可获得的最大点数
 * @Author zhaooo
 * @Date 2021/2/6/17:28
 */
public class Day6MaximumPointsYouCanObtainFromCards {
    static class Solution {
        public int maxScore(int[] cardPoints, int k) {
            int min;
            int totalPoints = 0;
            int sum = 0;
            for (int i = 0; i < cardPoints.length - k; i++) {
                sum += cardPoints[i];
            }
            min = sum;
            totalPoints = sum;
            for (int i = 0; i < k; i++) {
                sum = sum - cardPoints[i] + cardPoints[cardPoints.length - k + i];
                totalPoints += cardPoints[cardPoints.length - k + i];
                if (sum < min) {
                    min = sum;
                }
            }
            return totalPoints - min;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] card = new int[]{1,1000,1};
        System.out.println(solution.maxScore(card, 1));
    }
}
