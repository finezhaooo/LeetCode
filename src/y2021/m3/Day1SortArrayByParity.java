package y2021.m3;

import java.util.HashMap;

/**
 * @ClassName : Day1SortArrayByParity
 * @Description: 905. 按奇偶排序数组
 * @Author zhaooo
 * @Date 2021/3/1/18:02
 * <p>
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * 你可以返回满足此条件的任何数组作为答案。
 * <p>
 * 示例：
 * <p>
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day1SortArrayByParity {
    static class Solution {
        public int[] sortArrayByParity(int[] A) {
            int tmp;
            int left = 0;
            int right = A.length - 1;
            while (left < right) {
                while (A[left] % 2 == 0 && left < right) {
                    ++left;
                }
                while (A[right] % 2 == 1 && left < right) {
                    --right;
                }
                tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
                left++;
                right--;
            }
            return A;
        }
    }

    public static void main(String[] args) {
        int[] A = {3, 1, 2, 4};
        Solution solution = new Solution();
        for (int i : solution.sortArrayByParity(A)) {
            System.out.println(i);
        }
    }
}
