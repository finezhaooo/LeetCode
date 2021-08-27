package y2021.m8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @ClassName : CountingBits
 * @Description: 338. 比特位计数
 * @Author zhaooo
 * @Date 2021/8/27/11:25
 * 给定一个非负整数num。对于0 ≤ i ≤ num 范围中的每个数字i，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的__builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountingBits {
    /**
     * 动态规划
     * 最高位
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i <<= 1) {
            for (int j = 0; j < i && j + i <= n; j++) {
                // 每个2的n次方范围内
                // 0的数量等于1+res[j]
                res[j + i] = res[j] + 1;
            }
        }
        return res;
    }

    /**
     * Brian Kernighan 算法
     * @param n
     * @return
     */
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            // 将最末尾一个1变为0
            x &= (x - 1);
            ones++;
        }
        return ones;
    }

    /**
     * 动态规划 最高有效位
     * @param n
     * @return
     */
    public int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        // 最高位为1其余皆为0（即2的n次方）
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            // 为2的n次方
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    /**
     * 动态规划 最低有效位
     * @param n
     * @return
     */
    public int[] countBits4(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 右移表示÷2 在加上最后一位是否为0
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    /**
     * 动态规划 最低设置位
     * @param n
     * @return
     */
    public int[] countBits5(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最后末尾1改为0  再加上1
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}
