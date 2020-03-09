package dao;

import level.model.Book;
import level.model.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BookDao {

    public void saveBook(String bookName, Set<String> publishersNames) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Set<Publisher> publishers = new HashSet<>();

            Iterator<String> publisherIterator = publishersNames.iterator();
            while (publisherIterator.hasNext())
                publishers.add(new Publisher(publisherIterator.next()));

            Book book = new Book(bookName, publishers);
            session.save(book);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }
}
