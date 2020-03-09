import dao.BookDao;
import level.model.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        BookDao bookDao =  new BookDao();

        Set<String> publishers = new HashSet<>();
        publishers.add("Publisher 1");
        publishers.add("Publisher 2");
        bookDao.saveBook("Kill Mockingbird", publishers);
    }
}
