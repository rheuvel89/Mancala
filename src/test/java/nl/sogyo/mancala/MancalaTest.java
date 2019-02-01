package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    /*public static Player player1 = new Player();
    public static Player player2 = new Player(player1);
    public static Bowl[] bowls = {new Bowl(player1), new Bowl(player1), new Bowl(player1),
            new Bowl(player1), new Bowl(player1), new Bowl(player1), new Kalaha(player1),
            new Bowl(player2), new Bowl(player2), new Bowl(player2),
            new Bowl(player2), new Bowl(player2), new Bowl(player2), new Kalaha(player2)};

    public void setUp() {
        bowls[0].linkNeighbour(bowls[1]);
        bowls[1].linkNeighbour(bowls[2]);
        bowls[2].linkNeighbour(bowls[3]);
        bowls[3].linkNeighbour(bowls[4]);
        bowls[4].linkNeighbour(bowls[5]);
        bowls[5].linkNeighbour(bowls[6]);
        bowls[6].linkNeighbour(bowls[7]);
        bowls[7].linkNeighbour(bowls[8]);
        bowls[8].linkNeighbour(bowls[9]);
        bowls[9].linkNeighbour(bowls[10]);
        bowls[10].linkNeighbour(bowls[11]);
        bowls[11].linkNeighbour(bowls[12]);
        bowls[12].linkNeighbour(bowls[13]);
        bowls[13].linkNeighbour(bowls[0]);
    }

    public void tearDown() {
        player1 = new Player();
        player2 = new Player(player1);
        bowls = new Bowl[] {new Bowl(player1), new Bowl(player1), new Bowl(player1),
                new Bowl(player1), new Bowl(player1), new Bowl(player1), new Kalaha(player1),
                new Bowl(player2), new Bowl(player2), new Bowl(player2),
                new Bowl(player2), new Bowl(player2), new Bowl(player2), new Kalaha(player2)};
    }

    @Test
    public void testApp() {
        setUp();

        bowls[4].doTurn();
        bowls[7].doTurn();
        bowls[0].doTurn();

        Assert.assertFalse(player1.yourTurn());
        Assert.assertTrue(player2.yourTurn());
        Assert.assertEquals(0, bowls[0].seedCount());
        Assert.assertEquals(5, bowls[1].seedCount());
        Assert.assertEquals(5, bowls[2].seedCount());
        Assert.assertEquals(5, bowls[3].seedCount());
        Assert.assertEquals(0, bowls[4].seedCount());
        Assert.assertEquals(5, bowls[5].seedCount());
        Assert.assertEquals(8, bowls[6].seedCount());
        Assert.assertEquals(0, bowls[7].seedCount());
        Assert.assertEquals(0, bowls[8].seedCount());
        Assert.assertEquals(5, bowls[9].seedCount());
        Assert.assertEquals(5, bowls[10].seedCount());
        Assert.assertEquals(5, bowls[11].seedCount());
        Assert.assertEquals(5, bowls[12].seedCount());
        Assert.assertEquals(0, bowls[13].seedCount());

        tearDown();
    }*/
}
