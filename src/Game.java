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


    public void setSecretNumber(int first, int second, int third) {
       secretNumber[0] = first;
       secretNumber[1] = second;
       secretNumber[2] = third;
    }

    static void main(String[] args) {

    }

    public boolean isSolved(int first, int second, int third) {
        checkStrike(first, second, third);
        return strike == 3;
    }

    public void checkStrike(int first, int second, int third) {
        guessNumber[0] = first;
        guessNumber[1] = second;
        guessNumber[2] = third;
        checkStrikeForIndex(0);
        checkStrikeForIndex(1);
        checkStrikeForIndex(2);
    }

    private void checkStrikeForIndex(int i) {
        if(secretNumber[i] == guessNumber[i]) {
            strike ++;
            checkedGuessNumber[i] = true;
            checkedSecretNumber[i] = true;
        }
    }

    public void checkBall(int first, int second, int third) {
        guessNumber[0] = first;
        guessNumber[1] = second;
        guessNumber[2] = third;


        if(isNotStrike(0)) {
            if (!checkedSecretNumber[1]) {
                if (guessNumber[0] == secretNumber[1]) {
                    increaseBallAndCheck(0, 1);
                }
            } else if (!checkedSecretNumber[2]) {
                if (guessNumber[0] == secretNumber[2]) {
                    increaseBallAndCheck(0, 2);
                }
            }
        }
        if(isNotStrike(1)) {
            if (!checkedSecretNumber[0]) {
                if (guessNumber[1] == secretNumber[0]) {
                    increaseBallAndCheck(0, 1);
                }
            } else if (!checkedSecretNumber[2]) {
                if (guessNumber[1] == secretNumber[2]) {
                    increaseBallAndCheck(1, 2);
                }
            }
        }
        if(isNotStrike(2)) {
            if (!checkedSecretNumber[0]) {
                if (guessNumber[2] == secretNumber[0]) {
                    increaseBallAndCheck(2, 0);
                }
            } else if (!checkedSecretNumber[1]) {
                if (guessNumber[2] == secretNumber[1]) {
                    increaseBallAndCheck(2, 1);
                }
            }
        }
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
