package offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @ClassName : T13
 * @Description:
 * @Author zhaooo
 * @Date 2021/3/13/15:14
 */
public class T13 {
    static class Solution {
        boolean[][] visited;
        int m, n, k;

        public int movingCount(int m, int n, int k) {
            visited = new boolean[m][n];
            this.m = m;
            this.n = n;
            this.k = k;
            //return dfs(0, 0, 0, 0);

            //bfs
            Queue<int[]> queue = new LinkedList<>();
            int result = 0;
            queue.add(new int[]{0, 0, 0, 0});
            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                int i = temp[0];
                int j = temp[1];
                int sumI = temp[2];
                int sumJ = temp[3];
                if (i >= m || j >= n || sumI + sumJ > k || visited[i][j]) {
                } else {
                    visited[i][j] = true;
                    result++;
                    queue.add(new int[]{i + 1, j, sum(i, sumI), sumJ});
                    queue.add(new int[]{i, j + 1, sumI, sum(j, sumJ)});
                }
            }
            return result;
        }

        public int sum(int index, int sum) {
            if ((index + 1) % 10 == 0) {
                return sum - 8;
            }
            return sum + 1;
        }

        public int dfs(int i, int j, int sumI, int sumJ) {
            if (i >= m || j >= n || sumI + sumJ > k || visited[i][j]) {
                return 0;
            }
            visited[i][j] = true;
            return 1 + dfs(i + 1, j, sum(i, sumI), sumJ) + dfs(i, j + 1, sumI, sum(j, sumJ));
        }
    }
}
