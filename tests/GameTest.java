import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void createGame() throws Exception {
        Game game = new Game();
        assertNotNull(game);
    }
}