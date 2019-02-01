package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class BowlTest {

    public static Player player1;
    public static Bowl bowl1;

    public void setUp() {
        player1 = new Player();
        bowl1 = new Bowl(player1);
    }

    public void tearDown() {
        player1 = null;
        bowl1 = null;
    }

    @Test
    public void testCreateNewBowl() {
        setUp();

        int count = bowl1.seedCount();

        Assert.assertEquals(4, count);

        tearDown();
    }

    @Test public void testGetNeighbours() {
        setUp();
        Bowl firstNeighbour = bowl1.getNeighbour();
        Bowl firstNeighbourAlternative = bowl1.getNeighbour(1);
        Bowl kalaha = bowl1.getNeighbour(6);

        Assert.assertEquals(firstNeighbourAlternative, firstNeighbour);
        Assert.assertTrue(kalaha instanceof Kalaha);

        tearDown();
    }

    @Test
    public void testCreateNewKalaha() {
        setUp();
        Kalaha kalaha = (Kalaha)bowl1.getNeighbour(6);

        int count = kalaha.seedCount();

        Assert.assertEquals(0, count);

        tearDown();
    }


    @Test
    public void testMoveSeeds() {
        setUp();
        Bowl bowl = bowl1;

        bowl.moveSeeds();
        int count1 = bowl1.seedCount();
        int count2 = bowl1.getNeighbour().seedCount();
        int count3 = bowl1.getNeighbour(2).seedCount();
        int count4 = bowl1.getNeighbour(3).seedCount();
        int count5 = bowl1.getNeighbour(4).seedCount();

        Assert.assertEquals(0, count1);
        Assert.assertEquals(5, count2);
        Assert.assertEquals(5, count3);
        Assert.assertEquals(5, count4);
        Assert.assertEquals(5, count5);
    }



    @Test
    public void testDistributeSeedsOverOwnKalaha() {
        setUp();
        Bowl bowl = bowl1.getNeighbour(4);

        bowl.moveSeeds();
        int count5 = bowl.seedCount();
        int count6 = bowl.getNeighbour().seedCount();
        int count7 = bowl.getNeighbour(2).seedCount();
        int count8 = bowl.getNeighbour(3).seedCount();
        int count9 = bowl.getNeighbour(4).seedCount();
        int count10 = bowl.getNeighbour(5).seedCount();

        Assert.assertEquals(0, count5);
        Assert.assertEquals(5, count6);
        Assert.assertEquals(1, count7);
        Assert.assertEquals(5, count8);
        Assert.assertEquals(5, count9);
        Assert.assertEquals(4, count10);

        tearDown();
    }

    @Test
    public void testDistributeSeedsOverOpponentKalaha() {
        setUp();
        Bowl bowl = bowl1.getNeighbour(11);

        bowl.moveSeeds();
        int count12 = bowl.seedCount();
        int count13 = bowl.getNeighbour().seedCount();
        int count14 = bowl.getNeighbour(2).seedCount();
        int count1 = bowl.getNeighbour(3).seedCount();
        int count2 = bowl.getNeighbour(4).seedCount();
        int count3 = bowl.getNeighbour(5).seedCount();
        int count4 = bowl.getNeighbour(6).seedCount();

        Assert.assertEquals(0, count12);
        Assert.assertEquals(5, count13);
        Assert.assertEquals(0, count14);
        Assert.assertEquals(5, count1);
        Assert.assertEquals(5, count2);
        Assert.assertEquals(4, count4);

        tearDown();
    }


    @Test
    public void testGettingNeighbourAcross() {
        setUp();
        Bowl bowl = bowl1;
        Bowl expectedNeighbourAcross = bowl1.getNeighbour(12);

        Bowl foundNeighbourAcross = bowl.getCrossNeighbour();

        Assert.assertEquals(expectedNeighbourAcross, foundNeighbourAcross);

        tearDown();
    }

    @Test
    public void testCanStealFromFullNeighbourAcross() {
        setUp();
        Bowl bowl = bowl1;
        Bowl neighbourAcross = bowl1.getCrossNeighbour();

        boolean canSteal = neighbourAcross.canSteal();
        Assert.assertTrue(canSteal);

        tearDown();
    }

    @Test
    public void testCanStealFromEmptyNeighbourAcross() {
        setUp();
        Bowl bowl = bowl1;
        Bowl neighbourAcross = bowl1.getCrossNeighbour();
        neighbourAcross.seeds = 0;

        boolean canSteal = neighbourAcross.canSteal();
        Assert.assertFalse(canSteal);

        tearDown();
    }


    @Test
    public void testStealingFromFullNeighbourAcross() {
        setUp();
        Bowl bowl = bowl1;
        Bowl neighbourAcross = bowl1.getCrossNeighbour();
        Kalaha kalaha = (Kalaha)bowl1.getNeighbour(6);

        boolean canSteal = neighbourAcross.canSteal();
        neighbourAcross.steal();
        int seedsInNeighbourAcross = neighbourAcross.seedCount();
        int seedsInKalaha = kalaha.seedCount();

        Assert.assertTrue(canSteal);
        Assert.assertEquals(0, seedsInNeighbourAcross);
        Assert.assertEquals(5, seedsInKalaha);

        tearDown();
    }

    @Test
    public void testGameOverWithOwnBowlsFull() {
        setUp();

        boolean gameOver = bowl1.gameOver();

        Assert.assertFalse(gameOver);

        tearDown();
    }

    @Test
    public void testGameOverWithOwnBowlsEmpty() {
        setUp();
        Bowl bowl5 = bowl1.getNeighbour(5);
        bowl1.seeds = 0;
        bowl1.getNeighbour(1).seeds = 0;
        bowl1.getNeighbour(2).seeds = 0;
        bowl1.getNeighbour(3).seeds = 0;
        bowl1.getNeighbour(4).seeds = 0;
        bowl1.getNeighbour(5).seeds = 0;

        boolean gameOver1 = bowl1.gameOver();
        boolean gameOver2 = bowl5.gameOver();

        Assert.assertTrue(gameOver1);
        Assert.assertTrue(gameOver2);

        tearDown();
    }

    @Test
    public void testGameOverWithOpponentBowlsEmpty() {
        setUp();
        Bowl bowl5 = bowl1.getNeighbour(5);
        bowl1.getNeighbour(7).seeds = 0;
        bowl1.getNeighbour(8).seeds = 0;
        bowl1.getNeighbour(9).seeds = 0;
        bowl1.getNeighbour(10).seeds = 0;
        bowl1.getNeighbour(11).seeds = 0;
        bowl1.getNeighbour(12).seeds = 0;

        boolean gameOver1 = bowl1.gameOver();
        boolean gameOver2 = bowl5.gameOver();

        Assert.assertFalse(gameOver1);
        Assert.assertFalse(gameOver2);

        tearDown();
    }

    @Test
    public void testDoFirstTurnOnRightBowl() {
        setUp();
        Player player2 = player1.getOpponent();

        bowl1.doTurn();
        boolean isPlayer1Turn = player1.yourTurn();
        boolean isPlayer2Turn = player2.yourTurn();

        Assert.assertFalse(isPlayer1Turn);
        Assert.assertTrue(isPlayer2Turn);

        tearDown();
    }

    @Test
    public void testDoFirstTurnOnWrongBowl() {
        setUp();
        Bowl bowl7 = bowl1.getNeighbour(7);
        Player player2 = player1.getOpponent();

        bowl7.doTurn();
        boolean isPlayer1Turn = player1.yourTurn();
        boolean isPlayer2Turn = player2.yourTurn();

        Assert.assertTrue(isPlayer1Turn);
        Assert.assertFalse(isPlayer2Turn);

        tearDown();
    }

}
