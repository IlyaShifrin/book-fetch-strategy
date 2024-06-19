package telran.java52;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.java52.book.dao.BookRepository;

@SpringBootApplication
public class BookFetchStrategyApplication implements CommandLineRunner {

	@Autowired
	BookRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookFetchStrategyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.addBooks();
		repository.printAuthorsOfBook("978-081011845");
		
	}

}
