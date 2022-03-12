package inventorymanagementpublisher;

public class Book {
	
	private int id;
	private String title;
	private int isbn;
	private String author;
	private int price;
	private boolean isActive;
	
	
	public Book() {
		super();
	}


	public Book(int id, String title, int isbn, String author, int price, boolean isActive) {
		super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.isActive = isActive;
		
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


	public int getIsbn() {
		return isbn;
	}


	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	

}
