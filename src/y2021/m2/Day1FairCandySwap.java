package y2021.m2;

import java.util.HashSet;

/**
 * @ClassName : Day1FairCandySwap
 * @Description: 888. 公平的糖果棒交换
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1]是 Bob 必须交换的糖果棒的大小。
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 * <p>
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 * <p>
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 * @Author zhaooo
 * @Date 2021/2/1/21:59
 */
public class Day1FairCandySwap {
    static class Solution {

        public int[] fairCandySwap(int[] A, int[] B) {
            int sumA = 0;
            int sumB = 0;
            HashSet<Integer> setB = new HashSet<>();
            for (int i : A) {
                sumA += i;
            }
            for (int i : B) {
                setB.add(i);
                sumB += i;
            }
            //A-diff = B+diff
            int diff = (sumA - sumB) / 2;
            for (int i : A) {
                if (i > diff && setB.contains(i - diff)) {
                    return new int[]{i, i - diff};
                }
            }
            return null;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] a = {1, 1};
            int[] b = {2, 2};
            int[] c = solution.fairCandySwap(a, b);
            for (int i : c) {
                System.out.println(i);
            }
        }
    }
}
