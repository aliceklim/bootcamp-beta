package ru.faang.school.hashmap.task_2;

import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class WesterosLibrary {

    private final Map<Book, String> library = new HashMap<>();

    public String addBook(String title, Author author, int year){
        Book newBook = new Book(title, author, year);
        if (library.containsKey(newBook)){
            throw new LibraryException(String.format(Message.BOOK_ALREADY_EXISTS, newBook));
        }
        library.put(newBook, assignRow(author.getLastName()));
        return String.format(Message.BOOK_ADDED, newBook, library.size());
    }

    private String assignRow(String surname){
        return "row " + surname.charAt(0);
    }

    public String deleteBook(String title, Author author, int year){
        Book book = new Book(title, author, year);
        if (!library.containsKey(book)){
            throw new LibraryException(String.format(Message.NO_SUCH_BOOK, book));
        }
        library.remove(book);
        return String.format(Message.BOOK_DELETED, book, library.size());
    }

    public String findBook(String title, Author author, int year){
        Book book = new Book(title, author, year);
        if (!library.containsKey(book)){
            throw new LibraryException(String.format(Message.NO_SUCH_BOOK, book));
        }
        return String.format(Message.BOOK_FOUND, library.get(book));
    }

    public void getAllBooksInfo(){
        if (library.isEmpty()){
            throw new LibraryException(Message.LIBRARY_EMPTY);
        }
        for (Map.Entry<Book,String> entry : library.entrySet()){
            System.out.println(String.format(Message.BOOK_INFO_AND_PLACEMENT, entry.getKey(), entry.getValue()));
        }
    }

    public int getLibrarySize(){
        return library.size();
    }
}
