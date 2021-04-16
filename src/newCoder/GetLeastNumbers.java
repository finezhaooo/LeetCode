package newCoder;

import java.util.ArrayList;

/**
 * @ClassName : GetLeastNumbers
 * @Description:
 * @Author zhaooo
 * @Date 2021/4/11/0:11
 */
public class GetLeastNumbers {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k > input.length) {
            return result;
        }
        //BubbleSort(input, k, result);
        heapSort(input, k, result);
        return result;
    }

    // 建立小根堆
    public void adjustHeap(int[] input, int parent, int length) {
        int tmp = input[parent];
        // 左孩子
        int left = parent * 2 + 1;
        while (left < length) {
            int right = left + 1;
            // 选取右孩子为left即较小的节点
            if (right < length && input[right] < input[left]) {
                left++;
            }
            // 较小节点是否大于tmp即最开始的父节点
            if (tmp <= input[left]) {
                break;
            }
            // 较小节点提升
            input[parent] = input[left];
            // 在原来较小节点的位置开始重新调整
            parent = left;
            left = left * 2 + 1;
        }
        // parent为最后一次交换提供值的位置
        // 此时该parent为叶节点
        input[parent] = tmp;
    }

    public ArrayList<Integer> heapSort(int[] input, int k, ArrayList<Integer> result) {
        //创建堆
        int len = input.length;
        // i = (len - 1)/2也行
        for (int i = (len - 2) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(input, i, len);
        }
        for (int i = 1; i <= k; i++) {
            result.add(input[0]);
            int tmp = input[0];
            input[0] = input[len - i];
            input[len - i] = tmp;
            adjustHeap(input, 0, len - i);
        }
        return result;
    }

    public ArrayList<Integer> BubbleSort(int[] input, int k, ArrayList<Integer> result) {
        for (int i = 0; i < k; i++) {
            for (int j = input.length - 1; j > i; j--) {
                if (input[j] < input[j - 1]) {
                    int tmp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = tmp;
                }
            }
            result.add(input[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
    }
}
