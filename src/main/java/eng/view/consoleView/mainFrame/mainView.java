package eng.view.consoleView.mainFrame;

import eng.model.data.Word;

import java.util.*;

public class mainView  implements iView{
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
            System.out.println("Главное меню:\n-----------------------------------");
            System.out.println("Выберите раздел:");
            System.out.println("1. Добавить слово в словарь.");
            System.out.println("2. Отобразить список всех слов.");
            System.out.println("3. Отобразить список слов для изучения.");
            System.out.print("4. Начать тест по сложным словам.\n: ");
            try {
                Scanner scanner = new Scanner(System.in);
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                showErrorMessage("Введите число");
            }
        }while (userChoice<0);
        return userChoice;
    }


    /**
     * отобразить в консоли сообщение об ошибке
     * @param message - текст сообщения
     */
    public void showErrorMessage(String message){
        System.out.println();
        message = "** Ошибка! "+ message+" **";
        for (int i = 0; i < message.length(); i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println(message);
        for (int i = 0; i < message.length(); i++) {
            System.out.print("*");
        }
        System.out.println();
    }

}
