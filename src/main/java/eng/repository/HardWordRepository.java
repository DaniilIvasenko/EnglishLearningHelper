package eng.repository;

import eng.model.data.HardWord;
import eng.model.data.Word;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.util.List;

public class HardWordRepository implements iHardWordRepository {
    SessionFactory sessionFactory;

    public HardWordRepository() {
        this.sessionFactory = new Configuration()
                .configure("Hibernate.cfg.xml")
                .addAnnotatedClass(HardWord.class)
                .buildSessionFactory();
    }

    @Override
    public void addHardWord(HardWord word) {
        try (Session session = sessionFactory.getCurrentSession()) {
        Transaction transaction = session.beginTransaction();
        session.save(word);
        transaction.commit();
        }

    }


    @Override
    public List<HardWord> findAllHardWords() {
        List<HardWord> hardWords;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            hardWords = session.createQuery("from HardWord").list();
            transaction.commit();
        }
        return hardWords;
    }

    /**
     * удаление сложного слова по значению
     * @param word тестовое значение слова
     */
    public  void  deleteHardWord(HardWord word){
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(word);
            transaction.commit();
        }
    }


    /**
     * обновление сложного слова в БД
     * @param hardWord слово которое должно быть изменено
     */
    @Override
    public void updateHardWord(HardWord hardWord) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(hardWord);
            transaction.commit();
        }
    }
}
