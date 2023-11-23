package algot.emil.messagesapi;

import algot.emil.entities.*;
import algot.emil.enums.EncounterPriority;
import algot.emil.enums.EncounterStatus;
import algot.emil.enums.Sex;
import algot.emil.messagesapi.repositories.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@EntityScan(basePackages = "algot.emil.entities")
@SpringBootApplication
public class MessagesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(MessageRepository messageRepository) {

        return args -> {

            Message message = new Message(3L,1L, 3L,"hej");
            messageRepository.save(message);
        };
    }

}
