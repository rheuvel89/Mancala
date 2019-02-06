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
        Bowl firstNeighbour = (Bowl)bowl1.getNeighbour();
        Bowl firstNeighbourAlternative = (Bowl)bowl1.getNeighbour(1);
        Kalaha kalaha = (Kalaha)bowl1.getNeighbour(6);

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

        bowl1.moveSeeds();
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
    public void testMoveSeedsFromEmptyBowl() {
        setUp();
        bowl1.setSeeds(0);

        bowl1.moveSeeds();
        int count1 = bowl1.seedCount();
        int count2 = bowl1.getNeighbour().seedCount();
        int count3 = bowl1.getNeighbour(2).seedCount();
        int count4 = bowl1.getNeighbour(3).seedCount();
        int count5 = bowl1.getNeighbour(4).seedCount();

        Assert.assertEquals(0, count1);
        Assert.assertEquals(4, count2);
        Assert.assertEquals(4, count3);
        Assert.assertEquals(4, count4);
        Assert.assertEquals(4, count5);
        Assert.assertTrue(bowl1.getOwner().yourTurn());
    }


    @Test
    public void testDistributeSeedsOverOwnKalaha() {
        setUp();
        Bowl bowl = (Bowl)bowl1.getNeighbour(4);

        bowl.moveSeeds();
        int count5 = bowl.seedCount();
        int count6 = bowl.getNeighbour(1).seedCount();
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
        Bowl bowl = (Bowl)bowl1.getNeighbour(11);

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
        Assert.assertEquals(5, count3);
        Assert.assertEquals(4, count4);

        tearDown();
    }

    @Test
    public void testPutLastSeedInOwnKalaha() {
        setUp();
        Bowl bowl3 = (Bowl)bowl1.getNeighbour(2);

        bowl3.moveSeeds();
        int count3 = bowl3.seedCount();
        int count4 = bowl3.getNeighbour().seedCount();
        int count5 = bowl3.getNeighbour(2).seedCount();
        int count6 = bowl3.getNeighbour(3).seedCount();
        int count7 = bowl3.getNeighbour(4).seedCount();
        int count8 = bowl3.getNeighbour(5).seedCount();

        Assert.assertEquals(0, count3);
        Assert.assertEquals(5, count4);
        Assert.assertEquals(5, count5);
        Assert.assertEquals(5, count6);
        Assert.assertEquals(1, count7);
        Assert.assertEquals(4, count8);

        tearDown();
    }

    @Test
    public void testGettingNeighbourAcross() {
        setUp();
        Bowl expectedNeighbourAcross = (Bowl)bowl1.getNeighbour(12);

        Bowl foundNeighbourAcross = bowl1.getCrossNeighbour();

        Assert.assertEquals(expectedNeighbourAcross, foundNeighbourAcross);

        tearDown();
    }

    @Test
    public void testGettingNeighbourAcrossFromOtherSide() {
        setUp();
        Bowl callingBowl = (Bowl)bowl1.getNeighbour(12);
        Bowl expectedNeighbourAcross = bowl1;

        Bowl foundNeighbourAcross = callingBowl.getCrossNeighbour();

        Assert.assertEquals(expectedNeighbourAcross, foundNeighbourAcross);

        tearDown();
    }

    @Test
    public void testCanStealFromFullNeighbourAcross() {
        setUp();
        Bowl neighbourAcross = bowl1.getCrossNeighbour();

        boolean canSteal = neighbourAcross.canSteal();
        Assert.assertTrue(canSteal);

        tearDown();
    }

    @Test
    public void testCanStealFromEmptyNeighbourAcross() {
        setUp();
        Bowl bowl = bowl1;
        Bowl neighbourAcross = bowl.getCrossNeighbour();
        neighbourAcross.setSeeds(0);

        boolean canSteal = neighbourAcross.canSteal();
        Assert.assertFalse(canSteal);

        tearDown();
    }


    @Test
    public void testStealingFromFullNeighbourAcross() {
        setUp();
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
    public void testCannotStealFromSelf() {
        setUp();
        Bowl startingBowl = (Bowl)bowl1.getNeighbour(4);
        Bowl stealingBowl = (Bowl)bowl1.getNeighbour(7);
        Bowl neighbourAcross = stealingBowl.getCrossNeighbour();
        Kalaha kalaha = (Kalaha)bowl1.getNeighbour(6);
        startingBowl.setSeeds(3);
        stealingBowl.setSeeds(0);

        startingBowl.doTurn();
        int seedsInStartingBowl = startingBowl.seedCount();
        int seedsInStealingBowl = stealingBowl.seedCount();
        int seedsInNeighbourAcross = neighbourAcross.seedCount();
        int seedsInKalaha = kalaha.seedCount();

        Assert.assertEquals(0, seedsInStartingBowl);
        Assert.assertEquals(1, seedsInStealingBowl);
        Assert.assertEquals(5, seedsInNeighbourAcross);
        Assert.assertEquals(1, seedsInKalaha);

        tearDown();
    }

    @Test
    public void testStealingFromTurn() {
        setUp();
        Bowl bowl5 = (Bowl)bowl1.getNeighbour(4);
        Bowl neighbourAcross = bowl5.getCrossNeighbour();
        Kalaha kalaha = (Kalaha)bowl1.getNeighbour(6);
        bowl5.setSeeds(0);

        bowl1.doTurn();
        int seedsInOriginalBowl = bowl1.seedCount();
        int seedsInStealingBowl = bowl5.seedCount();
        int seedsInNeighbourAcross = neighbourAcross.seedCount();
        int seedsInKalaha = kalaha.seedCount();

        Assert.assertEquals(0, seedsInOriginalBowl);
        Assert.assertEquals(0, seedsInStealingBowl);
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
        Bowl bowl5 = (Bowl)bowl1.getNeighbour(5);
        bowl1.setSeeds(0);
        ((Bowl)bowl1.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(4)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(5)).setSeeds(0);

        boolean gameOver1 = bowl1.gameOver();
        boolean gameOver2 = bowl5.gameOver();

        Assert.assertTrue(gameOver1);
        Assert.assertTrue(gameOver2);

        tearDown();
    }

    @Test
    public void testGameOverWithOpponentBowlsEmpty() {
        setUp();
        Bowl bowl5 = (Bowl)bowl1.getNeighbour(5);
        ((Bowl)bowl1.getNeighbour(7)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(8)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(9)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(10)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(11)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(12)).setSeeds(0);

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
        Bowl bowl7 = (Bowl)bowl1.getNeighbour(7);
        Player player2 = player1.getOpponent();

        bowl7.doTurn();
        boolean isPlayer1Turn = player1.yourTurn();
        boolean isPlayer2Turn = player2.yourTurn();

        Assert.assertTrue(isPlayer1Turn);
        Assert.assertFalse(isPlayer2Turn);

        tearDown();
    }

    @Test
    public void testGetWinnerWhenGameNotEnded() {
        setUp();

        bowl1.endGame();
        Player winner = bowl1.getWinner();

        Assert.assertEquals(null, winner);

        tearDown();
    }

    @Test
    public void testEndGameByPlayer1() {
        setUp();
        bowl1.setSeeds(0);
        ((Bowl)bowl1.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(4)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(5)).setSeeds(0);

        bowl1.endGame();
        int count1 = bowl1.seedCount();
        int count2 = bowl1.getNeighbour(1).seedCount();
        int count3 = bowl1.getNeighbour(2).seedCount();
        int count4 = bowl1.getNeighbour(3).seedCount();
        int count5 = bowl1.getNeighbour(4).seedCount();
        int count6 = bowl1.getNeighbour(5).seedCount();
        int count7 = bowl1.getNeighbour(6).seedCount();
        int count8 = bowl1.getNeighbour(7).seedCount();
        int count9 = bowl1.getNeighbour(8).seedCount();
        int count10 = bowl1.getNeighbour(9).seedCount();
        int count11 = bowl1.getNeighbour(10).seedCount();
        int count12 = bowl1.getNeighbour(11).seedCount();
        int count13 = bowl1.getNeighbour(12).seedCount();
        int count14 = bowl1.getNeighbour(13).seedCount();

        Assert.assertEquals(0, count1);
        Assert.assertEquals(0, count2);
        Assert.assertEquals(0, count3);
        Assert.assertEquals(0, count4);
        Assert.assertEquals(0, count5);
        Assert.assertEquals(0, count6);
        Assert.assertEquals(0, count7);
        Assert.assertEquals(0, count8);
        Assert.assertEquals(0, count9);
        Assert.assertEquals(0, count10);
        Assert.assertEquals(0, count11);
        Assert.assertEquals(0, count12);
        Assert.assertEquals(0, count13);
        Assert.assertEquals(24, count14);

        tearDown();
    }

    @Test
    public void testEndGameByPlayer2() {
        setUp();
        Bowl bowl8 = (Bowl)bowl1.getNeighbour(7);
        bowl8.setSeeds(0);
        ((Bowl)bowl8.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(4)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(5)).setSeeds(0);

        bowl1.doTurn();
        bowl8.endGame();
        int count1 = bowl1.seedCount();
        int count2 = bowl1.getNeighbour(1).seedCount();
        int count3 = bowl1.getNeighbour(2).seedCount();
        int count4 = bowl1.getNeighbour(3).seedCount();
        int count5 = bowl1.getNeighbour(4).seedCount();
        int count6 = bowl1.getNeighbour(5).seedCount();
        int count7 = bowl1.getNeighbour(6).seedCount();
        int count8 = bowl1.getNeighbour(7).seedCount();
        int count9 = bowl1.getNeighbour(8).seedCount();
        int count10 = bowl1.getNeighbour(9).seedCount();
        int count11 = bowl1.getNeighbour(10).seedCount();
        int count12 = bowl1.getNeighbour(11).seedCount();
        int count13 = bowl1.getNeighbour(12).seedCount();
        int count14 = bowl1.getNeighbour(13).seedCount();

        Assert.assertEquals(0, count1);
        Assert.assertEquals(0, count2);
        Assert.assertEquals(0, count3);
        Assert.assertEquals(0, count4);
        Assert.assertEquals(0, count5);
        Assert.assertEquals(0, count6);
        Assert.assertEquals(24, count7);
        Assert.assertEquals(0, count8);
        Assert.assertEquals(0, count9);
        Assert.assertEquals(0, count10);
        Assert.assertEquals(0, count11);
        Assert.assertEquals(0, count12);
        Assert.assertEquals(0, count13);
        Assert.assertEquals(0, count14);

        tearDown();
    }

    @Test
    public void testEndGameByPlayer1WithPlayer2BowlsEmpty() {
        setUp();
        Bowl bowl8 = (Bowl)bowl1.getNeighbour(7);
        bowl8.setSeeds(0);
        ((Bowl)bowl8.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(4)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(5)).setSeeds(0);

        bowl1.endGame();
        int count1 = bowl1.seedCount();
        int count2 = bowl1.getNeighbour(1).seedCount();
        int count3 = bowl1.getNeighbour(2).seedCount();
        int count4 = bowl1.getNeighbour(3).seedCount();
        int count5 = bowl1.getNeighbour(4).seedCount();
        int count6 = bowl1.getNeighbour(5).seedCount();
        int count7 = bowl1.getNeighbour(6).seedCount();
        int count8 = bowl1.getNeighbour(7).seedCount();
        int count9 = bowl1.getNeighbour(8).seedCount();
        int count10 = bowl1.getNeighbour(9).seedCount();
        int count11 = bowl1.getNeighbour(10).seedCount();
        int count12 = bowl1.getNeighbour(11).seedCount();
        int count13 = bowl1.getNeighbour(12).seedCount();
        int count14 = bowl1.getNeighbour(13).seedCount();

        Assert.assertEquals(4, count1);
        Assert.assertEquals(4, count2);
        Assert.assertEquals(4, count3);
        Assert.assertEquals(4, count4);
        Assert.assertEquals(4, count5);
        Assert.assertEquals(4, count6);
        Assert.assertEquals(0, count7);
        Assert.assertEquals(0, count8);
        Assert.assertEquals(0, count9);
        Assert.assertEquals(0, count10);
        Assert.assertEquals(0, count11);
        Assert.assertEquals(0, count12);
        Assert.assertEquals(0, count13);
        Assert.assertEquals(0, count14);

        tearDown();
    }

    @Test
    public void testEndGameByPlayer2WithPlayer1BowlsEmpty() {
        setUp();
        Bowl bowl6 = ((Bowl)bowl1.getNeighbour(5));
        Bowl bowl8 = (Bowl)bowl1.getNeighbour(7);
        bowl1.setSeeds(0);
        ((Bowl)bowl1.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(4)).setSeeds(0);

        bowl6.doTurn();
        bowl8.endGame();
        int count1 = bowl1.seedCount();
        int count2 = bowl1.getNeighbour(1).seedCount();
        int count3 = bowl1.getNeighbour(2).seedCount();
        int count4 = bowl1.getNeighbour(3).seedCount();
        int count5 = bowl1.getNeighbour(4).seedCount();
        int count6 = bowl1.getNeighbour(5).seedCount();
        int count7 = bowl1.getNeighbour(6).seedCount();
        int count8 = bowl1.getNeighbour(7).seedCount();
        int count9 = bowl1.getNeighbour(8).seedCount();
        int count10 = bowl1.getNeighbour(9).seedCount();
        int count11 = bowl1.getNeighbour(10).seedCount();
        int count12 = bowl1.getNeighbour(11).seedCount();
        int count13 = bowl1.getNeighbour(12).seedCount();
        int count14 = bowl1.getNeighbour(13).seedCount();

        Assert.assertEquals(0, count1);
        Assert.assertEquals(0, count2);
        Assert.assertEquals(0, count3);
        Assert.assertEquals(0, count4);
        Assert.assertEquals(0, count5);
        Assert.assertEquals(0, count6);
        Assert.assertEquals(1, count7);
        Assert.assertEquals(5, count8);
        Assert.assertEquals(5, count9);
        Assert.assertEquals(5, count10);
        Assert.assertEquals(4, count11);
        Assert.assertEquals(4, count12);
        Assert.assertEquals(4, count13);
        Assert.assertEquals(0, count14);

        tearDown();
    }

    @Test
    public void testGameIsDraw() {
        setUp();

        bowl1.endGame();
        Player winner = bowl1.getWinner();

        Assert.assertEquals(null, winner);

        tearDown();
    }

    @Test
    public void testGameWonByPlayer1() {
        setUp();
        bowl1.setSeeds(0);
        ((Bowl)bowl1.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(4)).setSeeds(0);
        ((Bowl)bowl1.getNeighbour(5)).setSeeds(0);
        ((Kalaha)bowl1.getNeighbour(6)).setSeeds(25);

        bowl1.endGame();
        Player winner = bowl1.getWinner();

        Assert.assertEquals(bowl1.getOwner(), winner);

        tearDown();
    }

    @Test
    public void testGameWonByPlayer2() {
        setUp();
        Bowl bowl8 = (Bowl)bowl1.getNeighbour(7);
        bowl8.setSeeds(0);
        ((Bowl)bowl8.getNeighbour(1)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(2)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(3)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(4)).setSeeds(0);
        ((Bowl)bowl8.getNeighbour(5)).setSeeds(0);
        ((Kalaha)bowl8.getNeighbour(6)).setSeeds(25);

        bowl1.doTurn();
        bowl1.endGame();
        Player winner = bowl1.getWinner();

        Assert.assertEquals(bowl8.getOwner(), winner);

        tearDown();
    }

}
