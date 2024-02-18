package eng.repository;

import eng.model.data.Word;
import eng.model.data.WordTranslations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.util.List;

public class WordsRepository implements iWordsRepository{
    private SessionFactory sessionFactory;

    public WordsRepository() {
        this.sessionFactory = new Configuration()
                .configure("Hibernate.cfg.xml")
                .addAnnotatedClass(Word.class)
                .addAnnotatedClass(WordTranslations.class)
                .buildSessionFactory();
    }

    /**
     * сохранение слова в БД
     *
     * @param word - слово для добавления в БД
     * @return
     */
    public Word saveWord(Word word) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(word);
            transaction.commit();
        }
        return word;
    }


    /**
     * поиск слова в БД
     *
     * @param name - слово для поиска
     * @return - результат поиска
     */
    public Word findWordByName(String name) {
        Word word ;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                word = session.createQuery("from Word where word = :word", Word.class)
                        .setParameter("word", name)
                        .getSingleResult();
            }catch (NoResultException e){
                word = null;
            }
            transaction.commit();
        }
        return word;
    }


    /**
     * найти все слова у которых поле isRemembered = false
     * @return список найденных слов
     */
    @Override
    public List<Word> findAll(){
        List<Word> notRememberedWords;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            notRememberedWords =  session.createQuery("from Word ", Word.class).list();
            transaction.commit();
        }
        return notRememberedWords;
    }


    /**
     * удалить слово из БД
     * @param word слово для удаления
     */
    @Override
    public void deleteWord(Word word) {
        try (Session session = sessionFactory.getCurrentSession()) {
        Transaction transaction = session.beginTransaction();
        session.delete(word);
        transaction.commit();
        }
    }

    /**
     * изменение слова в БД
     * @param word - слово для изменения в бд
     */
    @Override
    public void updateWord(Word word) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(word);
            transaction.commit();
        }
    }
}
