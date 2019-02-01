package nl.sogyo.mancala;

public class Player {

    private boolean isTurn;
    private Player opponent = null;

    public Player() {
        this.isTurn = true;
        opponent = new Player(this);
    }

    public Player(Player opponent) {
        this.opponent = opponent;
        this.isTurn = !opponent.yourTurn();
    }

    public boolean yourTurn() {
        return isTurn;
    }

    public void flipTurns() {
        isTurn = !isTurn;
        opponent.flipTurn();
    }

    public void flipTurn() {
        isTurn = !isTurn;
    }

    public Player getOpponent() {
        return opponent;
    }


}
