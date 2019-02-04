package nl.sogyo.mancala;

public class Kalaha implements IBowl {

    protected IBowl neighbour;
    protected Player owner;
    protected int seeds = 4;

    public Kalaha(Player owner, int count, Bowl firstBowl) {
        this.owner = owner;
        seeds = 0;
        count--;
        if (count == 0) {
            neighbour = firstBowl;
        } else {
            neighbour = new Bowl(owner.getOpponent(), count, firstBowl);
        }
    }

    public int seedCount() {
        return seeds;
    }

    public IBowl getNeighbour(int count) {
        count--;
        return count < 1 ? neighbour : neighbour.getNeighbour(count);
    }

    @Override
    public Bowl getCrossNeighbour(int count) {
        return (Bowl)neighbour.getCrossNeighbour(count);
    }

    @Override
    public void steal(int count) {
        boolean onMySide = owner.yourTurn();
        if (onMySide)
            seeds += count;
        else
            neighbour.steal(count);
    }

    @Override
    public void moveSeeds(int count) { //TODO: Revisit, doesnt seem right
        boolean onMySide = owner.yourTurn();
        if (onMySide) {
            count--;
            seeds++;
        } if (count > 0) {
            neighbour.moveSeeds(count);
        }
    }

    @Override
    public boolean gameOver(Bowl startingBowl) {
        return neighbour.gameOver(startingBowl);
    }

    @Override
    public void endGame(int count) {
        seeds += count;
        neighbour.endGame(this, 0);
    }

    @Override
    public void endGame(Kalaha firstKalaha, int count) {
        seeds += count;
        if (firstKalaha != this) {
            neighbour.endGame(firstKalaha, 0);
        }
    }

    @Override
    public Player getWinner() {
        return neighbour.getWinner(seeds);
    }

    @Override
    public Player getWinner(int seedsOppositePlayer) { //TODO: Returning null on draw
        return seeds != seedsOppositePlayer ? seeds > seedsOppositePlayer ? owner : owner.getOpponent() : null;
    }

}
