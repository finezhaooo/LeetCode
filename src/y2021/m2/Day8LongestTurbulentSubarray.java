package y2021.m2;

/**
 * @ClassName : Day8LongestTurbulentSubarray
 * @Description: 978. 最长湍流子数组
 * 当A的子数组A[i],A[i+1],...,A[j]满足下列条件时，我们称其为湍流子数组：
 * ·若i<=k<j,当k为奇数时，A[k]>A[k+1],且当k为偶数时，A[k]<A[k+1];
 * ·或若i<=k<j,当k为偶数时，A[k]>A[k+1],且当k为奇数时，A[k]<A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回A的最大湍流子数组的长度。
 * 示例 1：
 * <p>
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * <p>
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[100]
 * 输出：1
 * <p>
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/2/9/13:11
 */
public class Day8LongestTurbulentSubarray {
    static class Solution {
        public int maxTurbulenceSize(int[] arr) {
            int n = arr.length;
            int ret = 1;
            int left = 0, right = 0;

            while (right < n - 1) {
                if (left == right) {
                    if (arr[left] == arr[left + 1]) {
                        left++;
                    }
                    right++;
                } else {
                    if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                        right++;
                    } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                        right++;
                    } else {
                        left = right;
                    }
                }
                ret = Math.max(ret, right - left + 1);
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 1, 1};
        System.out.println(solution.maxTurbulenceSize(arr));
    }
}
