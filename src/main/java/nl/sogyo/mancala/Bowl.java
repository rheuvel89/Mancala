package nl.sogyo.mancala;

public class Bowl {

    protected Bowl neighbour;
    protected Player owner;
    protected int seeds = 4;


    public Bowl() {}

    // TODO: Bowl() creates Players! -> Make interface implemented by Kalaha and Bowl
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

    public int seedCount() {
        return seeds;
    }

    public Bowl getNeighbour() {
        return neighbour;
    }

    public Bowl getNeighbour(int count) {
        count--;
        if (count < 1) {
            return neighbour;
        } else {
            return neighbour.getNeighbour(count);
        }
    }

    public void doTurn() {
        if (owner.yourTurn()) {
            moveSeeds();
        }
    }

    public void moveSeeds() {
        neighbour.moveSeeds(seeds);
        seeds = 0;
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
        if (canSteal)
            neighbourAcross.steal();
    }

    public Bowl getCrossNeighbour() {
        return neighbour.getCrossNeighbour(1);
    }

    public Bowl getCrossNeighbour(int count) {
        boolean onMySide = owner.yourTurn();
        int change = onMySide ? 1 : -1;
        count += change;
        if (count == 0) {
            return this;
        } else {
            return neighbour.getCrossNeighbour(count);
        }
    }

    public boolean canSteal() {
        return seeds > 0;
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
        int count = seeds;
        seeds = 0;
        neighbour.endGame(count);
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
        return neighbour.getWinner();
    }

    public Player getWinner(int seedsOppositePlayer) {
        return neighbour.getWinner(seedsOppositePlayer);
    }


}
