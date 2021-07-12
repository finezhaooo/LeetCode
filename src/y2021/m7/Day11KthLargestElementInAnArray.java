package y2021.m7;

import java.util.Arrays;

/**
 * @ClassName : Day11KthLargestElementInAnArray
 * @Description: 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 104
 * -104<= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/11/14:35
 */
public class Day11KthLargestElementInAnArray {
    /**
     * 利用快速排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        //升序
        return find(nums, 0, nums.length - 1, nums.length - k);
    }

    public int find(int[] nums, int left, int right, int n) {
        int pivotPos = partition(nums, left, right);
        //找到就返回，否则在剩下的两部分取其一寻找
        if (pivotPos == n) {
            return nums[pivotPos];
        }
        if (pivotPos < n) {
            return find(nums, pivotPos + 1, right, n);
        } else {
            return find(nums, left, pivotPos - 1, n);
        }
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    /**
     * 快速排序优化
     * 使用使用降序
     * nums[left]为nums[left],nums[(left+right)/2]和nums[right]中位数
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        // 使用降序下标k-1即为第K大
        return find2(nums, 0, nums.length - 1, k - 1);
    }

    public int find2(int[] nums, int left, int right, int n) {
        int pivotPos = partitionDesc(nums, left, right);
        //找到就返回，否则在剩下的两部分取其一寻找
        if (pivotPos == n) {
            return nums[pivotPos];
        }
        if (pivotPos < n) {
            return find2(nums, pivotPos + 1, right, n);
        } else {
            return find2(nums, left, pivotPos - 1, n);
        }
    }

    //降序并使用中位数
    public int partitionDesc(int[] nums, int left, int right) {
        // 使用中位数
        setMedianNum(nums, left, right);
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] <= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] >= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    public void setMedianNum(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        int[] arr = new int[]{nums[left], nums[mid], nums[right]};
        Arrays.sort(arr);
        nums[left] = arr[1];
        nums[mid] = arr[0];
        nums[right] = arr[2];
    }

    /**
     * 利用堆排序
     * 可以使用大根堆或者小根堆
     * 大根堆：建立一个len长的大根堆，做k-1次删除操作后nums[0]即为第k大的元素
     * 小根堆: 建立一个k长的的小根堆，剩下的每个元素与堆顶比较，大于堆顶就swap，最后堆顶nums[0]即为第k大的元素
     * 本题为小根堆
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest3(int[] nums, int k) {
        buildMinHeap(nums, k);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                swap(nums, i, 0);
                heapify(nums, 0, k);
            }
        }
        return nums[0];
    }


    public void buildMinHeap(int[] arr, int len) {
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    public void heapify(int[] arr, int i, int len) {
        int min = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left < len && arr[min] > arr[left]) {
            min = left;
        }
        if (right < len && arr[min] > arr[right]) {
            min = right;
        }
        if (min != i) {
            swap(arr, min, i);
            heapify(arr, min, len);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
