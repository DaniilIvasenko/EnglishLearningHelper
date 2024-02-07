package eng.view.consoleView.testsView;

import eng.view.consoleView.ConsoleView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestView extends ConsoleView implements iTestView{

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
}
