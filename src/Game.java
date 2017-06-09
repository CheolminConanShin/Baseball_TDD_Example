import java.util.Scanner;

/**
 * Created by SDS on 2017-06-09.
 */
public class Game {
    public static final int NUMBER_COUNT = 3;
    public int[] secretNumber = new int[3];
    public int strike = 0;
    public int ball = 0;
    private boolean[] checkedGuessNumber = new boolean[3];
    private boolean[] checkedSecretNumber = new boolean[3];
    private int[] guessNumber = new int[3];
    ;

    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        System.out.println("정답을 입력하세요");
        String number = sc.next();
        int[] secretNumber = new int[3];
        for (int i = 0; i < number.length(); i++) {
            secretNumber[i] = Character.getNumericValue(number.charAt(i));
        }
        game.setSecretNumber(secretNumber);

        System.out.println("맞춰보세요");
        boolean isSolved;
        do {
            String guess = sc.next();
            int[] guessNumber = new int[3];
            for (int i = 0; i < guess.length(); i++) {
                guessNumber[i] = Character.getNumericValue(guess.charAt(i));
            }

            isSolved = game.isSolved(guessNumber);
            game.checkBall(guessNumber);

            System.out.println("guess : " + guess
                    + " -> solved : " + isSolved + ", "
                    + game.strike + " strikes "
                    + game.ball + " balls");
        } while (!isSolved);
    }

    public void setSecretNumber(int[] number) {
        secretNumber[0] = number[0];
        secretNumber[1] = number[1];
        secretNumber[2] = number[2];
    }

    public boolean isSolved(int[] guessNumberArray) {
        return checkStrike(guessNumberArray) == NUMBER_COUNT;
    }

    public int checkStrike(int[] guessNumberArray) {
        strike = 0;
        guessNumber[0] = guessNumberArray[0];
        guessNumber[1] = guessNumberArray[1];
        guessNumber[2] = guessNumberArray[2];

        for (int i = 0; i < NUMBER_COUNT; i++) {
            checkStrikeForIndex(i);
        }
        return strike;
    }

    private void checkStrikeForIndex(int i) {
        if (secretNumber[i] == guessNumber[i]) {
            strike++;
            checkedGuessNumber[i] = true;
            checkedSecretNumber[i] = true;
        }
    }

    public int checkBall(int[] guessNumber) {
        ball = 0;
        for (int guessNumberIndex = 0; guessNumberIndex < NUMBER_COUNT; guessNumberIndex++) {
            for (int secretNumberIndex = 0; secretNumberIndex < NUMBER_COUNT; secretNumberIndex++) {
                if (bothIndexesAreNotChecked(guessNumberIndex, secretNumberIndex)) {

                    int currentGuessNumber = guessNumber[guessNumberIndex];
                    int currentSecretNumber = secretNumber[secretNumberIndex];

                    if (currentGuessNumber == currentSecretNumber) {
                        increaseBallAndCheck(guessNumberIndex, secretNumberIndex);
                    }
                }
            }
        }
        return ball;
    }

    private boolean bothIndexesAreNotChecked(int guessIndex, int secretIndex) {
        return !checkedGuessNumber[guessIndex] && !checkedSecretNumber[secretIndex];
    }

    private void increaseBallAndCheck(int guessIndex, int secretIndex) {
        ball++;
        checkedSecretNumber[secretIndex] = true;
        checkedGuessNumber[guessIndex] = true;
    }

    private boolean isNotStrike(int index) {
        return !checkedGuessNumber[index];
    }
}
