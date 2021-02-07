package y2021.m2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @ClassName : Day3SlidingSindowMedian
 * @Description: 480. 滑动窗口中位数
 * <p>
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * ·[2,3,4],中位数是3
 * ·[2,3],中位数是(2+3)/2=2.5
 * 给你一个数组nums,有一个大小为k的窗口从最左端滑动到最右端。窗口中有k个数，每次窗口向右移动1位。你的任务
 * 是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * <p>
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/2/3/10:09
 */
public class Day3SlidingWindowMedian {
    static class Solution {

        public double[] medianSlidingWindow(int[] nums, int k) {
            double[] result = new double[nums.length - k + 1];
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                list.add(nums[i]);
            }
            Comparator<Integer> comparator = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            };
            list.sort(comparator);
            int i = k;
            if (k % 2 == 0) {
                double tmp;
                tmp = (double) list.get(k / 2);
                tmp = tmp + (double) list.get((k / 2) - 1);
                // 2个2147483647相加/2会等于-1
                //result[i - k] = (double) (list.get(k / 2) + list.get((k / 2) - 1)) / 2;
                result[i - k] = tmp / 2;
                for (; i < nums.length; i++) {
                    list.remove(new Integer(nums[i - k]));
                    list.add(nums[i]);
                    list.sort(comparator);
                    //result[i - k + 1] = (list.get(k / 2) + list.get((k / 2) - 1)) >> 1;
                    tmp = (double) list.get(k / 2);
                    tmp = tmp + (double) list.get((k / 2) - 1);
                    result[i - k + 1] = tmp / 2;
                }
            } else {
                result[i - k] = list.get(k / 2);
                for (; i < nums.length; i++) {
                    list.remove(new Integer(nums[i - k]));
                    list.add(nums[i]);
                    list.sort(comparator);
                    result[i - k + 1] = list.get((k / 2));
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2147483647, 2147483647, 2147483647, 2147483647};
        Solution solution = new Solution();
        for (double v : solution.medianSlidingWindow(nums, 2)) {
            System.out.println(v);
        }
    }
}
