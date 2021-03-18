package offer;

/**
 * @ClassName : T14_1
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/12/15:24
 */
public class T14_1 {
    static class Solution {
        public int cuttingRope(int n) {
            if (n <= 3) {
                return n - 1;
            } else {
                int count = n / 3;
                int remainder = n % 3;
                switch (remainder){
                    case 0:{
                        return (int) Math.pow(3,count);
                    }
                    case 1: {
                        return (int) Math.pow(3,count-1)*4;
                    }
                    case 2:{
                        return (int) (Math.pow(3,count)*2);
                    }
                    default:
                        break;
                }
            }
            return -1;
        }
    }
}
