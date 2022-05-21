import java.util.ArrayList;
import java.util.List;
import java.util.*;

class ConnectFive{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    private int[][] board;
    private int winner;

    public ConnectFive(){
        this.board = new int[6][7];
        // initiate board with 0 (no token)
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = 0;
            }
        }
        this.winner = 0;
    }

    public int getWinner() {
        return winner;
    }
    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void setBoard(int row, int col, int token){
        this.board[row][col] = token;
    }

    public int getBoard(int row, int col){
        return this.board[row][col];
    }


    public int getRow(int position){
        int i;
        for (i = 5; i >= 0; i--) {
            if (this.board[i][position - 1] == 0) {
                break;
            }
        }
        return i;
    }
    public boolean areFourConnected(int row, int col, int player) {

        // horizontalCheck
        for (int j = Math.max(col-3,0); j <= Math.min(col,3); j++) {
            if (this.board[row][j] == player && this.board[row][j + 1] == player && this.board[row][j + 2] == player
                        && this.board[row][j + 3] == player) {
                return true;
            }
        }
        
        // verticalCheck
        int irow = row;
        if (irow + 3 < 6) {
            if (this.board[irow][col] == player && this.board[irow+1][col] == player && this.board[irow+2][col] == player
                && this.board[irow+3][col] == player) {
                    return true;
            }
        }
        // DiagonalCheck
        // diagonal kanan
        int k;
        int startrow = row;
        int startcol = col;
        for (k=0;k<4;k++){
            if (startrow -1 == -1 || startcol-1 == -1){
                break;
            }
            else{
                startrow -= 1;
                startcol -= 1;
            }
        }
        for (int i=0;i<=k;i++){
            if (startrow +i+ 3< 6 && startcol + i + 3 < 7){
                if (this.board[startrow + i][startcol + i] == player && this.board[startrow + i + 1][startcol + i + 1] == player
                    && this.board[startrow + i + 2][startcol + i + 2] == player && this.board[startrow + i + 3][startcol + i + 3] == player) {
                        return true;
                    }
            }
        }

        // diagonal kiri
        startrow = row;
        startcol = col;
        for (k = 0; k < 4; k++) {
            if (startrow - 1 == -1 || startcol + 1 == 7) {
                break;
            } else {
                startrow -= 1;
                startcol += 1;
            }
        }
        for (int i = 0; i <= k; i++) {
            if (startrow + i + 3 < 6 && startcol - i - 3 >= 0) {
                if (this.board[startrow + i][startcol - i] == player && this.board[startrow + i + 1][startcol - i - 1] == player
                    && this.board[startrow + i + 2][startcol - i - 2] == player && this.board[startrow + i + 3][startcol - i - 3] == player) {
                        return true;
                    }
            }
        }
        return false;
    }
    
    public void printBoard(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (j ==0) {
                    System.out.println("+---+---+---+---+---+---+---+");
                    System.out.print("|");
                }
                if (this.board[i][j] == 1) {
                    System.out.print(ANSI_RED_BACKGROUND);
                }
                if (this.board[i][j] == 2){
                    System.out.print(ANSI_YELLOW_BACKGROUND);
                }
                System.out.print(" "+ this.board[i][j] +" ");
                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("+---+---+---+---+---+---+---+");
    }

    public boolean isPositionFull(int position){
        if (!isValid(position)){
            return true;
        }
        else{
            if (this.board[0][position - 1] != 0){
                // System.out.println("this column is full");
                return true;
            }
            else{
                return false;
            }
        } 
    }

    public boolean isValid(int position){
        return position>0 && position<8;
    }

    public void addToken(int position, int token){
        int i;
        for (i = 5; i >= 0; i--) {
            if (this.board[i][position-1] == 0) {
                this.board[i][position-1] = token;
                break;
            }
        }
        if (areFourConnected(i, position-1, token)) {
            this.winner=token;
        }
    }

    public boolean isBoardFull(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    
}