package offer;

/**
 * @ClassName : T11
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/22:25
 */
public class T11 {
    static class Solution {
        public int minArray(int[] numbers) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int mid = (right + left) / 2;
                if (numbers[mid] > numbers[right]) {
                    left = mid + 1;
                } else if (numbers[mid] < numbers[right]) {
                    right = mid;
                } else {
                    right--;
                }
            }
            return numbers[left];
        }
    }
}
