package newCoder;

/**
 * @ClassName : NumberOf1
 * @Description: 1的数量
 * @Author zhaooo
 * @Date 2021/4/11/18:19
 */
public class NumberOf1 {
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
