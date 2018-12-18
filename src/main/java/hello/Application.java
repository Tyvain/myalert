package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(PixelRepository repository) {
        return (args) -> {
            for (int row = 0; row < 40; row++) {
                for(int col = 0; col < 40; col++) {
                    repository.save(new Pixel(new Point(row, col), (Math.random() <= 0.5) ? "w3-flat-turquoise":"w3-flat-wisteria"));
                }}
        };
    }
}
