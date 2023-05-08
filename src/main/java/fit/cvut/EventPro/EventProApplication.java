package fit.cvut.EventPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"fit.cvut.EventPro", "fit.cvut.EventPro.service"})
public class EventProApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventProApplication.class, args);
    }
}
