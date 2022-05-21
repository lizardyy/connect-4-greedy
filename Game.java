public class Game {
    private ConnectFive board;
    private int turnGame;
    private int tokenPlayer;
    private int tokenBot;
    
    public Game(int tokenPlayer, int tokenBot, ConnectFive board) {
        this.turnGame = 1;
        this.tokenPlayer = tokenPlayer;
        this.tokenBot = tokenBot;
        this.board = board;
    }

    public int getTurn() {
        return turnGame;
    }

    public void setTurn(int turn) {
        this.turnGame = turn;
    }

    public void printTurn(){
        if (this.turnGame == tokenPlayer){
            System.out.println("Player's turn");
        } else {
            System.out.println("Bot's turn");
        }
    }
    public void nextTurn(){
        if (this.turnGame == tokenPlayer){
            this.turnGame = tokenBot;
        } else {
            this.turnGame = tokenPlayer;
        }
    }
    
    public boolean endGame() {
        if (board.getWinner() == 0) {
            if (board.isBoardFull()) {
                System.out.println("Draw!");
                return true;
            }
            return false;
        }else{
            if (board.getWinner() == tokenPlayer) {
                System.out.println("Player wins!");
            } else {
                System.out.println("Bot wins!");
            }
            return true;
        }
    }
}
