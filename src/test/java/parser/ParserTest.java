package parser;

import model.House;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseFileCSV() {
        ParserResult parserResult = Parser.parseFile("src/test/resources/test.csv");

        assertEquals(7,parserResult.getHouses().size());
        assertTrue(parserResult.getHouses().contains(new House("Владивосток", "Дальняя улица", 196, 3)));
        assertFalse(parserResult.getHouses().contains(new House("Владивосток", "Дальняя улица", 196, 2)));
        assertFalse(parserResult.getHouses().contains(new House("Влад", "Дальняя улица", 196, 3)));

        assertEquals(1,parserResult.getDuplicateHouse().size());
        assertEquals(2,parserResult.getDuplicateHouse().get(new House("Владивосток", "Дальняя улица", 196, 3)));

        assertEquals(6,parserResult.getCities().size());
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(1));
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(2));
        assertEquals(1,parserResult.getCities().get("Владивосток").getValueHouse(3));
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(4));
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(5));
        assertEquals(1,parserResult.getCities().get("Барнаул").getValueHouse(2));
        assertEquals(1,parserResult.getCities().get("Братск").getValueHouse(5));
        assertEquals(1,parserResult.getCities().get("Братск").getValueHouse(4));
        assertEquals(1,parserResult.getCities().get("Балаково").getValueHouse(2));
    }

    @Test
    void parseFileXML() {
        ParserResult parserResult = Parser.parseFile("src/test/resources/test.xml");

        assertEquals(6,parserResult.getHouses().size());
        assertTrue(parserResult.getHouses().contains(new House("Владивосток", "Дальняя улица", 196, 3)));
        assertFalse(parserResult.getHouses().contains(new House("Владивосток", "Дальняя улица", 196, 2)));
        assertFalse(parserResult.getHouses().contains(new House("Влад", "Дальняя улица", 196, 3)));

        assertEquals(1,parserResult.getDuplicateHouse().size());
        assertEquals(2,parserResult.getDuplicateHouse().get(new House("Владивосток", "Дальняя улица", 196, 3)));

        assertEquals(6,parserResult.getCities().size());
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(1));
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(2));
        assertEquals(1,parserResult.getCities().get("Владивосток").getValueHouse(3));
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(4));
        assertEquals(0,parserResult.getCities().get("Владивосток").getValueHouse(5));
        assertEquals(1,parserResult.getCities().get("Барнаул").getValueHouse(2));
        assertEquals(1,parserResult.getCities().get("Братск").getValueHouse(5));
    }

    @Test
    void rightPatchFile() {
        ParserResult parserResult1 = Parser.parseFile("src/test/resources/test.xml");
        assertNull(parserResult1.getError());
    }

    @Test
    void wrongPatchFile() {
        ParserResult parserResult1 = Parser.parseFile("C:/666/sd.csv");
        assertEquals("Файл не найден",parserResult1.getError());
    }

    @Test
    void wrongExtensionFile() {
        ParserResult parserResult1 = Parser.parseFile("src/test/resources/test.txt");
        assertEquals("Файлы с таким расширением не поддерживаются",parserResult1.getError());

        parserResult1 = Parser.parseFile("src/test/resources/test.png");
        assertEquals("Файлы с таким расширением не поддерживаются",parserResult1.getError());
    }

    @Test
    void wrongSyntaxFile() {
        ParserResult parserResult = Parser.parseFile("src/test/resources/testWrong.xml");
        assertEquals("Синтаксическая ошибка XML",parserResult.getError());
    }

    @Test
    void wrongFileXML() {
        ParserResult parserResult = Parser.parseFile("src/test/resources/testWrong2.xml");
        assertEquals("Файл не корректен",parserResult.getError());
    }

    @Test
    void wrongFileCSV() {
        ParserResult parserResult = Parser.parseFile("src/test/resources/testWrong.csv");
        assertEquals("Файл не корректен",parserResult.getError());
    }
}