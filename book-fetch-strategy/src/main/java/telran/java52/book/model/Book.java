package telran.java52.book.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "isbn")
@Entity
public class Book {
	@Id
	String isbn;
	String title;
	@Singular
	// FetchType.EAGER только если всегда!!! нужны авторы при запросе книги
	// Может ударить по производительности
	@ManyToMany//(fetch = FetchType.EAGER)
	Set<Author> authors; 
}
