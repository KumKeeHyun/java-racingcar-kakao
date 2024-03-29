package controller;

import racing.Car;
import racing.RacingGame;
import racing.RealRandomStrategy;
import view.RacingGameView;

import java.util.List;
import java.util.stream.IntStream;

public class RacingGameController {

    public  void startGame() {
        String carNames = RacingGameView.mustGetCarNames();
        int tryNums = RacingGameView.mustGetTryNums();

        RacingGame racingGame = new RacingGame(carNames, new RealRandomStrategy());
        RacingGameView.displayResult();
        IntStream.range(0, tryNums).forEach(i -> {
            racingGame.moveCars();
            RacingGameView.displayCars(racingGame.getCars());
        });
        RacingGameView.displayWinners(racingGame.getWinners());
    }

}
