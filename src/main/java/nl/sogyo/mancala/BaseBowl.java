package nl.sogyo.mancala;

public abstract class BaseBowl {

    protected BaseBowl neighbour;
    protected Player owner;
    protected int seeds = 4;

    public Player getOwner() {
        return owner;
    }

    public int seedCount() {
        return seeds;
    }

    protected void setSeeds(int count) {
        seeds = count;
    }

    public BaseBowl getNeighbour(int count) {
        count--;
        return count < 0 ? this : count == 0 ? neighbour : neighbour.getNeighbour(count);
    }

    public abstract void moveSeeds(int count);

    public abstract BaseBowl getCrossNeighbour(int count, boolean countingUp);

    public abstract void steal(int count);

    public abstract boolean gameOver(Bowl startingBowl);

    public abstract void endGame(int count);

    public abstract void endGame(Kalaha firstKalaha, int count);

    public abstract Player getWinner();

    public abstract Player getWinner(int seedsOppositePlayer);

}
