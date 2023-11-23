package algot.emil.messagesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = "algot.emil.entities")
@SpringBootApplication
public class MessagesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesApiApplication.class, args);
    }

}
