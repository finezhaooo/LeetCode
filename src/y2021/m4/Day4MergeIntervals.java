package y2021.m4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @ClassName : Day4MergeIntervals
 * @Description: 56. 合并区间
 * @Author zhaooo
 * @Date 2021/4/4/12:33
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class Day4MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int last = 0;
        for (int i = 1; i < intervals.length; i++) {
            // 范围在intervals[last]之外
            if (intervals[i][0] > intervals[last][1]) {
                intervals[++last] = intervals[i];
            } else {
                intervals[last][1] = Math.max(intervals[last][1], intervals[i][1]);
            }
        }
        // 原地排序，也可用list申请空间
        return Arrays.copyOfRange(intervals, 0, last + 1);
    }
}
