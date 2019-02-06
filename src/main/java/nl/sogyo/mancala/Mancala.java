package nl.sogyo.mancala;

import java.util.Scanner;

public class Mancala {

    private static Scanner in = new Scanner(System.in);
    private static Player player1 = new Player();
    private static Bowl bowl1 = new Bowl(player1);

    public static void main( String[] args ) {
        while (!bowl1.gameOver()) {
            doTurn();
        }
        endGame();
    }

    private static void printState() {
        System.out.println("14|13|12|11|10|9 |8---");
        System.out.println(getGameState(bowl1));
        System.out.println("---1 |2 |3 |4 |5 |6 |7");

    }

    private static String getGameState(IBowl firstBowl) {
        String returnString = "";
        returnString += "   " + firstBowl.getNeighbour(12).seedCount();
        returnString += "  " + firstBowl.getNeighbour(11).seedCount();
        returnString += "  " + firstBowl.getNeighbour(10).seedCount();
        returnString += "  " + firstBowl.getNeighbour(9).seedCount();
        returnString += "  " + firstBowl.getNeighbour(8).seedCount();
        returnString += "  " + firstBowl.getNeighbour(7).seedCount();
        returnString += "  \n";
        returnString += firstBowl.getNeighbour(13).seedCount() +
                "                    " +
                firstBowl.getNeighbour(6).seedCount() + "\n";
        returnString += "   " + firstBowl.seedCount();
        returnString += "  " + firstBowl.getNeighbour(1).seedCount();
        returnString += "  " + firstBowl.getNeighbour(2).seedCount();
        returnString += "  " + firstBowl.getNeighbour(3).seedCount();
        returnString += "  " + firstBowl.getNeighbour(4).seedCount();
        returnString += "  " + firstBowl.getNeighbour(5).seedCount();
        return returnString;
    }

    private static int getInput(String prompt) {
        String input = "";
        while (input.length() < 1 || !input.matches("[1-9]|1[0-4]")) {
            System.out.print(prompt);
            input = in.nextLine();
        }
        return Integer.parseInt(input) - 1;
    }

    private static void doTurn() {
        printState();
        IBowl turnBowl = bowl1.getNeighbour(getInput(player1.yourTurn() ? "Player 1: " : "Player 2: "));
        if (turnBowl instanceof Bowl) {
            ((Bowl)turnBowl).doTurn();
        }
    }

    private static void endGame() {
        bowl1.endGame();
        bowl1.getWinner();
        printState();
    }

}
