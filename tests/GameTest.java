import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SDS on 2017-06-09.
 */
public class GameTest {
    @Test
    public void createGame() throws Exception {
        Game game = new Game();
        assertNotNull(game);
    }
}