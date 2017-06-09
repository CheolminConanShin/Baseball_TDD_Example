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
            game.setGuessNumber(guessNumber);


            isSolved = game.isSolved();
            game.checkBall();

            System.out.println("guess : " + guess
                    + " -> solved : " + isSolved + ", "
                    + game.strike + " strikes "
                    + game.ball + " balls");
        } while (!isSolved);
    }

    public void setGuessNumber(int[] guessNumberArray) {
        guessNumber[0] = guessNumberArray[0];
        guessNumber[1] = guessNumberArray[1];
        guessNumber[2] = guessNumberArray[2];
    }

    public void setSecretNumber(int[] number) {
        secretNumber[0] = number[0];
        secretNumber[1] = number[1];
        secretNumber[2] = number[2];
    }

    public boolean isSolved() {
        return checkStrike() == NUMBER_COUNT;
    }

    public int checkStrike() {
        int strike = 0;
        for (int i = 0; i < NUMBER_COUNT; i++) {
            strike += checkStrikeForIndex(i);
        }
        return strike;
    }

    private int checkStrikeForIndex(int i) {
        if (secretNumber[i] == guessNumber[i]) {
            checkedGuessNumber[i] = true;
            checkedSecretNumber[i] = true;
            return 1;
        }
        return 0;
    }

    public int checkBall() {
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
