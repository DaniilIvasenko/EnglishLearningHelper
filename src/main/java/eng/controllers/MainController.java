package eng.controllers;

import eng.exceptions.HardWordPoolOverflowException;
import eng.model.data.Word;
import eng.repository.HardWordRepository;
import eng.repository.WordsRepository;
import eng.services.words.HardWordService;
import eng.services.words.WordService;
import eng.view.consoleView.mainFrame.mainView;
import eng.view.consoleView.mainFrame.iView;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class MainController  implements iMainController{
    private final WordService wordService ;
    private final HardWordService hardWordService;
    private final TestController testController;
    private  final iView view;

    public MainController() {
        this.wordService = new WordService(new WordsRepository());
        this.view = new mainView();
        this.testController = new TestController();
        this.hardWordService = new HardWordService(new HardWordRepository());
    }

    /**
     * запуск программы
     */
    public void run(){
        while (true){
            int userChoice = view.showMainFrame();
            switch (userChoice){
                case 1:
                    Word newWord = view.createNewWord();
                    wordService.saveWord(newWord);
                    try {
                        hardWordService.saveWord(hardWordService.hardWordBuilder(newWord));
                    }catch (HardWordPoolOverflowException e){
                        wordService.deleteWord(newWord.getWord());
                        System.out.println("переполнение пула ");
                        // todo придумать как отобразить пользователю ошибку о переполнении пула сложных слов
                    }
                    break;

                case 2:
                    List<Word> words = wordService.findAllNotRemembered();
                    view.showWordsList(words);
                    break;

                case 3:
                    testController.start(hardWordService.findAllHardWords());
            }
        }

    }
}
