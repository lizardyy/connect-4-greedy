public class Bot {
    int token_bot;
    int token_player;
    ConnectFive board;
    int[][] cost;
    public Bot(ConnectFive board, int token_bot, int token_player ) {
        this.board = board;
        this.cost = new int[7][3];
        this.token_bot = token_bot;
        this.token_player = token_player; 

    }

    public void costPosition(){
        // bagian tengah lebih penting dari bagian samping karena lebih banyak kombinasi yang dapat dibuat ketika memiliki piece di tengah
        if (!this.board.isPositionFull(1)){
            cost[0][2] = 1;
        }
        if (!this.board.isPositionFull(2)){
            cost[1][2] = 2;
        }
        if (!this.board.isPositionFull(3)){
            cost[2][2] = 3;
        }
        if (!this.board.isPositionFull(4)){
            cost[3][2] = 4;
        }
        if (!this.board.isPositionFull(5)){
            cost[4][2] = 3;
        }
        if (!this.board.isPositionFull(6)){
            cost[5][2] = 2;
        }
        if (!this.board.isPositionFull(7)){
            cost[6][2] = 1;
        }
    }

    public void set0(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                cost[i][j] = 0;
            }
        }
    }

    public void avoidTrap(int position){
        int rownext = this.board.getRow(position)-1;
        int col = position -1;
        if (rownext >=0){
            this.board.setBoard(rownext, col, token_player);
            if (this.board.areFourConnected(rownext, col, token_player)){
                for(int i=0;i<3;i++){
                    cost[position-1][i] = 0;
                }
            }
            this.board.setBoard(rownext, col, 0);
        }
    }

    public void opponentConnect2(int position){
        int row = this.board.getRow(position);
        int col = position - 1;

        if(!this.board.isPositionFull(position)){
            if (col + 2 < 7) {
                if (this.board.getBoard(row, col + 1) == this.token_player
                        && this.board.getBoard(row, col + 2) == this.token_player) {
                    this.cost[col][1] += 1;
                }
                if (row - 2 >= 0) {
                    if (this.board.getBoard(row - 1, col + 1) == this.token_player
                            && this.board.getBoard(row - 2, col + 2) == this.token_player) {
                        this.cost[col][1] += 1;
                    }
                }
                
                if (row + 2 < 6) {
                    if (this.board.getBoard(row + 1, col + 1) == this.token_player
                            && this.board.getBoard(row + 2, col + 2) == this.token_player) {
                        this.cost[col][1] += 1;
                    }
                }

            }

            if (col - 2 >= 0) {
                if (this.board.getBoard(row, col - 1) == this.token_player
                        && this.board.getBoard(row, col - 2) == this.token_player) {
                    this.cost[col][1] += 1;
                }
                if (row - 2 >= 0) {
                    if (this.board.getBoard(row - 1, col - 1) == this.token_player
                            && this.board.getBoard(row - 2, col - 2) == this.token_player) {
                        this.cost[col][1] += 1;
                    }
                }

                if (row + 2 < 6) {
                    if (this.board.getBoard(row + 1, col - 1) == this.token_player
                            && this.board.getBoard(row + 2, col - 2) == this.token_player) {
                        this.cost[col][1] += 1;
                    }
                }
            }

            // cek vertikal
            if (row + 2 < 6) {
                if (this.board.getBoard(row + 1, col) == this.token_player
                        && this.board.getBoard(row + 2, col) == this.token_player) {
                    this.cost[col][1] += 1;
                }
            }

            if (row - 1 >= 0 && row + 1 < 6 && col - 1 >= 0 && col + 1 < 7) {
                if (this.board.getBoard(row - 1, col - 1) == this.token_player
                        && this.board.getBoard(row +1, col + 1) == this.token_player) {
                    this.cost[col][1] += 1;
                }
                if (this.board.getBoard(row - 1, col + 1) == this.token_player
                        && this.board.getBoard(row + 1, col - 1) == this.token_player) {
                    this.cost[col][1] += 1;
                }
            }

            if (col - 1 >= 0 && col + 1 < 7) {
                if (this.board.getBoard(row, col - 1) == this.token_player
                        && this.board.getBoard(row, col + 1) == this.token_player) {
                    this.cost[col][1] += 1;
                }
            }
        }
        

    }

    public void botConnect2(int position) {
        int row = this.board.getRow(position);
        int col = position - 1;
        // cek horizontal;
        if(!this.board.isPositionFull(position)){
            if (col + 2 < 7) {
                if (this.board.getBoard(row, col + 1) == this.token_player
                        && this.board.getBoard(row, col + 2) == this.token_player) {
                    this.cost[col][0] += 1;
                }
                if (row - 2 >= 0) {
                    if (this.board.getBoard(row - 1, col + 1) == this.token_player
                            && this.board.getBoard(row - 2, col + 2) == this.token_player) {
                        this.cost[col][0] += 1;
                    }
                }

                if (row + 2 < 6) {
                    if (this.board.getBoard(row + 1, col + 1) == this.token_player
                            && this.board.getBoard(row + 2, col + 2) == this.token_player) {
                        this.cost[col][0] += 1;
                    }
                }

            }
            if (col - 2 >= 0) {
                if (this.board.getBoard(row, col - 1) == this.token_player
                        && this.board.getBoard(row, col - 2) == this.token_player) {
                    this.cost[col][0] += 1;
                }
                if (row - 2 >= 0) {
                    if (this.board.getBoard(row - 1, col - 1) == this.token_player
                            && this.board.getBoard(row - 2, col - 2) == this.token_player) {
                        this.cost[col][0] += 1;
                    }
                }

                if (row + 2 < 6) {
                    if (this.board.getBoard(row + 1, col - 1) == this.token_player
                            && this.board.getBoard(row + 2, col - 2) == this.token_player) {
                        this.cost[col][0] += 1;
                    }
                }
            }

            // cek vertikal
            if (row + 2 < 6) {
                if (this.board.getBoard(row + 1, col) == this.token_player
                        && this.board.getBoard(row + 2, col) == this.token_player) {
                    this.cost[col][0] += 1;
                }
            }
        }

    }

    public boolean connect3(int position, int token){
        int row = this.board.getRow(position);
        int col = position-1;
        this.board.setBoard(row,col,token);
        if (this.board.areFourConnected(row, col, token)){
            this.board.setBoard(row, col, 0);
            return true;
        }
        this.board.setBoard(row, col, 0);
        return false;
    }

    public void countCost(){
        costPosition();
        for (int i = 1; i <= 7; i++) {
            opponentConnect2(i);
            botConnect2(i);
            avoidTrap(i);
        }
        
    }
    public int getMove(){
        set0();
        countCost();
        int idxmax = 0;

        for (int i = 1;i <=7; i++){
            if(!this.board.isPositionFull(i)){
                if (connect3(i, this.token_bot)) {
                    return i;
                }
                if (connect3(i, this.token_player)) {
                    return i;
                }
            }
        }
        
        for (int i = 1; i <= 6; i++) {
            if(!this.board.isPositionFull(i)){
                if (token_player==1){
                    if (this.cost[i][1] > this.cost[idxmax][1]) {
                        idxmax = i;
                    } else {
                        if (this.cost[i][1] == this.cost[idxmax][1]) {
                            if (this.cost[i][0] > this.cost[idxmax][0]) {
                                idxmax = i;
                            } else {
                                if (this.cost[i][0] == this.cost[idxmax][0]) {
                                    if (this.cost[i][2] > this.cost[idxmax][2]) {
                                        idxmax = i;
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    if (this.cost[i][0] > this.cost[idxmax][0]) {
                        idxmax = i;
                    } else {
                        if (this.cost[i][0] == this.cost[idxmax][0]) {
                            if (this.cost[i][1] > this.cost[idxmax][1]) {
                                idxmax = i;
                            } else {
                                if (this.cost[i][1] == this.cost[idxmax][1]) {
                                    if (this.cost[i][2] > this.cost[idxmax][2]) {
                                        idxmax = i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        boolean allzero = true;
        for (int i = 0; i <= 6; i++) {
            if (this.cost[i][2] != 0) {
                allzero = false;
            }
        }
        if (allzero) {
            for (int i = 1; i <=7; i++) {
                if(!this.board.isPositionFull(i)){
                    return i;
                }
            }
        }
        return idxmax+1;
    }

    public void printCost(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.cost[i][j] + " ");
            }
            System.out.println();
        }
    }
}
