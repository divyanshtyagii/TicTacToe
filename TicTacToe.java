import java.util.Scanner;

public class TicTacToe {
    private static char[][] board;
    private static char currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            boolean gameWon = false;
            boolean draw = false;

            while (!gameWon && !draw) {
                displayBoard();
                int[] move = getPlayerMove(scanner);
                int row = move[0];
                int col = move[1];

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    gameWon = checkWin();
                    draw = checkDraw();

                    if (!gameWon && !draw) {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            displayBoard();
            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("It's a draw!");
            }

            System.out.print("Play again? (yes/no): ");
            String choice = scanner.next().toLowerCase();
            playAgain = choice.equals("yes");
        }

        scanner.close();
    }

    private static void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        currentPlayer = 'X';
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static int[] getPlayerMove(Scanner scanner) {
        int[] move = new int[2];
        System.out.print("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
        move[0] = scanner.nextInt() - 1;
        move[1] = scanner.nextInt() - 1;
        return move;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}