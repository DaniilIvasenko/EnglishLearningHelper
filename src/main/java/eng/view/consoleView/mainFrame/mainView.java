package eng.view.consoleView.mainFrame;

import eng.model.data.Word;
import eng.view.consoleView.ConsoleView;

import java.util.*;

public class mainView extends ConsoleView implements iView {
    @Override
    public Word createNewWord() {
        Scanner scanner = new Scanner(System.in);

        Word newWord = new Word();
        System.out.println("Создание нового слова:\n--------------------------------");
        boolean flag = true;
        while (flag) {
        System.out.print("Введите слово:\t");
        String word = scanner.nextLine().trim();
        newWord.setWord(word);

            if (word.length()!=0){
                flag= false;
            }else {
                showErrorMessage("Поле 'слово' не может быть пустым");
            }
        }

        flag = true;
        while (flag) {
            System.out.print("Введите перевод:\t");
            String wordTranslation= scanner.nextLine().trim();
            newWord.setWordTranslation(wordTranslation);

            if (wordTranslation.length()!=0){
                flag= false;
            }else {
                showErrorMessage("Поле 'перевод' не может быть пустым");
            }
        }

        System.out.print("Введите описание слова:\t");
        String wordDetail = scanner.nextLine().trim();
        newWord.setWordDetail(wordDetail);

        newWord.setDateOfCreation(new Date());
        return newWord;
    }


    @Override
    public void showWordsList(List<Word> words) {
        System.out.println("\nСписок слов:\n--------------------------------------");
        int counter = 1;
        for (Word word : words) {
            System.out.printf("%d. %s - %s (%s)\n", counter++, word.getWord(), word.getWordTranslation(), word.getWordDetail());
        }
    }



    /**
     * отобразить главное меню приложения
     */
    @Override
    public int showMainFrame() {

        int userChoice = -1;
        do {
            System.out.println("\nДобро пожаловать!!!\n------------------------------------------");
            System.out.println("Выберите раздел:");
            System.out.println("1. Добавить слово в словарь.");
            System.out.println("2. Отобразить список всех слов.");
            System.out.print("3. Начать тест по сложным словам.\n: ");
            try {
                Scanner scanner = new Scanner(System.in);
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                showErrorMessage("Введите число");
            }
        }while (userChoice<0);
        return userChoice;
    }



}
