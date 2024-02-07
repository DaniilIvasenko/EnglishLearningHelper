package eng;

import eng.controllers.MainController;
import eng.repository.WordsRepository;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.run();
    }
}