

/**
 * @author Paulene Dean
 * @version 1.0
 * @created 13-Oct-2021 11:10:23 PM
 */
public class Transaction {

	private Date date;
	private String orderNumber;
	private LineItem m_LineItem;
	private Account account;

	public Transaction(){

	}

	public void finalize() throws Throwable {

	}
	public Account getAccount(){
		return account;
	}

	public Date getDate(){
		return date;
	}

	public LineItem getLineItem(){
		return m_LineItem;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public boolean loadAccountHistory(){
		return false;
	}

	public void loadOpenOrders(){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAccount(Account newVal){
		account = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDate(Date newVal){
		date = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLineItem(LineItem newVal){
		m_LineItem = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrderNumber(String newVal){
		orderNumber = newVal;
	}
}//end Transaction