package nl.sogyo.mancala;

public class Bowl extends BaseBowl {

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

    public BaseBowl getNeighbour() {
        return neighbour;
    }

    public void doTurn() {
        if (owner.yourTurn()) {
            moveSeeds();
        }
    }

    public void moveSeeds() {
        if (seeds > 0) {
            int count = seeds;
            seeds = 0;
            neighbour.moveSeeds(count);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void steal(int count) {
        neighbour.steal(count);
    }

    public boolean gameOver() {
        boolean onMySide = owner.yourTurn();
        return seeds == 0 || !onMySide ? neighbour.gameOver(this) : false;
    }

    @Override
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

    @Override
    public void endGame(int count) {
        count += seeds;
        seeds = 0;
        neighbour.endGame(count);
    }

    @Override
    public void endGame(Kalaha firstKalaha, int count) {
        count += seeds;
        seeds = 0;
        neighbour.endGame(firstKalaha, count);
    }

    @Override
    public Player getWinner() {
        return gameOver() ? neighbour.getWinner() : null;
    }

    @Override
    public Player getWinner(int seedsOppositePlayer) {
        return neighbour.getWinner(seedsOppositePlayer);
    }

}
