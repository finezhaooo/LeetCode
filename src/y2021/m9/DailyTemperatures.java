package y2021.m9;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName : DailyTemperatures
 * @Description: 739. 每日温度
 * @Author zhaooo
 * @Date 2021/9/7/15:08
 * 请根据每日 气温 列表 temperatures，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0 来代替。
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出:[1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出:[1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <=temperatures.length <= 105
 * 30 <=temperatures[i]<= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures {
    /**
     * 动态规划
     * 以当前日期为跳板
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = res.length - 2; i >= 0; i--) {
            if (temperatures[i + 1] > temperatures[i]) {
                res[i] = 1;
            } else {
                // 相对天数下标（比i大多少，即在多少天后）
                int dayOffset = 1;
                // 此天数下表的更高温度在多少天后
                int tmp = res[i + dayOffset];
                //  没有更大的温度，推出循环
                while (tmp != 0) {
                    // 相当于i又多了tmp天
                    dayOffset += tmp;
                    if (temperatures[i + dayOffset] > temperatures[i]) {
                        res[i] = dayOffset;
                        break;
                    }
                    // 更新为此天数下表的更高温度在多少天后
                    tmp = res[i + dayOffset];
                }
            }
        }
        return res;
    }

    /**
     * 动态规划
     * 使用实际下标，不使用偏移值
     * res为下标差 即j-i
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int length = T.length;
        int[] result = new int[length];

        //从右向左遍历
        for (int i = length - 2; i >= 0; i--) {
            // j+= result[j]是利用已经有的结果进行跳跃
            for (int j = i + 1; j < length; j += result[j]) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                    //遇到0表示后面不会有更大的值，那当然当前值就应该也为0
                } else if (result[j] == 0) {
                    result[i] = 0;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 单调栈（下一个更大元素问题）
     * 递减展
     * @param temperatures
     * @return
     * https://leetcode-cn.com/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/
     */
    public int[] dailyTemperatures3(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        // 栈保存下标
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            // 大于栈顶,出栈
            // 即当前元素是栈内还未确定更大温度 的 更大温度(出栈即确定了更大温度)
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
