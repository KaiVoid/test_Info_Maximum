package parser;

import model.House;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParserResultTest {

    @Test
    void addOneHouse() {
        ParserResult result = new ParserResult();

        House house = new House("Починки", "Ленина", 87, 2);
        result.addHouse("Починки", "Ленина", 87, 2);

        assertEquals(1,result.getHouses().size());
        assertTrue(result.getHouses().contains(house));
        assertNull(result.getDuplicateHouse().get(house));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(1));
        assertEquals(1,result.getCities().get("Починки").getValueHouse(2));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(3));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(4));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(5));
    }

    @Test
    void addThreeHouse() {
        ParserResult result = new ParserResult();

        House house1 = new House("Починки", "Ленина", 87, 2);
        House house2 = new House("Починки", "Пушкина", 78, 4);
        House house3 = new House("Починки", "Измайлова", 666, 3);

        result.addHouse("Починки", "Ленина", 87, 2);
        result.addHouse("Починки", "Пушкина", 78, 4);
        result.addHouse("Починки", "Измайлова", 666, 3);

        assertEquals(3,result.getHouses().size());
        assertTrue(result.getHouses().contains(house1));
        assertTrue(result.getHouses().contains(house2));
        assertTrue(result.getHouses().contains(house3));
        assertNull(result.getDuplicateHouse().get(house1));

        assertEquals(0,result.getCities().get("Починки").getValueHouse(1));
        assertEquals(1,result.getCities().get("Починки").getValueHouse(2));
        assertEquals(1,result.getCities().get("Починки").getValueHouse(3));
        assertEquals(1,result.getCities().get("Починки").getValueHouse(4));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(5));
    }

    @Test
    void addThreeDuplicateHouse() {
        ParserResult result = new ParserResult();

        House house1 = new House("Починки", "Ленина", 87, 2);
        House house2 = new House("Починки", "Ленина", 87, 2);
        House house3 = new House("Починки", "Ленина", 87, 2);

        result.addHouse("Починки", "Ленина", 87, 2);
        result.addHouse("Починки", "Ленина", 87, 2);
        result.addHouse("Починки", "Ленина", 87, 2);

        assertEquals(1,result.getHouses().size());

        assertTrue(result.getHouses().contains(house1));
        assertTrue(result.getHouses().contains(house2));
        assertTrue(result.getHouses().contains(house3));

        assertEquals(3,result.getDuplicateHouse().get(house1));
        assertEquals(3,result.getDuplicateHouse().get(house2));
        assertEquals(3,result.getDuplicateHouse().get(house3));

        assertEquals(0,result.getCities().get("Починки").getValueHouse(1));
        assertEquals(1,result.getCities().get("Починки").getValueHouse(2));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(3));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(4));
        assertEquals(0,result.getCities().get("Починки").getValueHouse(5));
    }
}