package sample.tdd_study_work.fizzbuzz.impl;

import sample.tdd_study_work.fizzbuzz.iterable.FizzBuzzGame;
import java.util.Random;

public class FizzBuzzGameImpl implements FizzBuzzGame {

    @Override
    public String playGame(int number) {

        // 음수도 고려해야 한다. 바보~ ㅋㅋ;;
        if(number == 0) {
            return "Fail";
        }

        if(number % 15 == 0) {
            return "FizzBuzz";
        }

        if(number % 3 == 0) {
            return "Fizz";
        }

        if(number % 5 == 0) {
            return "Buzz";
        }

        return "Fail";
    }

//    @Override
//    public boolean[] isInvalidUser(boolean isValid, int count) {
//        return count == 3 ? new boolean[]{true} : new boolean[]{false};
//    }

    public String playGame(int number, String[] GameResultArray) {

        Random random = new Random();

        int randomNumber = getRandomNumber(number, random);
        int randomNumber2 = getRandomNumber(number, random);

        if (invalidSuccessNumber(GameResultArray, randomNumber) || invalidSuccessNumber(GameResultArray, randomNumber2)) {
            return "Congratulations!";
        }

        return "Loose!";
    }

    private boolean invalidSuccessNumber(String[] GameResultArray, int number) {
        return playGame(number) == GameResultArray[0] || playGame(number) == GameResultArray[1];
    }

    private int getRandomNumber(int number, Random random) {
        return number * random.nextInt(9) + 1;
    }
}
