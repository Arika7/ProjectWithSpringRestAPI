package ru.alishev.springcourse;

public class Sensor {
    String name;

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Sensor() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                '}';
    }
}
