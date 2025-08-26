import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ConnectFour implements AbstractStrategyGame{
    private char[][] grid;
    private Queue<Integer>players;
    private int currPlayer;
    private int winner;
    private int length;
    private int width;

    public ConnectFour(){
        this.players = new LinkedList<>();
        this.players.add(1);
        this.players.add(2); 
        this.grid = new char[][]{{'-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-'},
                                 {'-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-'},
                                 {'-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-'}};
        this.length = grid.length;
        this.width = grid[0].length;
    }

    @Override
    public String instructions() {
        String instructions = "";
        instructions += "Welcome to Connect Four!\n";
        instructions += "The objective of the game is to get 4 spots in a row diagonally, verticalally\n";
        instructions += "You do this by dropping tokens in through the top of the grid\n"; 
        instructions += "Every player has one move in which they can either drop one of their tokens";
        instructions += " from the top of the grid, or remove one of their tokens from the bottom\n";
        instructions += "Player 1 is X, and Player 2 is O\n";
        instructions += "You will be playing on a grid that is 7 wide and 6 tall\n";
        instructions += "Good Luck!\n";
        return instructions;
    }

    public boolean isGameOver() {
        if(verticalCheck('X') || horizontalCheck('X') || diagonalCheck('X')){
            winner = 1;
            return true;
        }
        if(verticalCheck('O') || horizontalCheck('O') || diagonalCheck('O')){
            winner = 2;
            return true;
        }
        if(boardFull()){
            winner = -1;
            return true;
        }
        return false;
    }

    private boolean boardFull(){
        boolean check = true;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                if(grid[i][j] != 'X' && grid[i][j] != 'O'){
                    check = false;
                }
            }
        }
        return check;
    }

    private boolean verticalCheck(char curr){
        boolean check = false;
        int i = 0;
        while(i < width && check == false){
            int j = 0;
            boolean check1 = false;
            while(j+3<length && check1 == false){
                int count = 0;
                for(int y = j; y<=j+3; y++){
                    if(grid[y][i] == curr){
                        count++;
                    }
                }
                if(count == 4){
                    check1 = true;
                }
                j++;
            }
            if(check1 == true){
                check = true;
            }
            i++;
        }
        return check;
    }

    private boolean horizontalCheck(char curr){
        boolean check = false;
        int i = 0;
        while(i < length && check == false){
            int j = 0;
            boolean check1 = false;
            while(j+3<width && check1 == false){
                int count = 0;
                for(int y = j; y<=j+3; y++){
                    if(grid[i][y] == curr){
                        count++;
                    }
                }
                if(count == 4){
                    check1 = true;
                }
                j++;
            }
            if(check1 == true){
                check = true;
            }
            i++;
        }
        return check;
    }

    private boolean diagonalCheck(char curr){
        boolean result = false;
        boolean check1 = diagonalCheckHelper(curr);
        boolean check2 = reverseDiagonalCheckHelper(curr);
        if(check1 || check2){
            result = true;
        }
        return result;
    }

    private boolean diagonalCheckHelper(char curr){
        boolean b = false;
        int i = length-1;
        while(i >= length-3 && b == false){
            int j = 0;
            boolean a = false;
            while(j < width-3 && a == false){
                int count = 0;
                for(int y = 0; y < 4; y++){
                    char check = grid[i-y][j+y];
                    if(check==curr){
                        count++;
                    }
                }
                if(count == 4){
                    a = true;
                }
                j++;
            }
            if(a == true){
                b = true;
            }
            i--;
        }
        return b;
    }

    public boolean reverseDiagonalCheckHelper(char curr){
        boolean b = false;
        int i = length-1;
        while(i >= length-3 && b == false){
            int j = width-1;
            boolean a = false;
            while(j >= width-4 && a == false){
                int count = 0;
                for(int y = 0; y < 4; y++){
                    char check = grid[i-y][j-y];
                    if(check==curr){
                        count++;
                    }
                }
                if(count == 4){
                    b = true;
                }
                j--;
            }
            if(a == true){
                b = true;
            }
            i--;
        }
        return b;
    }
    

    @Override
    public int getWinner() {
        return winner;
    }

    @Override
    public int getNextPlayer() {
        currPlayer = players.remove();
        players.add(currPlayer);
        return currPlayer;
    }

    @Override
    public void makeMove(Scanner input) {
        char token = ' ';
        if(currPlayer == 1){
            token = 'X';
        }else if(currPlayer == 2){
            token = 'O';
        }
        System.out.println("Do you want to add or remove?");
        String decision = input.next();
        if(!decision.equals("add") && !decision.equals("remove")){
            throw new IllegalArgumentException("invalid input");
        }
        if(decision.equals("add")){
            System.out.println("Where do you want to add your token?");
            System.out.println("x coordinate:");
            int x = input.nextInt();
            System.out.println("y coordinate:");
            int y = input.nextInt();
            if(x < 0 || width <= x){
                throw new IllegalArgumentException("x coordinate out of bounds");
            }
            if(y < 0 || length <= y ){
                throw new IllegalArgumentException("y coordinate out of bounds");
            }
            if(y < length-1){
                System.out.println(grid[y+1][x]);
                if(grid[y+1][x] != 'X' && grid[y+1][x] != 'O'){
                    throw new IllegalArgumentException("Cannot place token above empty spot");
                }
            }
            if(grid[y][x] == 'X' || grid[y][x] == 'O'){
                throw new IllegalArgumentException("Token is already there");
            }
            grid[y][x] = token;
        }else if(decision.equals("remove")){
            System.out.println("Where do you want to remove your token?");
            System.out.println("x coordinate:");
            int x = input.nextInt();
            if(x < 0 || width <= x){
                throw new IllegalArgumentException("x coordinate out of bounds");
            }
            if(grid[length-1][x] != token){
                throw new IllegalArgumentException("Must choose a bottom row with your token");
            }
            for(int i = length-1; i > 0; i--){
                grid[i][x] = grid[i-1][x];
            }
            grid[x][0] = '-';
        }
    }

    @Override
    public String toString(){
        String gameState = "";
        gameState+="  0 1 2 3 4 5 6\n";
        for(int i = 0; i < length; i++){
            gameState+=i;
            for(int j = 0; j < width; j++){
                gameState += " " + grid[i][j];
            }
            gameState+="\n";
        }
        return gameState;
    }

}
