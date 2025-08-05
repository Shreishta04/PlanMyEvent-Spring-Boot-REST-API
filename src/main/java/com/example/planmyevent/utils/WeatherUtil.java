package com.example.planmyevent.utils;

import com.example.planmyevent.dtos.WeatherRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherUtil {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String getWeatherResponse(WeatherRequestDTO weatherRequestDTO) {
        try {
            String city = weatherRequestDTO.getCity();
            LocalDate date = weatherRequestDTO.getDate();
            System.out.println("API Key being used: " + apiKey);
            String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey + "&units=metric";
            ;
            System.out.println("Final Weather API URL: " + url);
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = objectMapper.readTree(response);

            JsonNode forecastList = root.path("list");

            for (JsonNode forecast : forecastList) {
                String dateTime = forecast.path("dt_txt").asText();
                LocalDate forecastDate = LocalDate.parse(dateTime.split(" ")[0], DateTimeFormatter.ISO_LOCAL_DATE);

                if (forecastDate.equals(date)) {
                    String condition = forecast.path("weather").get(0).path("main").asText();
                    double temp = forecast.path("main").path("temp").asDouble();
                    return String.format("%s, %.1f°C", condition, temp);
                }
            }
            return "Unavailable: No forecast for " + date;

        } catch (HttpClientErrorException e) {
            System.out.println("HttpClientErrorException: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return "Unavailable: Could not retrieve weather for " + weatherRequestDTO.getCity();
        } catch (Exception e) {
            return "Unavailable: Internal error occurred";
        }
    }
}
