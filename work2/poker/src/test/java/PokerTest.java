import org.junit.Test;

import static org.junit.Assert.*;

public class PokerTest {
    @Test
    public void poker() throws Exception {
        assertEquals("White wins",Poker.poker("2H 3D 5S 9C KD","2C 3H 4S 8C AH"));
        assertEquals("Black wins",Poker.poker("2H 4S 4C 2D 4H","2S 8S AS QS 3S"));
        assertEquals("Tie",Poker.poker("2H 3D 5S 9C KD","2D 3H 5C 9S KH"));
    }

}