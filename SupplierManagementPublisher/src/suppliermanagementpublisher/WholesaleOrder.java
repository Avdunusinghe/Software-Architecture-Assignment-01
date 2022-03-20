package suppliermanagementpublisher;

public class WholesaleOrder {
	
	private int id;
	private String noOfBooks;
	private String bookName;
	private String supplierCompany;
	private Boolean isActive;
	
	public WholesaleOrder() {
		super();
	}

	public WholesaleOrder(int id, String noOfBooks, String bookName, String supplierCompany, 
			Boolean isActive) {
		super();
		this.id = id;
		this.noOfBooks = noOfBooks;
		this.bookName = bookName;
		this.supplierCompany = supplierCompany;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNoOfBooks() {
		return noOfBooks;
	}

	public void setNoOfBooks(String noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getSupplierCompany() {
		return supplierCompany;
	}

	public void setSupplierCompany(String supplierCompany) {
		this.supplierCompany = supplierCompany;
	}

	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	
	
	
	
	
	

}
