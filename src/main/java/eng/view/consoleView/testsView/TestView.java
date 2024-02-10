package eng.view.consoleView.testsView;

import eng.model.data.HardWord;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TestView implements iTestView {

    @Override
    public int showMenu() {
        int userChoice = -1;
        do {
            System.out.println("\nМеню тестов:\n-----------------------------");
            System.out.println("Выберите раздел: \n" +
                    "1. Начать новый тест\n" +
                    "2. Назад в главное меню");
            try {
                Scanner scanner = new Scanner(System.in);
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                showErrorMessage("Введите число");
            }
        }while (userChoice<0);

        return userChoice;
    }


    @Override
    public List<String> startTest(List<HardWord> wordsInTest) {
        List<String> userAnswers = new ArrayList<>();
        System.out.println("Тест начался\n======================");
        System.out.println("Введите перевод слова, в случае если не знаете оставьте поле пустым:");
        int counter = 1;
        for (HardWord currentWord : wordsInTest) {
            StringBuilder stringBuilder = new StringBuilder((counter++) + ". ");
            stringBuilder.append(currentWord.getWord().getWordMainTranslation());
            String translations = currentWord.getWord().getAdditionalTranslations().stream()
                    .map(x->x.getTranslation()).collect(Collectors.joining(", "));
            if (translations.length()!=0){
                stringBuilder.append(" ("+translations+")");
            }
            stringBuilder.append(" - ");


            System.out.print(stringBuilder);

            Scanner scanner= new Scanner(System.in);
            String userInput = scanner.nextLine().trim().toLowerCase();

            if(currentWord.getWord().getWord().toLowerCase().equals(userInput)){
                System.out.println("{+}");
            }else {
                System.out.println("{Ошибка! правильный ответ: '" + currentWord.getWord().getWord()+"'}");
            }
            userAnswers.add(userInput);
        }
        return userAnswers;
    }


    /**
     * отобразить в консоли сообщение об ошибке
     * @param message - текст сообщения
     */
    public void showErrorMessage(String message){
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


    @Override
    public void showTestResult(String result) {
        System.out.println("----------------------------------------------------");
        System.out.println("Тест завершен!\n==============");
        System.out.println(result);
    }
}
