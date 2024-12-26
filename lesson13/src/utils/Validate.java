package utils;

import entities.Book;
import entities.User;
import service.BookService;

import java.util.ArrayList;

public class Validate {
    BookService bookService = new BookService();
    public boolean existBook(String book_id, ArrayList<Book>books){
        for (Book book: books){
            if (book.getId().equalsIgnoreCase(book_id)){
                return true;
            }
        }
        return false;
    }
    public boolean existUser(String user_id, ArrayList<User>users){
        for (User user: users){
            if (user.getId().equalsIgnoreCase(user_id)){
                return true;
            }
        }
        return false;
    }
}
