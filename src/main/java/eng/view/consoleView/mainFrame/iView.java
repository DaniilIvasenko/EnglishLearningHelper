package eng.view.consoleView.mainFrame;

import eng.model.data.Word;

import java.util.List;

public interface iView {
    /**
     * Пользователь создает новое слово для добавления в БД
     * @return
     */
    Word createNewWord();


    /**
     * Отобразить пользователю список слов
     * @param words список для отображения на экране пользователя
     */
    void  showWordsList(List<Word> words);


    int showMainFrame();


}
