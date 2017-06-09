import org.junit.Before;
import org.junit.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private int FIRST_SECRET_NUMBER = 1;
    private int SECOND_SECRET_NUMBER = 2;
    private int THIRD_SECRET_NUMBER = 3;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        int[] guessArray = new int[]{FIRST_SECRET_NUMBER, SECOND_SECRET_NUMBER, THIRD_SECRET_NUMBER};
        game.setSecretNumber(guessArray);
    }

    @Test
    public void createGame() throws Exception {
        assertNotNull(game);
    }

    @Test
    public void should_generate_three_random_numbers() throws Exception {
        assertEquals(1, game.secretNumber[0]);
        assertEquals(2, game.secretNumber[1]);
        assertEquals(3, game.secretNumber[2]);
    }

    @Test
    public void should_return_true_if_secretNumber_and_guessNumber_are_equal() throws Exception {
        int first = FIRST_SECRET_NUMBER;
        int second = SECOND_SECRET_NUMBER;
        int third = THIRD_SECRET_NUMBER;
        int[] guessArray = new int[]{FIRST_SECRET_NUMBER, SECOND_SECRET_NUMBER, THIRD_SECRET_NUMBER};
        assertTrue(game.isSolved(guessArray));
    }

    @Test
    public void should_return_false_if_secretNumber_and_guessNumber_are_not_equal() throws Exception {
        int[] guessArray = new int[]{2,3,4};
        assertFalse(game.isSolved(guessArray));
    }

    @Test
    public void should_return_one_strike_if_one_number_is_equal_and_same_position() throws Exception {
        int first = FIRST_SECRET_NUMBER;
        int second = 4;
        int third = 5;
        int[] guessArray = new int[]{FIRST_SECRET_NUMBER,4,5};
        game.checkStrike(guessArray);
        assertEquals(1, game.strike);
    }

    @Test
    public void should_return_one_ball_if_one_number_is_equal_and_different_position() throws Exception {
        int first = 7;
        int second = FIRST_SECRET_NUMBER;
        int third = 5;
        int[] guessArray = new int[]{7,FIRST_SECRET_NUMBER,5};
        game.checkBall(guessArray);
        assertEquals(1, game.ball);
    }

    @Test
    public void should_return_one_strike_and_zero_ball_when_123_and_received_114() throws Exception {
        int first = FIRST_SECRET_NUMBER;
        int second = 1;
        int third = 4;
        int[] guessArray = new int[]{FIRST_SECRET_NUMBER,1,4};
        game.checkStrike(guessArray);
        game.checkBall(guessArray);
        assertEquals(1, game.strike);
        assertEquals(0, game.ball);
    }

    @Test
    public void should_return_three_strikes_when_123_equals_123() throws Exception {
        int first = FIRST_SECRET_NUMBER;
        int second = SECOND_SECRET_NUMBER;
        int third = THIRD_SECRET_NUMBER;
        int[] guessArray = new int[]{FIRST_SECRET_NUMBER, SECOND_SECRET_NUMBER, THIRD_SECRET_NUMBER};
        game.checkStrike(guessArray);
        game.checkBall(guessArray);
        assertEquals(3, game.strike);
        assertEquals(0, game.ball);
    }
}