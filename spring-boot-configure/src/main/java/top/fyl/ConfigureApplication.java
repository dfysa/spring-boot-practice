package top.fyl;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dfysa
 * @data 23/9/2024 下午1:40
 * @description
 */
@SpringBootApplication
//@ImportResource(locations = {"classpath:beans.xml"})
public class ConfigureApplication {
    public static void main(String[] args) {   SpringApplication.run(ConfigureApplication.class,args);
    }
}

