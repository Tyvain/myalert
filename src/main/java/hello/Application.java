package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class Application {
    public static int SIZE = 40;
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(PixelRepository repository) {
        return (args) -> {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    repository.save(new Pixel(new Point(row, col), "_7f8c8d"));
                }
            }
        };
    }
}
