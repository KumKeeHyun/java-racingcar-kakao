package view;

import racing.Car;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RacingGameView {

    public static final String INPUT_CAR_NAMES_MSG = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    public static final String INPUT_TRY_NUMS_MSG = "시도할 회수는 몇회인가요?";
    public static final String RESULT_MSG = "실행 결과";
    public static final String DISPLAY_WINNER_FMT = "%s가 최종 우승했습니다.\n";

    public static String mustGetCarNames() {
        return retryableInput(RacingGameView::getCarNamesInput, RacingGameView::validateCarNames);
    }

    private static <T> T retryableInput(Supplier<T> supplier, Predicate<T> validator) {
        T input = supplier.get();
        while (!validator.test(input)) {
            input = supplier.get();
        }
        return input;
    }

    private static boolean validateCarNames(String carNames) {
        return !carNames.isEmpty();
    }

    private static String getCarNamesInput() {
        return getInput(INPUT_CAR_NAMES_MSG);
    }

    public static int mustGetTryNums() {
        return retryableInput(RacingGameView::getTryNumsInput, RacingGameView::validateTryNums);
    }

    private static boolean validateTryNums(int tryNums) {
        return tryNums > 0;
    }

    private static int getTryNumsInput() {
        String input = getInput(INPUT_TRY_NUMS_MSG);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return -1;
        }
    }

    private static String getInput(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void displayResult() {
        System.out.println();
        System.out.println(RESULT_MSG);
    }

    public static void displayCars(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.display()));
        System.out.println();
    }

    public static void displayWinners(List<Car> cars) {
        String winners = cars.stream().map(Car::getName).collect(Collectors.joining(", "));
        System.out.printf(DISPLAY_WINNER_FMT, winners);
    }
}
