package eng.services.words;

import eng.model.data.HardWord;
import eng.model.data.Word;
import eng.repository.iHardWordRepository;
import eng.repository.iWordsRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WordService {
    private final iWordsRepository wordsRepository;

    /**
     * сохранение слова в БД
     *
     * @param word - слово для добавления в БД
     * @return
     */
    public Word saveWord(Word word) {
        deleteWord(word.getWord());
        wordsRepository.saveWord(word);
        return word;
    }


    /**
     * поиск слова в БД
     *
     * @param name - слово для поиска
     * @return - результат поиска
     */
    public Word findWordByName(String name) {
        return wordsRepository.findWordByName(name);
    }

    /**
     * поиск в БД слов с полем isRemembered = false
     * @return список найденных слов
     */
    public List<Word> findAll(){
        return wordsRepository.findAll();
    }


    /**
     * удаление слова по значению
     * @param word - текстовое значение слова
     */
    public  void  deleteWord(String word){
        Word findThisWordInDB = wordsRepository.findWordByName(word);
        if (findThisWordInDB!= null){
            wordsRepository.deleteWord(findThisWordInDB);
        }
    }


    /**
     * устанавливает флаг isRemembered для всех слов из списка в БД -> true
     * @param rememberedHardWords список у которых надо изменить флаг isRemembered
     */
    public void updateRememberedWords(List<HardWord> rememberedHardWords){
        rememberedHardWords.stream().forEach(x-> {
            Word wordToUpdate = x.getWord();
            wordToUpdate.setHardWord(null);
            wordToUpdate.setRemembered(true);
            wordsRepository.updateWord(wordToUpdate);
        });
     }

}
