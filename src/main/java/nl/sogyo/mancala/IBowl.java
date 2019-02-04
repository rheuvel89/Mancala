package nl.sogyo.mancala;

public interface IBowl {

    public int seedCount();

    public IBowl getNeighbour();

    public IBowl getNeighbour(int count);

    public void moveSeeds();

    public void moveSeeds(int count);

    public IBowl getCrossNeighbour();

    public IBowl getCrossNeighbour(int count);

    public boolean canSteal();

    public void steal();

    public void steal(int count);

    public boolean gameOver();

    public boolean gameOver(Bowl startingBowl);


}
