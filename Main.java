import java.util.*;
public class Main {
    
    public static void main(String[] args) {
        ConnectFive board = new ConnectFive();
        Game game;
        Bot bot;
        int position;
        int player_turn;
        int bot_turn;
        Scanner scanner = new Scanner(System.in);

        // test
        Random ran = new Random();

        System.out.println("Welcome to Connect Five!");
        System.out.println("Choose your turn: 1 or 2");
        player_turn = Integer.parseInt(System.console().readLine());
        if (player_turn ==1){
            bot_turn = 2;
        } else {
            bot_turn = 1;
        }
        game = new Game(player_turn, bot_turn, board);
        bot = new Bot(board, bot_turn, player_turn);
        while(!game.endGame()){
            position = 0;
            if (game.getTurn() == player_turn){
                while (!board.isValid(position) || board.isPositionFull(position)) {
                    System.out.println("Choose column to drop token: ");
                    position = scanner.nextInt();
                    // position = ran.nextInt(100) % 7 + 1;
                }
            }
            else{
                position = bot.getMove();
                bot.printCost();
                //test
                // System.out.println("Choose column to drop token: ");
                // position = scanner.nextInt();
            }
            board.addToken(position, game.getTurn());
            board.printBoard();
            game.nextTurn();
        }
    }
}
