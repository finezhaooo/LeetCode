package y2021.m4;

import java.util.Arrays;

/**
 * @ClassName : Day8WordSearch
 * @Description: 79. 单词搜索
 * @Author zhaooo
 * @Date 2021/4/8/14:39
 * <p>
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'SEE'
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCB'
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Day8WordSearch {
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (getWord(board, used, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean getWord(char[][] board, boolean[][] used,
                           String word, int index, int i, int j) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j]) {
            return false;
        }
        if (board[i][j] == word.charAt(index)) {
            used[i][j] = true;
            if (!(getWord(board, used, word, index + 1, i + 1, j) ||
                    getWord(board, used, word, index + 1, i, j + 1) ||
                    getWord(board, used, word, index + 1, i - 1, j) ||
                    getWord(board, used, word, index + 1, i, j - 1))) {
                // 回溯回之前的状态
                used[i][j] = false;
            }else {
                return true;
            }
        }
        return false;
    }
}
