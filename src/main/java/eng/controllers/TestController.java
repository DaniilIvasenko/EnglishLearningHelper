package eng.controllers;

import eng.model.data.HardWord;
import eng.services.TestService;
import eng.view.consoleView.testsView.TestView;
import eng.view.consoleView.testsView.iTestView;

import java.util.List;

public class TestController {
    private iTestView testView = new TestView();
    private TestService  testService= new TestService();

    /**
     * передача управления контроллеру тестов
     *
     * @param hardWordList список сложных слов
     * @return измененный список сложных слов после завершения работы с контроллером тестов
     */

    public List<HardWord> start(List<HardWord> hardWordList) {
        boolean flag = true;
        while (flag){
            int userChoice = testView.showMenu();
            switch (userChoice){
                // запуск теста
                case 1:
                    List<HardWord> wordsInTest = testService.generateTest(hardWordList);
                    List<String> userAnswers = testView.startTest(wordsInTest);
                    String testResult = testService.getTestResults(userAnswers);
                    testView.showTestResult(testResult);
                    break;
                // выход из меню тестов
                case 2:
                    flag = false;
                    break;
            }
        }
        return hardWordList;
    }
}
