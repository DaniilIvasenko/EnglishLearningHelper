package eng.decorator;

import eng.model.data.Word;
import eng.model.data.WordTranslations;

import java.util.List;

/**
 * декоратор для преобразования списка слов
 */
public class WordListDecorator {
    /**
     * преобразует список слов в String, где каждое следующее слово начинается с новой строки
     * строки  пронумерованы
     * @param words  список слов
     * @return строка после преобразования
     */
    public static String wordListToString(List<Word> words){
        StringBuilder result =new StringBuilder();
        int counter=1;
        for (Word word : words) {
            result.append(String.format("%d. %s - %s",
                    counter++,
                    word.getWord(),
                    word.getWordMainTranslation()));

            String wordTranslations = wordTranslationListToString(word.getAdditionalTranslations());
            if (wordTranslations.length()!=0){
                result.append(" "+wordTranslations);
            }

            String wordDetails =  word.getWordDetail();
            if (wordDetails.length()!=0){
                result.append("\t'"+wordDetails+"'");
            }

            result.append(".\n");
        }
        return  result.toString();
    }

    /**
     * преобразует список WordTranslations к простому перечислению через запятую в строку
     *
     * @param translations список переводов слова
     * @return результирующая строка после преобразования.
     * Если translations - пуст -> вернется пустая строка
     */
    private static String wordTranslationListToString(List<WordTranslations> translations){
        if (translations.size()==0){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("(");
        for (WordTranslations translation : translations) {
            stringBuilder.append(translation.getTranslation()+", ");
        }
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        stringBuilder.append(")");
        return  stringBuilder.toString();
    }
}
