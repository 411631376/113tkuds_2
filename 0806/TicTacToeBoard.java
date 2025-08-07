public class TicTacToeBoard {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';
        System.out.println(checkWin(board));
    }

    public static boolean checkWin(char[][] b) {
        for (int i = 0; i < 3; i++)
            if (b[i][0] != '-' && b[i][0] == b[i][1] && b[i][1] == b[i][2]) return true;
        for (int j = 0; j < 3; j++)
            if (b[0][j] != '-' && b[0][j] == b[1][j] && b[1][j] == b[2][j]) return true;
        if (b[0][0] != '-' && b[0][0] == b[1][1] && b[1][1] == b[2][2]) return true;
        if (b[0][2] != '-' && b[0][2] == b[1][1] && b[1][1] == b[2][0]) return true;
        return false;
    }
}
