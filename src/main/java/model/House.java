package model;

import java.util.Objects;

public class House {

    private String city;
    private String street;
    private int house;
    private int floor;

    public House(String city, String street, int house, int floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house1 = (House) o;
        return house == house1.house && floor == house1.floor && Objects.equals(city, house1.city) && Objects.equals(street, house1.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFloor() {
        return floor;
    }

}
