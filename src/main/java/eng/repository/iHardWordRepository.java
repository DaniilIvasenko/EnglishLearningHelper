package eng.repository;

import eng.model.data.HardWord;
import eng.model.data.Word;

import java.util.List;

public interface iHardWordRepository {

    /**
     * добавить слово в таблицу сложных слов
     *
     * @param word сложное слово
     */
    void addHardWord(HardWord word);

    /**
     * получить список сложных слов
     *
     * @return список слов
     */
    List<HardWord> findAllHardWords();

    /**
     * удаление сложного слова по значению
     *
     * @param word слово которое надо удалить
     */
    void deleteHardWord(HardWord word);


    /**
     * обновление сложного слова в БД
     * @param hardWord слово которое должно быть изменено
     */
    void updateHardWord(HardWord hardWord);
}

