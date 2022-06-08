package per.cajr.leetcode.dfs.no51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * 皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * ps:皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * <p>
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <p>
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * <p>
 * 输出：[["Q"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 *
 * @author CAJR
 */
public class NQueens {

    List<List<String>> nRes = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] checkerboard = new char[n][n];
        for (char[] row : checkerboard) {
            Arrays.fill(row, '.');
        }
        solveQueen(checkerboard, 0);
        return nRes;
    }

    private void solveQueen(char[][] checkerboard, int row) {
        if (row == checkerboard.length) {
            setRes(checkerboard);
            return;
        }
        int n = checkerboard[row].length;
        for (int j = 0; j < n; j++) {
            if (!check(checkerboard, row, j)) {
                continue;
            }
            checkerboard[row][j] = 'Q';
            solveQueen(checkerboard, row + 1);
            checkerboard[row][j] = '.';
        }
    }

    private boolean check(char[][] checkerboard, int row, int col) {
        // 上方
        for (int i = 0; i < row; i++) {
            if (checkerboard[i][col] == 'Q') {
                return false;
            }
        }
        //右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < checkerboard.length; i--, j++) {
            if (checkerboard[i][j] == 'Q') {
                return false;
            }
        }
        //左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (checkerboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private void setRes(char[][] checkerboard) {
        List<String> list = new ArrayList<>();
        for (char[] rowCheckerboard : checkerboard) {
            StringBuilder checkerboardRow = new StringBuilder();
            for (char c : rowCheckerboard) {
                checkerboardRow.append(c);
            }
            list.add(checkerboardRow.toString());
        }
        this.nRes.add(list);
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(new NQueens().solveNQueens(n));
    }
}
