public class FacadeDesignPattern {
    public static void main(String[] args) {
        TicTacToeGameFacade game = new TicTacToeGameFacade();

        game.playMove(0, 0);
        game.playMove(1, 0);
        game.playMove(0, 1);
        game.playMove(1, 1);
        game.playMove(0, 2);
    }
}

class TicTacToeGameFacade {
    private TicTacToeBoard board = new TicTacToeBoard();
    private TicTacToeTurnManager turnManager = new TicTacToeTurnManager();
    private TicTacToeWinChecker winChecker = new TicTacToeWinChecker();
    private boolean gameOver = false;

    public void playMove(int row, int column) {
        if (gameOver) {
            System.out.println("Game already finished.");
            return;
        }

        char currentPlayer = turnManager.getCurrentPlayer();

        if (!board.placeMark(row, column, currentPlayer)) {
            System.out.println("Invalid move for player " + currentPlayer);
            return;
        }

        System.out.println("Player " + currentPlayer + " played at (" + row + ", " + column + ")");
        board.printBoard();

        if (winChecker.hasWon(board.getBoard(), currentPlayer)) {
            System.out.println("Player " + currentPlayer + " won the game.");
            gameOver = true;
        } else if (winChecker.isDraw(board.getBoard())) {
            System.out.println("Game ended in a draw.");
            gameOver = true;
        } else {
            turnManager.switchPlayer();
        }
    }
}

class TicTacToeBoard {
    private char[][] board = new char[3][3];

    public TicTacToeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = '-';
            }
        }
    }

    public boolean placeMark(int row, int column, char mark) {
        if (row < 0 || row >= 3 || column < 0 || column >= 3) {
            return false;
        }

        if (board[row][column] != '-') {
            return false;
        }

        board[row][column] = mark;
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            System.out.println(board[row][0] + " " + board[row][1] + " " + board[row][2]);
        }

        System.out.println();
    }
}

class TicTacToeTurnManager {
    private char currentPlayer = 'X';

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }
}

class TicTacToeWinChecker {
    public boolean hasWon(char[][] board, char mark) {
        for (int index = 0; index < 3; index++) {
            if (board[index][0] == mark && board[index][1] == mark && board[index][2] == mark) {
                return true;
            }

            if (board[0][index] == mark && board[1][index] == mark && board[2][index] == mark) {
                return true;
            }
        }

        return board[0][0] == mark && board[1][1] == mark && board[2][2] == mark ||
                board[0][2] == mark && board[1][1] == mark && board[2][0] == mark;
    }

    public boolean isDraw(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] == '-') {
                    return false;
                }
            }
        }

        return true;
    }
}
