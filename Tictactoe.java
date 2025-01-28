import java.util.Scanner;
public class Tictactoe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] gameBoard = {
            {'_', '_', '_'},
            {'_','_', '_'},
            {'_', '_', '_'}
        };
        printBoard(gameBoard);

        System.out.println("\nPlayer 1 will use X. Enter Player 1's name: ");
        String nameOne = scanner.nextLine();
        System.out.println("Player 2 will use O. Enter Player 2's name: ");
        String nameTwo = scanner.nextLine();

        int[] indices = new int [2];
        boolean isWin = false;
        outerloop:
        for (int counter = 1; counter < 10; counter++) {
            if (counter % 2 != 0) {
                indices = askMove(scanner, counter, gameBoard, nameOne, 'X');
            }
            else {
                indices = askMove(scanner, counter, gameBoard, nameTwo, 'O');
            }   

            if ((indices[0]==0 && indices[1]==1) || (indices[0]==1 && indices[1]==0) || (indices[0]==1 && indices[1]==2) || (indices[0]==2 && indices[1]==1)) {
                    isWin = (isHorizontal(indices[0],indices[1], gameBoard) || isVertical(indices[0],indices[1], gameBoard));
                }
                else {
                    isWin = (isHorizontal(indices[0],indices[1], gameBoard) || isVertical(indices[0],indices[1], gameBoard) || isDiagonal(indices[0],indices[1], gameBoard));
                }
            if (isWin == true) {
                if (counter%2 != 0) {
                    System.out.println(nameOne + " has won the game!");
                    break outerloop;
                }
                else {
                    System.out.println(nameTwo + " has won the game!");
                    break outerloop;
                }
            }
        }

        if (isWin == false) {
            System.out.println("Draw! The game has ended in a tie!");
        }
        scanner.close();
    }
    public static void printBoard(char[][] gameBoard) {
        System.out.println();
        for (int i=0; i < 3; i++) {
            for (int j=0; j< 3; j++) {
                System.out.print(" " + gameBoard[i][j]);
                if (j != 2) {
                    System.out.print(" |");
                }
            }
            if (i != 2)
                System.out.println("\n-----------");
        }
        System.out.println();
        System.out.println();
    }

    public static int[] askMove (Scanner scanner, int moveCount, char[][] board, String name, char playerChar) {
        // ask player for move. add char to board if it's valid
        int[] index = new int [2];

        System.out.println(name + ", enter the row & column where you would like to place " + playerChar + ".");
        System.out.println("Row (1, 2, 3): ");
        int row = scanner.nextInt() - 1;
        System.out.println("Column (1, 2, 3): ");
        int col = scanner.nextInt() - 1;

        if (row < 0 || col > 2 || row > 2 || col < 0) {
            System.out.println("Row or Column index is out of bounds. Please choose a valid row and column.");
            index = askMove (scanner, moveCount, board, name, playerChar); 
        }
        else if (board[row][col] != '_') {
            System.out.println("Space is already taken. Please choose a valid row and column.");
            index = askMove (scanner, moveCount, board, name, playerChar);
        }
        else {
            board[row][col] = playerChar;
            index[0] = row;
            index[1] = col;
            printBoard(board);
        }
        return index;
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
}