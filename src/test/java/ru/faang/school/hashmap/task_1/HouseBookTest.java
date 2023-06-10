package ru.faang.school.hashmap.task_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HouseBookTest {

    private HouseBook houseBook;

    @BeforeEach
    void setUp(){
        houseBook = new HouseBook();
        houseBook.addNewHouse("Lannister", "A Lannister always pays his debts",
                "a golden lion on a field of crimson");
        houseBook.addNewHouse("Stark", "Winter Is Coming",
                "a grey direwolf on a white background");
        houseBook.addNewHouse("Baratheon", "Ours Is The Fury",
                "a black stag on a gold background");
    }

    @Test
    void addNewHouseTest(){
        String result = houseBook.addNewHouse(
                "Targaryen", "Fire And Blood", "a three-headed dragon breathing flames");
        String houseName = "Targaryen";
        int expectedHouseBookSize = 4;

        assertEquals(expectedHouseBookSize, houseBook.getHouseBookSize());
        assertEquals("A house " + houseName + " added to the book. Houses in the book: "
                + expectedHouseBookSize + ".", result);
    }

    @Test
    void addNewHouseExistingInBookTest(){
        String houseName = "Stark";

        HouseBookException exception = assertThrows(HouseBookException.class,
                () -> houseBook.addNewHouse("Stark", "Winter is coming",
                "a grey direwolf on a white background"));

        assertEquals("The house " + houseName + " already exists in the book.", exception.getMessage());
    }

    @Test
    void getHouseSigilTest(){
        String result = houseBook.getHouseSigil("Stark");

        assertEquals("a grey direwolf on a white background", result);
    }

    @Test
    void getNotExistingHouseSigilTest(){
        String houseName = "Martell";
        HouseBookException exception = assertThrows(HouseBookException.class,
                () -> houseBook.getHouseSigil(houseName));
        assertEquals("The house " + houseName + " doesn't exist in the book.", exception.getMessage());
    }

    @Test
    void deleteHouseTest(){
        String houseName = "Stark";
        String result = houseBook.deleteHouse(houseName);
        int expectedHouseBookSize = 2;

        assertEquals(expectedHouseBookSize, houseBook.getHouseBookSize());
        assertEquals("A house " + houseName + " deleted from the book. Houses in the book: "
                + expectedHouseBookSize + ".", result);
    }

    @Test
    void deleteNotExistingHouse(){
        String houseName = "Martell";

        HouseBookException exception = assertThrows(
                HouseBookException.class, () -> houseBook.deleteHouse(houseName));
        assertEquals("The house " + houseName + " doesn't exist in the book.", exception.getMessage());
    }
}
