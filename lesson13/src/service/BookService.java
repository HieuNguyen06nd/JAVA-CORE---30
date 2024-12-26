package service;

import entities.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookService {
    public Book inputBook(Scanner scanner){
        System.out.println("Nhap ten sach");
        String name = scanner.nextLine();

        System.out.println("nhap tac gia");
        String auth= scanner.nextLine();

        System.out.println("nhap gia thue");
        double price = Double.parseDouble(scanner.nextLine());

        return new Book(name, auth, price);
    }
    public void updateBook(Scanner scanner, ArrayList<Book>books){
        System.out.println("nhap id sach can sua");
        String id = scanner.nextLine();
        Book book= findById(id, books);
        if (book == null){
            System.out.println("sach k ton tai");
        }else {
            System.out.println("Nhap ten sach");
            String name = scanner.nextLine();
            book.setName(name);

            System.out.println("nhap tac gia");
            String auth= scanner.nextLine();
            book.setAuth(auth);

            System.out.println("nhap gia thue");
            double price = Double.parseDouble(scanner.nextLine());
            book.setPrice(price);

        }
    }

    public void daleteBook(Scanner scanner, ArrayList<Book>books){
        System.out.println(" nhap id sach xoas");
        String book_id= scanner.nextLine();
        Book book =findById(book_id,books);
        if (book ==null){
            System.out.println(" khong tim thay sach id"+ book_id);
        }else {
            books.remove(book);
            System.out.println("xoas thanh cong book: "+ book_id);
        }
    }

    public Book findById(String id, ArrayList<Book> books){
        for (Book book: books){
            if (book.getId().equalsIgnoreCase(id)){
                return book;
            }
        }
        return null;
    }
}
