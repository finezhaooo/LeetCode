package y2021.m2;

/**
 * @ClassName : Day9SubarraysWithKDifferentIntegers
 * @Description: 992. K个不同整数的子数组
 * 给定一个正整数数组A,如果A的某个子数组中不同整数的个数恰好为K,则称A的这个连续、不一定不同的子数组为好子数组。
 * (例如，[1,2,3,1,2]中有3个不同的整数：1,2,以及3。）
 * 返回A中好子数组的数目。
 * 示例 1：
 * <p>
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * <p>
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/2/9/23:35
 */
public class Day9SubArraysWithKDifferentIntegers {
    static class Solution {
        public int subarraysWithKDistinct(int[] A, int K) {
            return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
        }

        /**
         * 滑动窗口求解最多 最多K - 最多K-1 = 恰好K个
         *
         * @param A
         * @param K
         * @return 最多包含 K 个不同整数的子区间的个数
         */
        private int atMostKDistinct(int[] A, int K) {
            int len = A.length;
            int[] freq = new int[len + 1];

            int left = 0;
            int right = 0;
            // [left, right) 里不同整数的个数
            int count = 0;
            int res = 0;
            // [left, right) 包含不同整数的个数小于等于 K
            while (right < len) {
                if (freq[A[right]] == 0) {
                    count++;
                }
                freq[A[right]]++;
                right++;

                while (count > K) {
                    freq[A[left]]--;
                    if (freq[A[left]] == 0) {
                        count--;
                    }
                    left++;
                }
                // [left, right) 区间的长度就是对结果的贡献
                res += right - left;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = new int[]{1, 2, 1, 3, 4};
        System.out.println(solution.subarraysWithKDistinct(A, 3));
    }
}
