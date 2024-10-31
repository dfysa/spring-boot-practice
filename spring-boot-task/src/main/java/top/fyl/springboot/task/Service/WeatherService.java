package top.fyl.springboot.task.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.fyl.springboot.task.entity.WeatherResponse;

/**
 * @author dfysa
 * @data 2024/10/28 下午5:05
 * @description
 */
@Service
public class WeatherService {
    // 写死城市编码和 API 密钥
    private final String API_URL = "https://restapi.amap.com/v3/weather/weatherInfo?city=320113&key=07b035a9f4f29de4d9c90a72df5db008";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public String getWeather() throws Exception {
        String response = restTemplate.getForObject(API_URL, String.class);
        WeatherResponse weatherResponse = objectMapper.readValue(response, WeatherResponse.class);

        if ("10000".equals(weatherResponse.getStatus()) && !weatherResponse.getLives().isEmpty()) {
            WeatherResponse.Weather weather = weatherResponse.getLives().get(0);
            return formatWeatherMessage(weather);
        } else {
            return "天气信息获取失败：" + weatherResponse.getInfo();
        }
    }

    private String formatWeatherMessage(WeatherResponse.Weather weather) {
        return String.format("城市：%s\n天气：%s\n温度：%s°C\n风向：%s\n风力：%s\n湿度：%s%%\n更新时间：%s",
                weather.getCity(),
                weather.getWeather(),
                weather.getTemperature(),
                weather.getWinddirection(),
                weather.getWindpower(),
                weather.getHumidity(),
                weather.getReporttime());
    }
}