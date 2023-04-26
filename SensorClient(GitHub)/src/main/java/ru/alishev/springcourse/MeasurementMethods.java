package ru.alishev.springcourse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MeasurementMethods {
    public void postSensor(Sensor sensor){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/sensors/registration";
        Map<String , String> jsonToSend = new HashMap<>();
        jsonToSend.put("name",sensor.getName());
        HttpEntity<Map<String , String>> request = new HttpEntity<>(jsonToSend);

        String post = restTemplate.postForObject(url, request, String.class);
        System.out.println(post);
    }
    public void send1000Mappings(Sensor sensor){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements/add";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = "";

            try {
                jsonStr = mapper.writeValueAsString(new Measurement(random.nextDouble(-100,100), random.nextBoolean(),sensor));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            HttpEntity<String> request = new HttpEntity<>(jsonStr, httpHeaders);
            String answer =  restTemplate.postForObject(url,request, String.class);
            System.out.println(answer);
        }}

    public void getMeasurements(){
        RestTemplate restTemplate = new RestTemplate();
        String url =  "http://localhost:8080/measurements";
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj= null;
        List<Measurement> list = null;
        try {
            list = mapper.readValue(response, new TypeReference<List<Measurement>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (Measurement m : list
        ) {
            System.out.println(m);
        }


    }
}
