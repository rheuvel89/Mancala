package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class MancalaTest {

    public static Mancala mancala;

    public void setUp() {
        mancala = new Mancala();
    }

    public void tearDown() {
        mancala = null;
    }

    @Test
    public void testDoTurnOnBowlInActiveGame() {
        setUp();
        mancala.doTurn(0);

        int bowl1Count = mancala.getCountOf(0);

        Assert.assertEquals(0, bowl1Count);

        tearDown();
    }

    @Test
    public void testDoTurnOnKalahaInActiveGame() {
        setUp();
        mancala.doTurn(6);

        int bowl6Count = mancala.getCountOf(5);
        int bowl7Count = mancala.getCountOf(6);
        int bowl8Count = mancala.getCountOf(7);

        Assert.assertEquals(4, bowl6Count);
        Assert.assertEquals(0, bowl7Count);
        Assert.assertEquals(4, bowl8Count);

        tearDown();
    }

    @Test
    public void testGetWinnerInActiveGame() {
        setUp();

        Optional winner = mancala.getWinner();

        Assert.assertEquals(null, winner);

        tearDown();
    }

}
