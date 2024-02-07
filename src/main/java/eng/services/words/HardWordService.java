package eng.services.words;

import eng.exceptions.HardWordPoolOverflowException;
import eng.model.data.HardWord;
import eng.model.data.Word;
import eng.repository.HardWordRepository;
import eng.repository.iHardWordRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HardWordService {

    private final iHardWordRepository hardWordRepository;
    private  int hardWordRepeatCounter = 2;
    /**
     * количество слов, которые могут одновременно находиться в списке сложных слов
     */
    private int hardWordPool = 20;

    /**
     * добавления сложного слова в таблицу
     * @param hardWord сложное слово
     */
    public void saveWord(HardWord hardWord) {
        if (wordPollIsFull()){
            throw  new HardWordPoolOverflowException();
        }else {
            hardWordRepository.addHardWord(hardWord);
        }
    }


    /**
     * найти все сложные слова в БД
     * @return - список сложных слов
     */
    public List<HardWord> findAllHardWords(){
        return hardWordRepository.findAllHardWords();
    }

    /**
     * создать сложное слово
     * @param word слово на основании которого, будет создано сложное слово
     * @return сложное слово
     */
    public HardWord hardWordBuilder(Word word){
        HardWord hardWord = new HardWord();
        hardWord.setWord(word);
        hardWord.setRepeatCounter(hardWordRepeatCounter);
        return hardWord;
    }


    /**
     * проверка заполнения пула сложных слов
     * @return true - если больше нельзя добавлять слова / false - можно добавлять слова
     */
    private boolean wordPollIsFull(){
        return hardWordRepository.findAllHardWords().stream().count()>=hardWordPool;
    }

}
