import java.util.Scanner;

/**
 * Created by SDS on 2017-06-09.
 */
public class Game {
    public int firstSecretNumber;
    public int secondSecretNumber;
    public int thirdSecretNumber;
    public int[] secretNumber = new int[3];
    public int strike = 0;
    public int ball = 0;
    private boolean[] checkedGuessNumber = new boolean[3];
    private boolean[] checkedSecretNumber = new boolean[3];
    private int[] guessNumber = new int[3];;


    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.println("정답을 입력하세요");
        int first = sc.nextInt();
        int second = sc.nextInt();
        int third = sc.nextInt();
        game.setSecretNumber(first, second, third);

        System.out.println("맞춰보세요");
        int firstGuess = sc.nextInt();
        int secondGuess = sc.nextInt();
        int thirdGuess = sc.nextInt();
        String guess = "" + firstGuess + secondGuess + thirdGuess;

        System.out.println("guess : " + guess
                + " -> solved : " + game.isSolved(firstGuess, secondGuess, thirdGuess) + ", "
                + game.strike + " strikes "
                + game.checkBall(firstGuess, secondGuess, thirdGuess) + " balls");
    }

    public void setSecretNumber(int first, int second, int third) {
       secretNumber[0] = first;
       secretNumber[1] = second;
       secretNumber[2] = third;
    }

    public boolean isSolved(int first, int second, int third) {
        checkStrike(first, second, third);
        return strike == 3;
    }

    public int checkStrike(int first, int second, int third) {
        guessNumber[0] = first;
        guessNumber[1] = second;
        guessNumber[2] = third;
        checkStrikeForIndex(0);
        checkStrikeForIndex(1);
        checkStrikeForIndex(2);
        return strike;
    }

    private void checkStrikeForIndex(int i) {
        if(secretNumber[i] == guessNumber[i]) {
            strike ++;
            checkedGuessNumber[i] = true;
            checkedSecretNumber[i] = true;
        }
    }

    public int checkBall(int first, int second, int third) {
        guessNumber[0] = first;
        guessNumber[1] = second;
        guessNumber[2] = third;

        for(int guessNumberIndex = 0; guessNumberIndex < 3; guessNumberIndex++){
            for (int secretNumberIndex = 0; secretNumberIndex < 3; secretNumberIndex++) {
                if (bothIndexesAreNotChecked(guessNumberIndex, secretNumberIndex)) {

                    int currentGuessNumber = guessNumber[guessNumberIndex];
                    int currentSecretNumber= secretNumber[secretNumberIndex];

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
