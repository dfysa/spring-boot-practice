package top.fyl.springboot.task.entity;

import lombok.Data;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/28 下午5:05
 * @description
 */
@Data
public class WeatherResponse {
    private String status;
    private String info;
    private String infocode;
    private List<Weather> lives;

    @Data
    public static class Weather {
        private String province;
        private String city;
        private String adcode;
        private String weather;
        private String temperature;
        private String winddirection;
        private String windpower;
        private String humidity;
        private String reporttime;
    }
}