package y2021.m2;

/**
 * @ClassName : Day27ContainerWithMostWater
 * @Description: 11. 盛最多水的容器
 * @Author zhaooo
 * @Date 2021/2/27/21:56
 * <p>
 * <p>
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * <p>
 *
 * ?题解：初始时指针指向左右端点 只需要移动较小的端点
 * ?双指针代表的是 可以作为容器边界的所有位置的范围。在一开始，双指针指向数组的左右边界，表示 数组中所有的位置都可以作为容器的边界，
 * ?因为我们还没有进行过任何尝试。在这之后，我们每次将 对应的数字较小的那个指针往另一个指针的方向移动一个位置，就表示我们认为这个指针不可能再作为容器的边界了。
 * ?若移动较大值（假设right为较大值） 新值为new  new小于left时 容量为(len-1)*new;new大于left时 容量为(len-1)*left 均小于len*left
 * ?即无论我们怎么移动右指针，得到的容器的容量都小于移动前容器的容量。也就是说，这个左指针对应的数不会作为容器的边界了，
 * ?那么我们就可以丢弃这个位置，将左指针向右移动一个位置，此时新的左指针于原先的右指针之间的左右位置，才可能会作为容器的边界。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day27ContainerWithMostWater {
    static class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int len = height.length;
            int right = len - 1;
            int max = 0;
            while (left < right) {
                if (height[left] <= height[right]) {
                    max = Math.max(max, --len * height[left]);
                    left++;
                } else {
                    max = Math.max(max, --len * height[right]);
                    right--;
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] test = {1,8,6,2,5,4,8,3,7};
        Solution solution = new Solution();
        System.out.println(solution.maxArea(test));
    }
}
