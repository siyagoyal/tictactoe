public class Tictactoe {
    private static char[][] gameBoard = {
        {'_', '_', '_'},
        {'_','_', '_'},
        {'_', '_', '_'}
    };
    public static int playCounter = 0;
    public static void main(String[] args) {
        
    }

    public static boolean checkWin (int row, int col, char[][] board, char playerChar) {
        if (check2(row, col) == true) {
            if (isHorizontal(row, col, gameBoard) || isVertical(row, col, gameBoard) == true) {
                return true;
            }
        }
        else {
            if ((isHorizontal(row, col, gameBoard) || isVertical(row, col, gameBoard) || isDiagonal(row, col, gameBoard)) == true) {
                return true;
            }
        }
        return false;
    }
    public static boolean isEmpty (int row, int col, char[][] board, char playerChar) {
        if (board[row][col] != '_') {
            return false;
        }
        else {
            board[row][col] = playerChar;
            playCounter++;
            return true;
        }
    }
    public static boolean check2 (int row, int col) {
    //checks if we only need to check horizontal/vertical (TRUE), or if we need to check diagonal as well (FALSE)
        if ((row==0 && col==1) || (row==1 && col==0) || (row==1 && col==2) || (row==2 && col==1)) {
            return true;
        }
        return false;        
    }
    public static boolean isHorizontal(int row, int col, char[][] board) {
        for (int i=0; i < 3; i++) {
            if (board[row][col]!=board[row][i]) {
                return false;
            }
        }
        return true;
    }
    public static boolean isVertical(int row, int col, char[][] board) {
        for (int i=0; i < 3; i++) {
            if (board[row][col] != board[i][col]) {
                return false;
            }
        }
        return true;
    }
    public static boolean isDiagonal(int row, int col, char[][] board) {
        if ((row == 0 && col == 0) || (row == 1 && col == 1) || (row == 2 && col == 2)) {
            if (board[row][col] == board[0][0] && board[row][col] == board[1][1] && board[row][col] == board[2][2]) {
                return true;
            }
        }
        if ((row==2 && col==0) || (row==1 && col==1) || (row==0 && col==2)) {
            if (board[row][col] == board[2][0] && board[row][col] == board[1][1] && board[row][col] == board[0][2]) {
                return true;
            }
        }
        return false;
    }
    public static char[][] getBoard() {
        return gameBoard;
    }
}