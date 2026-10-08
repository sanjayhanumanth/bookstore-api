package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    /** Seeds a few rows on first run so the API isn't empty. */
    @Bean
    CommandLineRunner seedData(BookRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Book(null, "Clean Code", "Robert C. Martin", "9780132350884", 2008));
                repository.save(new Book(null, "Effective Java", "Joshua Bloch", "9780134685991", 2018));
                repository.save(new Book(null, "Refactoring", "Martin Fowler", "9780134757599", 2018));
            }
        };
    }
}
