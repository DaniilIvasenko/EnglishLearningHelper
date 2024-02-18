package eng.services;

import eng.model.data.HardWord;
import eng.services.words.HardWordService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestService {
    /**
     * слова в последнем тесте
     */
    List<HardWord> lastTest;

    /**
     * создает тест путем перемешивания слов в полученном списке
     * @param words слова, которые должны войти в тест
     * @return список перемешанных слов
     */
    public List<HardWord> generateTest(List<HardWord> words){
         Collections.shuffle(words);
         lastTest =  words;
         return words;
    }

    /**
     * проверить тест и сформировать строку с результатами
     * @param answers ответы пользователя
     * @return  текстовый отчет о результатах теста
     */
    public String getTestResults(List<String> answers){
        List<HardWord> failList = checkAnswers(answers);
        StringBuilder resultStringBuilder =
                new StringBuilder("Допущено " +failList.size() +" ошибок из "+answers.size()+" слов!!!\n");
        for (HardWord wordWithFail : failList) {
            int wordNumberInTest = lastTest.indexOf(wordWithFail);
            resultStringBuilder.append("слово №"+wordNumberInTest+" ("+wordWithFail.getWord().getWordMainTranslation()+"). " +
                    " Правильный ответ: '"+wordWithFail.getWord().getWord()+"' " +
                    "ваш ответ '"+ answers.get(wordNumberInTest)+"'\n");
        }

        return resultStringBuilder.toString();
    }


    /**
     * проверка ответов -> уменьшение количество повторений(необходимых чтобы число считалось запомненным) в случае правильного ответа
     * иначе приведение этого числа к максимальному значению
     * @param answers ответы пользователя
     * @return слова в которых пользователь допустил ошибку
     */
    private List<HardWord> checkAnswers(List<String> answers){
        List<HardWord> failList = new ArrayList<>();
        for (int i = 0; i < lastTest.size() ; i++) {
            HardWord currentWord = lastTest.get(i);
            if (!currentWord.getWord().getWord().toLowerCase().equals(answers.get(i))){
                currentWord.setRepeatCounter(HardWordService.hardWordRepeatCounter);
                failList.add(currentWord);
            }else {
                currentWord.setRepeatCounter(currentWord.getRepeatCounter()-1);
            }
        }
        return failList;
    }
}
