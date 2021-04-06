package y2021.m4;

/**
 * @ClassName : Day5SortColors
 * @Description: 75. 颜色分类
 * @Author zhaooo
 * @Date 2021/4/5/20:24
 * <p>
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day5SortColors {
    public void sortColors(int[] nums) {
        int left = 0;
        int i;
        int right = nums.length - 1;
        while (left < right && nums[left] == 0) {
            left++;
        }
        i = left;
        while (left < right && nums[left] == 1) {
            left++;
        }
        while (left < right && nums[right] == 2) {
            right--;
        }
        while (left < right) {
            if (nums[right] == 0) {
                nums[right] = nums[i];
                nums[i++] = 0;
            }
            if (nums[right] == 1) {
                nums[right] = nums[left];
                nums[left++] = 1;
            }
            right--;
            for (int num : nums) {
                System.out.print(num);
                System.out.print("  ");
            }
            System.out.println("-------");
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        Day5SortColors day5 = new Day5SortColors();
        day5.sortColors(nums);
    }
}
