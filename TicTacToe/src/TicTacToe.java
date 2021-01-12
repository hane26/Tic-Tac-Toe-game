import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions;
    static ArrayList<Integer> cpuPoisitions;
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        do{
            playerPositions = new ArrayList<>();
            cpuPoisitions = new ArrayList<>();
            playTicTacToe();
            System.out.println("Do you want to play again? [Y/N]");
            String playAgain = scn.next();
            if(playAgain.equals("N")){
                break;
            }

        }while (true);
    }

    public static void playTicTacToe() {

            char[][] board ={
                    {' ', '|',' ', '|', ' '},
                    { '-','+','-', '+', '-'},
                    {' ', '|',' ', '|', ' '},
                    { '-','+','-', '+', '-'},
                    {' ', '|',' ', '|', ' '}
            };
            printBoard(board);
            while(true){

                Scanner scn = new Scanner(System.in);

                System.out.println("where do you want to place X? Enter position");
                int userPos = scn.nextInt();
                while(!validPos(userPos)){
                    System.out.println("Position taken, plz try another one :) ");
                    userPos = scn.nextInt();

                }
                placePiece(board,userPos,"player");
                //check

                String res = checkWin();
                if(res.length()> 0 ){
                    printBoard(board);
                    System.out.println(res);
                    break;
                }

                // cpu plays
                Random rand = new Random();
                int cpuPos= rand.nextInt(9)+1;
                while(!validPos(cpuPos)){
                    cpuPos = rand.nextInt(9)+1;
                }
                placePiece(board,cpuPos,"cpu");
                printBoard(board);
                //check

                res = checkWin();
                if(res.length()> 0 ){
                    printBoard(board);
                    System.out.println(res);
                    break;
                }

            }

    }

    public static boolean validPos(int pos){
        if(playerPositions.contains(pos) || cpuPoisitions.contains(pos)){
            return false;
        }
        return true;
    }
    public static void printBoard( char [][] board){
        for (char [] row : board ){
            for(char c : row ){
                System.out.print(c);
            }
            System.out.println();

        }
    }
    public static void placePiece(char[][] board, int pos, String user){
        char piece = ' ';
        if(user.equals("player")){
            piece ='X';
            playerPositions.add(pos);

        }else if(user.equals("cpu")){
            piece='O';
            cpuPoisitions.add(pos);;
        }
        switch (pos){
            case 1:
                board[0][0]= piece;
                break;
            case 2:
                board[0][2] = piece;
                break;
            case 3:
                board[0][4] = piece;
                break;
            case 4:
                board[2][0] = piece;
                break;
            case 5:
                board[2][2] = piece;
                break;
            case 6:
                board[2][4] = piece;
                break;
            case 7:
                board[4][0] = piece;
                break;
            case 8:
                board[4][2] = piece;
                break;
            case 9:
                board[4][4] = piece;
                break;
            default:
                System.out.println("Enter between 1 and 9");
                break;
        }
    }
    public static String checkWin(){
        List<Integer> row1 = Arrays.asList(1,2,3);
        List<Integer> row2 = Arrays.asList(4,5,6);
        List<Integer> row3 = Arrays.asList(7,8,9);
        List<Integer> col1 = Arrays.asList(1,4,7);
        List<Integer> col2 = Arrays.asList(2,5,8);
        List<Integer> col3 = Arrays.asList(3,6,9);
        List<Integer> diag1 = Arrays.asList(1,5,9);
        List<Integer> diag2 = Arrays.asList(7,5,3);

        List<List> wins = new ArrayList<List>();
        wins.add(row1);
        wins.add(row2);
        wins.add(row3);
        wins.add(col1);
        wins.add(col2);
        wins.add(col3);
        wins.add(diag1);
        wins.add(diag2);

        String res ="";
        for(List l : wins){
            if(playerPositions.containsAll(l)){
                return "Congrats!!, you WON!!! X)";

            }else if( cpuPoisitions.containsAll(l)){
                return "Better luck next time :( ";

            }else if(playerPositions.size() + cpuPoisitions.size()== 9){
                res = "It's a tie";
            }
        }
        return res;
    }

    }

