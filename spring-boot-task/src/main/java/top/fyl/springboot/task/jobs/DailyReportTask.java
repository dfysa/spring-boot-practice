package top.fyl.springboot.task.jobs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.fyl.springboot.task.Service.MailService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/10/28 下午3:41
 * @description
 */
//@Component
//@AllArgsConstructor
//@Slf4j
//public class DailyReportTask {
//    private final MailService mailService;
//
//    @Scheduled(cron = "0 54 15 * * ?")
//    public void sendReport(){
//        String report ="这是每日报表内容";
//        mailService.sendMail("846241197@qq.com","每日数据报表",report);
//        log.info("报表已生诚，并发送邮件完成！{}",LocalDateTime.now());
//    }
//}

@Component("dailyReportTask1")
@AllArgsConstructor
@Slf4j
public class DailyReportTask {
    private final MailService mailService;
    private static final String API_KEY = "07b035a9f4f29de4d9c90a72df5db008";  // 替换为您的高德 API Key

    @Scheduled(cron = "0 47 17 * * ?")  // 每天早上 9 点执行
    public void sendReport() {
        String weatherReport = getWeather("南京"); // 替换为您需要查询的城市
        if (weatherReport != null) {
            mailService.sendMail("846241197@qq.com", "今日天气报告", weatherReport);
            log.info("发送天气报告成功 {}", LocalDateTime.now());
        } else {
            log.warn("天气报告获取失败，未发送邮件 {}", LocalDateTime.now());
        }
    }

    private String getWeather(String city) {
        try {
            String urlStr = "https://restapi.amap.com/v3/weather/weatherInfo?city=" +
                    URLEncoder.encode(city, "UTF-8") +
                    "&key=" + API_KEY;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 解析天气信息
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());

            if (rootNode.path("status").asText().equals("1")) {
                JsonNode weatherInfo = rootNode.path("lives").get(0);
                String cityName = weatherInfo.path("city").asText();
                String weather = weatherInfo.path("weather").asText();
                String temperature = weatherInfo.path("temperature").asText();
                String windDirection = weatherInfo.path("winddirection").asText();
                String windPower = weatherInfo.path("windpower").asText();

                return String.format("城市: %s\n天气: %s\n温度: %s°C\n风向: %s\n风力: %s", cityName, weather, temperature, windDirection, windPower);
            } else {
                log.warn("获取天气信息失败，错误信息: {}", rootNode.path("info").asText());
                return null;
            }
        } catch (Exception e) {
            log.error("获取天气信息异常", e);
            return null;
        }
    }
}
