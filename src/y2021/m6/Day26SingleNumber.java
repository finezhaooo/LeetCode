package y2021.m6;

import java.util.HashSet;

/**
 * @ClassName : Day26SingleNumber
 * @Description: 136. 只出现一次的数字
 * @Author zhaooo
 * @Date 2021/6/27/9:48
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day26SingleNumber {
    /**
     * 哈希表
     * 第一次出现添加，第二次出现删除
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)){
                set.remove(num);
            }else {
                set.add(num);
            }
        }
        int res = 0;
        for (Integer integer : set) {
            res = integer;
        }
        return res;
    }

    /**
     * 位运算
     * a^a=0; a^0=a;
     * a^b^a=(a^a)^b=b
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
