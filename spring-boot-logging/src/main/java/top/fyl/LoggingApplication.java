package top.fyl;
import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dfysa
 * @data 2024/9/9 下午4:18
 * @description
 */
@SpringBootApplication
public class LoggingApplication {
    public static void main(String[] args) {   SpringApplication.run(LoggingApplication.class,args);
    }
}