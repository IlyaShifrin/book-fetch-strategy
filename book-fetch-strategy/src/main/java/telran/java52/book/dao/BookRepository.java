package telran.java52.book.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;

@Repository
public class BookRepository {

	// PersistenceContextType.EXTENDED - держит соединение сколько может
	// не рекомендуется как решение
	@PersistenceContext//(type = PersistenceContextType.EXTENDED)
	EntityManager em;
	
	@Transactional
	public void addBooks() {
		Author markTwain = Author.builder()
								.fullName("Mark Twain")
								.build();
		
		em.persist(markTwain);
		
		Book pandp = Book.builder()
						.isbn("978-0140350173")
						.author(markTwain)
						.title("The Prince and The Pauper")
						.build();
		em.persist(pandp);
		
		Author ilf = Author.builder()
						.fullName("Ilya Ilf")
						.build();
		
		Author petrov = Author.builder()
				.fullName("Yevgeny Petrov")
				.build();
		em.persist(ilf);
		em.persist(petrov);
		
		Book chairs12 = Book.builder()
				.isbn("978-081011845")
				.author(ilf)
				.author(petrov)
				.title("The Twelve Chairs")
				.build();
		em.persist(chairs12);
		
	}
	
	//*@Transactional(readOnly = true)
	public void printAuthorsOfBook(String isbn) {
//		Book book = em.find(Book.class, isbn);
		// join fetch - оптимальный вариант для жадной инициализации (борьба с ленивой инициализацией))
		TypedQuery<Book> query = em.createQuery("select b from Book b left join fetch b.authors a where b.isbn=?1", Book.class);
		query.setParameter(1, isbn);
		Book book = query.getSingleResult();
		Set<Author> authors = book.getAuthors();
		authors.forEach(a -> System.out.println(a.getFullName()));
		// if do something
		// допустим здесь соединение уже не нужно,
		// тогда это плохое решение *
	}
}
