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

public class PublisherDao {

    public void savePublisher(List<String> bookNames, String publisherName) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Set<Book> books = new HashSet<>();

            Iterator<String> bookNamesIterator = bookNames.iterator();
            while (bookNamesIterator.hasNext())
                books.add(new Book(bookNamesIterator.next()));

            Publisher publisher = new Publisher("Super publisher");
            publisher.setBooks(books);

            session.save(publisher);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
