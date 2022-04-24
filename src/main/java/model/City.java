package model;

public class City {

    private String cityName;
    private int valueFloors[] = new int[5];

    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getValueHouse(int floor) {
        return valueFloors[floor - 1];
    }

    public void inkValueHouse(int floor) {
        if (floor > 0 && floor <= 5) {
            this.valueFloors[floor - 1]++;
        }
    }

    public String getCityName() {
        return cityName;
    }
}
