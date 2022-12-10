package Lab09;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BookStoreTest {

	private BookStore store;
	private Book b1 = new Book(1, "Harper Lee", "To Kill a Mockingbird");
	private Book b2 = new Book(2, "Harper Lee", "To Kill a Mockingbird");
	private Book b3 = new Book(3, "Frances Hodgson", "The Secret Garden");
	private Book b4 = new Book(5, "J.K. Rowling",
			"Harry Potter and the Sorcerer's Stone");
	private Book b5 = new Book(4, "Douglas Adams",
			"The Hitchhiker's Guide to the Galaxy");

	/**
	 * setup the store
	 * 
	 */
	@Before
	public void setUpBookStore() {
		store = new BookStore();
		store.addBook(b1);
		store.addBook(b2);
		store.addBook(b3);
		store.addBook(b4);
	}

	/**
	 * tests the addition of book
	 * 
	 */
	@Test
	public void testAddBook() {
        store.addBook(b5);
        assertTrue(store.getBooks().contains(b5));
	}
	
    /**
     * tests sorting of books by author name
     * 
     */
    @Test
    public void testGetBooksSortedByAuthor() {
        List<Book> sorted = store.getBooksSortedByAuthor();
        String temp=null;
        for(Book book: sorted) {
            if(temp == null) {
                temp = book.getAuthorName();
                continue;
            }
            assertTrue( book.getAuthorName().compareTo(temp) >= 0); // The latter title should be larger than or equal to the former
            temp = book.getAuthorName();
        }
    }

    /**
     * tests sorting of books by title
     * 
     */
    @Test
    public void testGetBooksSortedByTitle() {
        List<Book> sorted = store.getBooksSortedByTitle();
        String temp = null;
        for(Book book: sorted) {
            if(temp == null) {
                temp = book.getTitle();
                continue;
            }
            assertTrue( book.getTitle().compareTo(temp) >= 0); // The latter title should be larger than or equal to the former
            temp = book.getTitle();
        }
    }
    
    /**
     * tests the assessor of books
     * 
     */
    @Test
    public void testGetBooks() {
        ArrayList<Book> expected = new ArrayList<Book>();
        expected.add(b1);
        expected.add(b2);
        expected.add(b3);
        expected.add(b4);
        assertEquals(expected,store.getBooks()); // Should be equivalent
    }
    
	/**
	 * tests the deletion of book
	 * 
	 */
	@Test
	public void testDeleteBook() {
        store.deleteBook(b4);
        assertFalse(store.getBooks().contains(b4)); // Should not contain the object
	}

	/**
	 * tests the number of copies of book in store
	 * 
	 */
	@Test
	public void testCountBookWithTitle() {
//	    assertEquals(2, store.countBookWithTitle("To Kill a Mockingbird")); // Hard coded
	    List<Book> books = store.getBooks();
	    int[] counts = new int[books.size()];
        
	    for(int i = 0; i < books.size(); i++) { // Manually counts each titles
	        int count = 0;
	        String title = books.get(i).getTitle();
	        for(Book book: books) {
	            if(book.getTitle().equals(title)) count++;
	        }
	        counts[i] = count;
	    }
        for(int i = 0; i < books.size(); i++) {
            assertEquals(counts[i], store.countBookWithTitle(books.get(i).getTitle()));// Compare the results
        }     
	    
	}

}
