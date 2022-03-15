package customermanagementpublisher;

public class RequestBook {
	
	private int id;
	private String bookName;
	private String author;
	private String message;
	
	
	public RequestBook() {
		super();
	}


	public RequestBook(int id, String booknName, String author, String message) {
		super();
		this.id = id;
		this.bookName = booknName;
		this.author = author;
		this.message = message;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBooknName() {
		return bookName;
	}


	public void setBooknName(String booknName) {
		this.bookName = booknName;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
