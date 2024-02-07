package eng.view.consoleView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    /**
     * отобразить в консоли сообщение об ошибке
     * @param message - текст сообщения
     */
    protected   void showErrorMessage(String message){
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
