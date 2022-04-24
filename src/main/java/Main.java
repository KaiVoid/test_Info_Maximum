import model.City;
import model.House;
import parser.Parser;
import parser.ParserResult;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String command;
        boolean exitFlag = false;

        while (!exitFlag) {
            System.out.println("Введите путь до файла, end - для выхода из программы");
            command = console.nextLine();

            if (Files.exists(Paths.get(command))) {
                showResult(Parser.parseFile(command));
            } else if (command.equals("end")) {
                System.out.println("Прощай!");
                exitFlag = true;
            } else {
                System.out.println("Путь некорректный");
            }
        }
    }

    private static void showResult(ParserResult result) {
        if (result.getError() != null) {
            System.out.println("Ошибка: " + result.getError());
        } else {
            for (Map.Entry<House, Integer> entry : result.getDuplicateHouse().entrySet()) {
                System.out.println("Дублирующаяся запись:\"" + entry.getKey().getCity() + " " + entry.getKey().getStreet() + " " + entry.getKey().getHouse() + "\" Количество повторений:" + entry.getValue());
            }

            for (Map.Entry<String, City> entry : result.getCities().entrySet()) {
                System.out.println("В городе " + entry.getValue().getCityName() + " находится ");
                System.out.println(" одноэтажных зданий : " + entry.getValue().getValueHouse(1));
                System.out.println(" двухэтажных : " + entry.getValue().getValueHouse(2));
                System.out.println(" трехэтажных : " + entry.getValue().getValueHouse(3));
                System.out.println(" четырехэтажных : " + entry.getValue().getValueHouse(4));
                System.out.println(" пятиэтажных : " + entry.getValue().getValueHouse(5));
            }
        }
    }
}
