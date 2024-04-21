package sample.tdd_study_work.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.tdd_study_work.fizzbuzz.impl.FizzBuzzGameImpl;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzGameImplTest {

    @Test
    @DisplayName("3과 5의 배수가 아니라면 Fail을 반환한다.")
    void Fail() {

        //given
        int number = 94;

        //when
        String failValue = getFizzBuzzGame(number);

        //then
        assertThat(failValue).isEqualTo("Fail");
    }

    @Test
    @DisplayName("3의 배수일 경우 Fizz를 반환한다.")
    void Fizz() {

        //given
        int number = 33;

        //when
        String fizzValue = getFizzBuzzGame(number);

        //then
        assertThat(fizzValue).isEqualTo("Fizz");
    }

    @Test
    @DisplayName("5의 배수일 경우 Buzz를 반환한다.")
    void Buzz() {

        //given
        int number = 25;

        //when
        String buzzValue = getFizzBuzzGame(number);

        //then
        assertThat(buzzValue).isEqualTo("Buzz");
    }

    @Test
    @DisplayName("3과 5의 공배수일 경우 FizzBuzz를 반환한다.")
    void FizzBuzz() {

        //given
        int number = 15;

        //when
        String fizzValue = getFizzBuzzGame(number);

        //then
        assertThat(fizzValue).isEqualTo("FizzBuzz");
    }

    @Test
    @DisplayName("사용자의 입력이 전부 소모되었을 때에도 일치하지 않으면 Loose를 반환한다.")
    void FailedLoose() {

        //given
        int number = 2;

        //when
        String LooseValue = getRandomFizzBuzzGame(number, new String[]{"Fiza", "Buza"});

        //then
        assertThat(LooseValue).isEqualTo("Loose!");

    }

    @Test
    @DisplayName("주어진 숫자와 배열이 특정 조건을 만족할 때 Congratulations!을 반환한다.")
    void testCongratulations() {

        // given
        int number = 2;
        String[] gameResultArray = {"Fizz", "Buzz"};

        // when
        String result = getRandomFizzBuzzGame(number, gameResultArray);

        // then
        assertThat(result).isEqualTo("Congratulations!");
    }

    @Test
    @DisplayName("주어진 숫자와 배열이 특정 조건을 만족하지 않을 때 Loose!를 반환한다.")
    void testLoose() {

        // given
        int number = 0;
        String[] gameResultArray = {"Fizz", "Buzz"};

        // when
        String result = getRandomFizzBuzzGame(number, gameResultArray);

        // then
        assertThat(result).isEqualTo("Loose!");
    }


    private String getFizzBuzzGame(int number) {
        FizzBuzzGameImpl data = getData();
        return data.playGame(number);
    }

    private static FizzBuzzGameImpl getData() {
        FizzBuzzGameImpl fizzBuzzGame = new FizzBuzzGameImpl();
        return fizzBuzzGame;
    }

    private String getRandomFizzBuzzGame(int number, String[] arg) {
        FizzBuzzGameImpl data = getData();
        return data.playGame(number, arg);
    }
}