package offer;

/**
 * @ClassName : T53_1
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/22:37
 */
public class T53_1 {
    static class Solution {
        public int search(int[] nums, int target) {
            return helper(nums, target) - helper(nums, target - 1);
        }
        int helper(int[] nums, int tar) {
            int i = 0, j = nums.length - 1;
            while(i <= j) {
                int m = (i + j) / 2;
                if(nums[m] <= tar) {
                    i = m + 1;
                } else {
                    j = m - 1;
                }
            }
            return i;
        }
    }

    public static void main(String[] args) {
        int[] array = {2,2};
        Solution solution = new Solution();
        System.out.println(solution.search(array, 2));
    }
}
