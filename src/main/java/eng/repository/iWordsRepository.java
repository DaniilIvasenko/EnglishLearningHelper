package eng.repository;

import eng.model.data.Word;

import java.util.List;

public interface iWordsRepository {

    /**
     * сохранение слова в БД
     *
     * @param word - слово для добавления в БД
     * @return
     */
    Word saveWord(Word word);


    /**
     * поиск слова в БД
     *
     * @param name - слово для поиска
     * @return - результат поиска
     */
    Word findWordByName(String name);


    /**
     * найти все слова у которых поле isRemembered = false
     * @return список найденных слов
     */
    List<Word> findAllNotRemembered();


    /**
     * удаление слова
     * @param word
     */
    void deleteWord(Word word);
}
