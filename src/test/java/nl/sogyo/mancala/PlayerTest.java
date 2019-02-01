package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testCreateNewPlayers(){
        Player player = new Player();
        Player opponent = player.getOpponent();
        Assert.assertTrue(player.yourTurn());
        Assert.assertFalse(opponent.yourTurn());
    }

    @Test
    public void testNextPlayerTurn(){
        Player player = new Player();
        Player opponent = player.getOpponent();
        player.flipTurns();
        Assert.assertFalse(player.yourTurn());
        Assert.assertTrue(opponent.yourTurn());
    }

}
