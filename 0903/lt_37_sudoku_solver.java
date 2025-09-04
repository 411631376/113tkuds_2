import java.util.*;

public class lt_37_sudoku_solver {
    private static final int N = 9;
    private int[] row = new int[N], col = new int[N], box = new int[N];
    private List<int[]> blanks = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        blanks.clear();
        Arrays.fill(row, 0); Arrays.fill(col, 0); Arrays.fill(box, 0);
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == '.') {
                    blanks.add(new int[]{r, c});
                } else {
                    int d = board[r][c] - '1', b = (r/3)*3 + (c/3), bit = 1 << d;
                    row[r] |= bit; col[c] |= bit; box[b] |= bit;
                }
            }
        }
        dfs(board, 0);
    }

    private boolean dfs(char[][] board, int idx) {
        if (idx == blanks.size()) return true;
        int[] rc = blanks.get(idx);
        int r = rc[0], c = rc[1], b = (r/3)*3 + (c/3);
        int used = row[r] | col[c] | box[b];
        for (int d = 0; d < 9; d++) {
            int bit = 1 << d;
            if ((used & bit) != 0) continue;
            row[r] |= bit; col[c] |= bit; box[b] |= bit;
            board[r][c] = (char)('1' + d);
            if (dfs(board, idx + 1)) return true;
            row[r] ^= bit; col[c] ^= bit; box[b] ^= bit;
            board[r][c] = '.';
        }
        return false;
    }

    // minimal demo
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        new lt_37_sudoku_solver().solveSudoku(board);
        for (char[] r : board) System.out.println(java.util.Arrays.toString(r));
    }
}

/*
解題思路：
1. 用三個 bitmask：row[9]、col[9]、box[9]（3x3），第 d 位表示數字 d+1 是否已用。
2. 先掃盤面，填好 mask，並紀錄所有空格座標於 blanks。
3. 回溯 DFS 依序填空格；可用數字集合 = ~(row|col|box) 的可用位，逐位嘗試。
4. 放入數字 → 更新 mask → 繼續；失敗則回溯還原。
時間複雜度：最壞接近 O(9^(空格數))，但 bitmask+剪枝實測很快。
空間複雜度：O(空格數) 的遞迴深度與 O(1) 的額外結構（固定大小的 mask）。
*/
