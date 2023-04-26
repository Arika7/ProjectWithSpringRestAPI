package ru.alishev.springcourse;


import java.util.List;


public class Consumer {
    public static void main(String[] args) {
        MeasurementMethods methods = new MeasurementMethods();
        Sensor sensor = new Sensor("sensor7");
        methods.postSensor(sensor);
        methods.send1000Mappings(sensor);
        methods.getMeasurements();

    }




}
