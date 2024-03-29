package eng.controllers;

import eng.decorator.WordListDecorator;
import eng.exceptions.HardWordPoolOverflowException;
import eng.model.data.HardWord;
import eng.model.data.Word;
import eng.model.data.WordTranslations;
import eng.repository.HardWordRepository;
import eng.repository.WordsRepository;
import eng.services.MailService;
import eng.services.words.HardWordService;
import eng.services.words.WordService;
import eng.view.consoleView.mainFrame.mainView;
import eng.view.consoleView.mainFrame.iView;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MainController  implements iMainController{
    private final WordService wordService ;
    private final HardWordService hardWordService;
    private final TestController testController;
    private final MailService mailService;
    private  final iView view;

    public MainController() {
        this.wordService = new WordService(new WordsRepository());
        this.view = new mainView();
        this.testController = new TestController();
        this.hardWordService = new HardWordService(new HardWordRepository());
        this.mailService = new MailService();
    }

    /**
     * запуск программы
     */
    public void run(){
        System.out.println("******************************\n" +
                "Добро пожаловать!!!\n******************************\n");
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
                        view.showErrorMessage("Переполнение пула сложных слов, пройдите тест чтобы добавлять новые слова");
                    }
                    break;

                // список всех слов
                case 2:
                    List<Word> words = wordService.findAll();
                    view.showWordsList(words);
                    break;

                    // список слов для изучения
                case 3:
                    List<HardWord> hardWords = hardWordService.findAllHardWords();
                    view.showWordsList(hardWordService.convertToWordList(hardWords));
                    break;

                    // передача управления контроллеру тестов
                case 4:
                    List<HardWord> hardWordsAfterTestComplete = testController.start(hardWordService.findAllHardWords());
                    List<HardWord> rememberedWords = hardWordService.balanceHardWordList(hardWordsAfterTestComplete);
                    wordService.updateRememberedWords(rememberedWords);
                    break;

                case 9:
                    List<Word> wordsToLearning = hardWordService.findAllHardWords().stream().map(x->x.getWord()).collect(Collectors.toList());
                    String message = WordListDecorator.wordListToString(wordsToLearning);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    String title = "Последний список слов от: "+ LocalDateTime.now().format(formatter);
                    mailService.sendEmailMessage(title, message);
                    System.exit(0);
                    break;
            }
        }

    }
}
