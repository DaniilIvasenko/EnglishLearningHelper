package eng.view.consoleView.testsView;

import eng.view.consoleView.mainFrame.iView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestView implements iTestView {

    @Override
    public int showMenu() {
        int userChoice = -1;
        do {
            System.out.println("\nМеню тестов:\n-----------------------------");
            System.out.println("Выберите раздел: \n" +
                    "1. Начать новый тест\n" +
                    "2. Завершить работу");
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
