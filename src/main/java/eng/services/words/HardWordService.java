package eng.services.words;

import eng.exceptions.HardWordPoolOverflowException;
import eng.model.data.HardWord;
import eng.model.data.Word;
import eng.repository.HardWordRepository;
import eng.repository.iHardWordRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HardWordService {

    private final iHardWordRepository hardWordRepository;
    public static final int hardWordRepeatCounter = 2;
    /**
     * количество слов, которые могут одновременно находиться в списке сложных слов
     */
    private int hardWordPool = 20;

    /**
     * добавления сложного слова в таблицу
     *
     * @param hardWord сложное слово
     */
    public void saveWord(HardWord hardWord) {
        if (wordPollIsFull()) {
            throw new HardWordPoolOverflowException();
        } else {
            hardWordRepository.addHardWord(hardWord);
        }
    }


    /**
     * найти все сложные слова в БД
     *
     * @return - список сложных слов
     */
    public List<HardWord> findAllHardWords() {
        return hardWordRepository.findAllHardWords();
    }

    /**
     * создать сложное слово
     *
     * @param word слово на основании которого, будет создано сложное слово
     * @return сложное слово
     */
    public HardWord hardWordBuilder(Word word) {
        HardWord hardWord = new HardWord();
        hardWord.setWord(word);
        hardWord.setRepeatCounter(hardWordRepeatCounter);
        return hardWord;
    }


    /**
     * проверка заполнения пула сложных слов
     *
     * @return true - если больше нельзя добавлять слова / false - можно добавлять слова
     */
    private boolean wordPollIsFull() {
        return hardWordRepository.findAllHardWords().stream().count() >= hardWordPool;
    }


    /**
     * преобразование списка сложных слов к списку обычных слов
     *
     * @param hardWords сложное слово
     * @return список простых слов
     */
    public List<Word> convertToWordList(List<HardWord> hardWords) {
        return hardWords.stream().map(x -> x.getWord()).collect(Collectors.toList());
    }


    /**
     * балансировка списка сложных слов
     * слова у которых число успешных повторений = 0 удаляются из списка
     * для остальных слов обновляется значение числа повторений
     *
     * @param hardWords список слов пришедший поле теста
     * @return список запомненных слов(то есть слов для которых repeatCounter = 0)
     */
    public List<HardWord> balanceHardWordList(List<HardWord> hardWords) {
        return hardWords.stream().filter(x -> {
            if (x.getRepeatCounter() == 0) {
                hardWordRepository.deleteHardWord(x);
                return true;
            }
            hardWordRepository.updateHardWord(x);
            return false;

        }).collect(Collectors.toList());
    }

}
