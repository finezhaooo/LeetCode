package y2021.m7;

import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName : Day5MajorityElement
 * @Description: 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例1：
 *
 * 输入：[3,2,3]
 * 输出：3
 * 示例2：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 进阶：
 *
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/5/21:14
 */
public class Day5MajorityElement {
    /**
     * 多数元素出现的次数大于n/2
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int cur = 0;
        int curLen = 0;
        int len = nums.length;
        for (int num : nums) {
            if (curLen == 0) {
                cur = num;
                curLen++;
                continue;
            }
            if (num == cur) {
                if (++curLen > len / 2) {
                    return cur;
                }
            } else {
                curLen--;
            }
        }
        return cur;
    }

    /**
     * 排序
     * 当多数元素是最大值或者最小值时这两种极端情况,它们会在下标为 n/2 位置上有重叠
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 随机化大于n/2的数组下标被众数占据了，这样我们随机挑选一个下标对应的元素并验证，有很大的概率能找到众数。
     * 由于一个给定的下标对应的数字很有可能是众数，我们随机挑选一个下标，检查它是否是众数，如果是就返回，否则继续随机挑选。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-lee
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param rand
     * @param min
     * @param max
     * @return
     */
    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurrences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int majorityElement3(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurrences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

    /**
     * 分治
     * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     * @param nums
     * @return
     */
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement4(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    /**
     * 如果我们把众数记为 +1，把其他数记为 -1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
     * @param nums
     * @return
     */
    public int majorityElement5(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
