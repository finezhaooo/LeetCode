package y2021.m7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @ClassName : Day16SlidingWindowMaximum
 * @Description: 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 示例 4：
 *
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 示例 5：
 *
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104<= nums[i] <= 104
 * 1 <= k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zhaooo
 * @Date 2021/7/16/18:25
 */
public class Day16SlidingWindowMaximum {
    /**
     * 超时
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];
        // dp[i]表示i到dp数组末尾（i-1）的最大值
        int[] dp = new int[k];
        // dp初始化
        dp[k - 1] = nums[k - 1];
        for (int i = k - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i]);
        }
        // i:num的指针
        int i;
        for (i = k; i < len; i++) {
            // j:dp的指针
            int j = i % k;
            res[i - k] = dp[j];
            dp[j] = nums[i];
            for (int l = 1; l < k; l++) {
                if (nums[i] > dp[(j + l) % k]) {
                    while (l < k) {
                        dp[((j + l++) % k)] = nums[i];
                    }
                }
            }
        }
        res[k - len] = dp[i % k];
        return res;
    }

    /**
     * 优先队列（堆）
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        //这里我们传入了一个比较器，当两者的值相同时，比较下标的位置，下标大的在前面。
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (p1, p2) -> p1[0] != p2[0] ? p2[0] - p1[0] : p2[1] - p1[1]);
        //初始化前K的元素到堆中
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        //有n-k+1个
        int[] ans = new int[n - k + 1];
        //将第一次答案加入数据
        ans[0] = queue.peek()[0];
        for (int i = k; i < n; i++) {
            //将新元素加入优先队列
            queue.offer(new int[]{nums[i], i});
            //循环判断当前队首是否在窗口中，窗口的左边界为i-k
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            //在窗口中直接赋值即可
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }

    /**
     * 由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i < j），并且 i 对应的元素不大于 j 对应的元素（nums[i]≤nums[j]）
     * 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，这是 i 在 j 的左侧所保证的。
     * 因此，由于 {nums}[j]nums[j] 的存在，{nums}[i]nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 {nums}[i]nums[i] 永久地移除。
     * 因此我们可以使用一个队列存储所有还没有被移除的下标。在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 {nums}nums 中对应的值是严格单调递减的。
     * 因为如果队列中有两个相邻的下标，它们对应的值相等或者递增，那么令前者为 i，后者为 j，就对应了上面所说的情况，即 {nums}[i]nums[i] 会被移除，这就产生了矛盾。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {

        int n = nums.length;
        //创建双端队列
        Deque<Integer> deque = new ArrayDeque<>();
        //先初始化前K个元素
        for (int i = 0; i < k; i++) {
            //判断队列是否为空 或者当前入队元素是否大于队尾元素 大于则出队
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //当前元素入队
            //由于需要判断当前元素是否在窗口中，所以实际上队列中存储的为当前元素的下标
            //根据下标找元素比根据元素找下标方便
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        //添加当前最大元素
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            //判断队列是否为空 或者当前入队元素是否大于队尾元素 大于则出队
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //当前元素入队
            deque.offerLast(i);
            //判断队首元素是否在窗口中
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            //添加答案
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**
     * 前后缀分组
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            } else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
}
