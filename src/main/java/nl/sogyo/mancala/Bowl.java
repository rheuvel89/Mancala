package nl.sogyo.mancala;

import java.util.ArrayList;

public class Bowl implements IBowl {

    private IBowl neighbour;
    private Player owner;
    private int seeds = 4;

    public Bowl(Player owner) {
        this.owner = owner;
        this.neighbour = new Bowl(owner, 13, this);
    }

    public Bowl(Player owner, int count, Bowl firstBowl) {
        this.owner = owner;
        count--;
        if ((count - 1) % 7 == 0) {
            neighbour = new Kalaha(owner, count, firstBowl);
        } else {
            neighbour = new Bowl(owner, count, firstBowl);
        }
    }

    protected Player getOwner() {
        return owner;
    }

    public int seedCount() {
        return seeds;
    }

    protected void setSeeds(int count) {
        seeds = count;
    }

    public IBowl getNeighbour() {
        return neighbour;
    }

    public IBowl getNeighbour(int count) {
        count--;
        return count < 0 ? this : count == 0 ? neighbour : neighbour.getNeighbour(count);
    }

    public void doTurn() {
        if (owner.yourTurn()) {
            moveSeeds();
        }
    }

    public void moveSeeds() {
        if (seeds > 0) {
            neighbour.moveSeeds(seeds);
            seeds = 0;
        }
    }

    public void moveSeeds(int count) {
        count--;
        seeds++;
        if (count > 0) {
            neighbour.moveSeeds(count);
        } else if (count == 0 && seeds == 1) {
            Bowl neighbourAcross = getCrossNeighbour();
            trySteal(neighbourAcross);
            owner.flipTurns();
        } else {
            owner.flipTurns();
        }
    }

    private void trySteal(Bowl neighbourAcross) {
        boolean canSteal = neighbourAcross.canSteal();
        if (canSteal) {
            neighbourAcross.steal();
            seeds = 0;
        }
    }

    public Bowl getCrossNeighbour() {
        return (Bowl)neighbour.getCrossNeighbour(1, true);
    }

    public Bowl getCrossNeighbour(int count, boolean countingUp) {
        int change = countingUp ? 1 : -1;
        count += change;
        return count == 0 ? this : (Bowl)neighbour.getCrossNeighbour(count, countingUp);
    }

    public boolean canSteal() {
        return !owner.yourTurn() && seeds > 0;
    }

    public void steal() {
        neighbour.steal(seeds + 1);
        seeds = 0;
    }

    public void steal(int count) {
        neighbour.steal(count);
    }

    public boolean gameOver() {
        boolean onMySide = owner.yourTurn();
        return seeds == 0 || !onMySide ? neighbour.gameOver(this) : false;
    }

    public boolean gameOver(Bowl startingBowl) {
        boolean onMySide = owner.yourTurn();
        return seeds == 0 || !onMySide ? startingBowl == this ? true : neighbour.gameOver(startingBowl) : false;
    }

    public void endGame() {
        if (gameOver()) {
            int count = seeds;
            seeds = 0;
            neighbour.endGame(count);
        }
    }

    public void endGame(int count) {
        count += seeds;
        seeds = 0;
        neighbour.endGame(count);
    }

    public void endGame(Kalaha firstKalaha, int count) {
        count += seeds;
        seeds = 0;
        neighbour.endGame(firstKalaha, count);
    }

    public Player getWinner() {
        return gameOver() ? neighbour.getWinner() : null;
    }

    public Player getWinner(int seedsOppositePlayer) {
        return neighbour.getWinner(seedsOppositePlayer);
    }

    public String getGameState(IBowl firstBowl) {
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


}
