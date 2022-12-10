package Lab09;

public class Book {

	private int id; // the unique id assigned to book
	private String title; // book title
	private String authorName;// author of the book

	public Book() {
		super();
	}

	public Book(int id, String authorName, String title) {
		this.id = id;
		this.title = title;
		this.authorName = authorName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "\n Book [id=" + id + ", title=" + title + ", authorName="
				+ authorName + "]";
	}

}
