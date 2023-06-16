package ru.faang.school.hashmap.task_5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexTest {
    private Index index;
    WebPage webPage1;
    WebPage webPage2;

    @BeforeEach
    void setUp(){
        index = new Index();
        webPage1 = new WebPage("hellokitty.com", "Hello Kitty",
                "Hello Kitty was born in the suburbs of London.");
        webPage2 = new WebPage("sephora.com", "Sephora",
                "Ricevi i Tuoi Prodotti a Casa o Ritirali in 2 ore nel Negozio Sephora più Vicino. " +
                        "Acquista i Tuoi prodotti Sephora Online!");
        index.indexPage(webPage1);
    }

    @Test
    void indexPageTest(){
        int expectedSearchMapSize = 9;
        int resultedSearchMapSize = index.getSearchMapSize();

        assertEquals(expectedSearchMapSize, resultedSearchMapSize);
    }

    @Test
    void indexDuplicatePageTest(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> index.indexPage(webPage1));

        int expectedSearchMapSize = 9;
        int resultedSearchMapSize = index.getSearchMapSize();
        String expected = "The website already indexed";

        assertEquals(expectedSearchMapSize, resultedSearchMapSize);
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void searchExistingKeywordTest(){
        WebPageLinkedList list = index.search("was");

        assertTrue(list.contains(webPage1));
    }

    @Test
    void searchNonExistingKeywordTest(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> index.search("keyboard"));
        String expected = "The keyword doesn't exist in the map";

        assertEquals(expected, exception.getMessage());
    }

    @Test
    void deleteExistingUrlTest(){
        index.delete(webPage1.getUrl());

        assertFalse(index.search("was").contains(webPage1));
    }

    @Test
    void deleteNonExistingUrlTest(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> index.delete(webPage2.getUrl()));
        String expected = "The url doesn't exist in the map";

        assertEquals(expected, exception.getMessage());
    }
}
