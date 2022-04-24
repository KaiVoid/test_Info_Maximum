package parser;

import model.City;
import model.House;

import java.util.HashMap;
import java.util.HashSet;

public class ParserResult {

    private HashSet<House> houses;
    private HashMap<House, Integer> duplicateHouse;
    private HashMap<String, City> cities;

    private String error;

    public ParserResult() {
        this.houses= new HashSet<>();
        this.duplicateHouse = new HashMap<>();
        this.cities = new HashMap<>();
    }

    public ParserResult(String error) {
        this.error = error;
    }

    public void addHouse(String city, String street, int houseNumber, int floor) {
        House house = new House(city, street, houseNumber,floor);

        if (houses.contains(house)) {
            if (!duplicateHouse.containsKey(house)) duplicateHouse.put(house, 1);
            duplicateHouse.put(house, duplicateHouse.get(house) + 1);
        } else {
            houses.add(house);
            if (!cities.containsKey(house.getCity())) cities.put(house.getCity(), new City(house.getCity()));
            cities.get(house.getCity()).inkValueHouse(house.getFloor());
        }
    }

    public String getError(){
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HashSet<House> getHouses() {
        return houses;
    }

    public HashMap<House, Integer> getDuplicateHouse() {
        return duplicateHouse;
    }

    public HashMap<String, City> getCities() {
        return cities;
    }
}
