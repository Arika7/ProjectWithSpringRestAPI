package ru.alishev.springcourse.Project3.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "min:2, max: 30")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
