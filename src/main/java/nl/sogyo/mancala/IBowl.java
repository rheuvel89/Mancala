package nl.sogyo.mancala;

import java.util.ArrayList;

public interface IBowl {

    public int seedCount();

    public IBowl getNeighbour(int count);

    public void moveSeeds(int count);

    public IBowl getCrossNeighbour(int count, boolean countingUp);

    public void steal(int count);

    public boolean gameOver(Bowl startingBowl);

    public void endGame(int count);

    public void endGame(Kalaha firstKalaha, int count);

    public Player getWinner();

    public Player getWinner(int seedsOppositePlayer);

}
