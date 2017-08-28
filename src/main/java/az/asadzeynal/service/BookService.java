package az.asadzeynal.service;

import az.asadzeynal.ejb.BookFacade;
import az.asadzeynal.ejb.StudentFacade;
import az.asadzeynal.entity.Book;
import az.asadzeynal.entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author asadzeynal
 */
@WebService(serviceName = "BookService")
public class BookService {
    @EJB
    private BookFacade bf;
    
    @EJB
    private StudentFacade sf;
    
    /**
     *
     * @param title
     * @param publisher
     * @param author
     * @param genre
     * @param borrowerID
     * @return
     */
    @WebMethod(operationName = "createBook")
    public String createBook(String title, String publisher, String author, String genre, long borrowerID) {
        Book b = new Book(title, publisher, author, genre);
        Student s = sf.find(borrowerID);
        if(s != null)
            b.setBorrower(s);
        bf.create(b);
        return "Book " + title + " by " + author +  " created";
    }
    
    /**
     *
     * @param title
     * @return
     */
    @WebMethod(operationName = "findBookByTitle")
    public List<Book> findBookByTitle(String title) {
        return bf.findBook(title, "title");
    }

    /**
     *
     * @param publisher
     * @return
     */
    @WebMethod(operationName = "findBookByPublisher")
    public List<Book> findBookByPublisher(String publisher) {
        return bf.findBook(publisher, "publisher");
    }

    /**
     *
     * @param author
     * @return
     */
    @WebMethod(operationName = "findBookByAuthor")
    public List<Book> findBookByAuthor(String author) {
        return bf.findBook(author, "author");
    }

    /**
     *
     * @param genre
     * @return
     */
    @WebMethod(operationName = "findBookByGenre")
    public List<Book> findBookByGenre(String genre) {
        return bf.findBook(genre, "genre");
    }

}
