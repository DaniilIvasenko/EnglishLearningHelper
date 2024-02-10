package eng.view.consoleView.testsView;

import eng.model.data.HardWord;

import java.util.List;

/**
 * пользовательский интерфейс для работы с тестами по сложным словам
 */
public interface iTestView {
    /**
     * отобразить меню тестов
     * @return выбор раздела меню от пользователя
     */
    int showMenu();


    /**
     * начать тест пользователь получает перевод слова и должен ввести слово на английском
     * @param wordsInTest список слов в тесте
     * @return ответы пользователя
     */
    List<String> startTest(List<HardWord> wordsInTest);


    /**
     * отобразить пользователю результат теста
     * @param result текстовое описание результата
     */
    void  showTestResult(String result);
}
