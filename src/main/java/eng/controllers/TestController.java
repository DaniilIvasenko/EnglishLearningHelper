package eng.controllers;

import eng.model.data.HardWord;
import eng.view.consoleView.testsView.TestView;
import eng.view.consoleView.testsView.iTestView;

import java.util.List;

public class TestController {
    private iTestView testView = new TestView();

    /**
     * передача управления контроллеру тестов
     *
     * @param hardWordList список сложных слов
     * @return измененный список сложных слов после завершения работы с контроллером тестов
     */

    public List<HardWord> start(List<HardWord> hardWordList) {
        return null;
    }
}
