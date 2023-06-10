package ru.faang.school.hashmap.task_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WesterosLibraryTest {

    private WesterosLibrary westerosLibrary;
    private Book gunslingerBook;
    private Book witcherBook;

    @BeforeEach
    void setUp(){
        westerosLibrary = new WesterosLibrary();
        gunslingerBook  = new Book("Gunslinger", new Author("Stephen", "King"), 1990);
        witcherBook = new Book("The Witcher", new Author("Anjey", "Sapkowsky"), 1986);
        westerosLibrary.addBook("Gunslinger", new Author("Stephen", "King"), 1990);
        westerosLibrary.addBook("Maestro e Margherita", new Author("Mikhail", "Bulgakov"), 1940);
    }

    @Test
    void addNewBookTest(){
        String result = westerosLibrary.addBook("The Witcher", new Author("Anjey", "Sapkowsky"), 1986);
        int expectedLibrarySize = westerosLibrary.getLibrarySize();

        assertEquals(String.format(Message.BOOK_ADDED, witcherBook, expectedLibrarySize), result);
    }

    @Test
    void addNewBookExistingTest(){
        LibraryException exception = assertThrows(LibraryException.class,
                () -> westerosLibrary.addBook("Gunslinger", new Author("Stephen", "King"), 1990));

        assertEquals(String.format(Message.BOOK_ALREADY_EXISTS, gunslingerBook), exception.getMessage());
    }

    @Test
    void deleteExistingBookTest() {
        String result = westerosLibrary.deleteBook("Gunslinger", new Author("Stephen", "King"), 1990);
        int expectedLibrarySize = 1;

        assertEquals(String.format(Message.BOOK_DELETED, gunslingerBook, expectedLibrarySize), result);
    }


    @Test
    void deleteNonExistingBookTest(){
        LibraryException exception = assertThrows(LibraryException.class,
                () -> westerosLibrary.deleteBook("The Witcher", new Author("Anjey", "Sapkowsky"), 1986));

        assertEquals(String.format(Message.NO_SUCH_BOOK, witcherBook), exception.getMessage());
    }

    @Test
    void findExistingBookTest(){
        String expectedRow = "row K";

        assertEquals(String.format(Message.BOOK_FOUND, expectedRow),
                westerosLibrary.findBook("Gunslinger", new Author("Stephen", "King"), 1990));
    }

    @Test
    void findNonExistingBookTest(){
        LibraryException exception = assertThrows(LibraryException.class,
                () -> westerosLibrary.findBook("The Witcher", new Author("Anjey", "Sapkowsky"), 1986));

        assertEquals(String.format(Message.NO_SUCH_BOOK, witcherBook), exception.getMessage());

    }
}
