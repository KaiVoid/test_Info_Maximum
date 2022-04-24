package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static ParserResult parseFile(String path) {
        ParserResult result = null;
        if (path.substring(path.lastIndexOf(".") + 1).equals("xml")) {
            result = parseXML(path);
        } else if (path.substring(path.lastIndexOf(".") + 1).equals("csv")) {
            result = parseCSV(path);
        } else {
            result = new ParserResult("Файлы с таким расширением не поддерживаются");
        }
        return result;
    }

    private static ParserResult parseXML(String path) {
        ParserResult resultOfParser = new ParserResult();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (!qName.equals("item")) {
                        return;
                    }
                    resultOfParser.addHouse(attributes.getValue(0), attributes.getValue(1), Integer.parseInt(attributes.getValue(2)), Integer.parseInt(attributes.getValue(3)));
                }
            };
            saxParser.parse(path, handler);
        } catch (ParserConfigurationException e) {
            resultOfParser.setError("Ошибка конфигурации");
        } catch (SAXException saxException) {
            resultOfParser.setError("Синтаксическая ошибка XML");
        } catch (IOException e) {
            resultOfParser.setError("Файл не найден");
        } catch (Exception e) {
            resultOfParser.setError("Файл не корректен");
        }
        return resultOfParser;
    }

    private static ParserResult parseCSV(String path) {
        ParserResult resultOfParser = new ParserResult();
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(path));
            for (int s = 1; s < fileLines.size(); s++) {
                String[] splitedText = fileLines.get(s).split(";");
                ArrayList<String> columnList = new ArrayList<>();
                for (int i = 0; i < splitedText.length; i++) {
                    if (IsColumnPart(splitedText[i])) {
                        String lastText = columnList.get(columnList.size() - 1);
                        columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                    } else {
                        splitedText[i] = splitedText[i].replaceAll("\"", "");
                        columnList.add(splitedText[i]);
                    }
                }
                resultOfParser.addHouse(columnList.get(0), columnList.get(1), Integer.parseInt(columnList.get(2)), Integer.parseInt(columnList.get(3)));
            }
        } catch (IOException e) {
            resultOfParser.setError("Файл не найден");
        }catch (Exception e) {
            resultOfParser.setError("Файл не корректен");
        }
        return resultOfParser;
    }

    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
