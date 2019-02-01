package nl.sogyo.mancala;

public class Kalaha extends Bowl {

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

    @Override
    public void doTurn() {
    }

    public Bowl getCrossNeighbour() {
        return this;
    }

    public Bowl getCrossNeighbour(int count) {
        return neighbour.getCrossNeighbour(count);
    }

    @Override
    public boolean canSteal() {
        return false;
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
    public void moveSeeds(int count) {
        boolean onMySide = owner.yourTurn();
        if (onMySide && count > 1) {
            count--;
            seeds++;
        }
        neighbour.moveSeeds(count);
    }

    public boolean gameOver(Bowl startingBowl) {
        return neighbour.gameOver(startingBowl);
    }

}
