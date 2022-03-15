package customermanagementpublisher;

public class Order {
	
	private int id;
	private String bookName;
	private String address;
	private boolean isActive;
	
	
	public Order() {
		super();
	}


	public Order(int id, String bookName, String address, boolean isActive) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.address = address;
		this.isActive = isActive;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	

}
