package nl.sogyo.mancala;

import java.util.Optional;

public class Mancala {

    private Player player1 = new Player();
    private Bowl bowl1 = new Bowl(player1);


    public void doTurn(int position) {
        BaseBowl turnBowl = bowl1.getNeighbour(position);
        if (turnBowl instanceof Bowl && !bowl1.gameOver()) {
            ((Bowl)turnBowl).doTurn();
        }
    }

    public boolean gameOver() {
        return bowl1.gameOver();
    }

    public Optional<Player> getWinner() {
        if (gameOver()) {
            bowl1.endGame();
            Player winner = bowl1.getWinner();
            return winner != null ? Optional.of(winner) : Optional.empty();
        }
        return null;
    }

    public int getCountOf(int position) {
        BaseBowl bowl = bowl1.getNeighbour(position);
        return bowl.seedCount();
    }

    public Player getActivePlayer() {
        return player1.yourTurn() ? player1 : player1.getOpponent();
    }

}
