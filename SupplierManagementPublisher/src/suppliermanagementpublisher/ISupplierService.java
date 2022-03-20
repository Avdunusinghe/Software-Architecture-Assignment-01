package suppliermanagementpublisher;

public interface ISupplierService {

	public void saveSupplier();
	public void getAllSupplierDetails();
	public void deleteSupplier();
	public void getSupplierById();
	public void wholesaleOrder();
	public void getAllWholeSaleOrders();
	public void deleteWholesaleOrder();
	public void getWholesaleOrderById();
	public void genarateSupplierDetailsReport();
	public void genarateWholeSaleOrderDetailsReport();

}
