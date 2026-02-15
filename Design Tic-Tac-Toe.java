// Assume the following rules are for the
// tic-tac-toe game on an n x n board
// between two players:
// A move is guaranteed to be valid
// and is placed on an empty block.
// Once a winning condition is reached,
// no more moves are allowed.
// A player who succeeds in placing n of
// their marks in a horizontal, vertical,
// or diagonal row wins the game.
// Implement the TicTacToe class:

// TicTacToe(int n) Initializes the object
// the size of the board n.
// int move(int row, int col, int player)
// Indicates that the player with id player
// plays at the cell (row, col) of the board.
// The move is guaranteed to be a valid 
// move, and the two players alternate in
// making moves. Return
// 0 if there is no winner after the
// move,
// 1 if player 1 is the winner after 
// the move, or
// 2 if player 2 is the winner after the move.

// 2 <= n <= 100
// player is 1 or 2.
// 0 <= row, col < n
// (row, col) are unique for each different
// call to move.
// At most n2 calls will be made to move.
class TicTacToe {
    
    int[] row;
    int[] col;
    int diag;
    int antiDiag;
    int size;
    public TicTacToe(int n) {
        size=n;
        row=new int[n];
        col=new int[n];
        diag=0;
        antiDiag=0;

    }
    
    public int move(int r, int c, int player) {
        int toAdd=player==1?1:-1;

        row[r]+=toAdd;
        col[c]+=toAdd;
        if(r==c) diag+=toAdd;
        if(r+c==size-1) antiDiag+=toAdd;

        if(Math.abs(row[r]) == size ||
           Math.abs(col[c]) == size ||
           Math.abs(diag) == size ||
           Math.abs(antiDiag) == size 
        ){
           return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
 
 //time:O(1)
 //space:O(n)


