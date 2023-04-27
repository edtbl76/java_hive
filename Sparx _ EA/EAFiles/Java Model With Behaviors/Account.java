

/**
 * @author Paulene Dean
 * @version 1.0
 * @created 13-Oct-2021 11:10:22 PM
 */
public class Account {

	private String billingAddress;
	private boolean bValidUser;
	private boolean closed;
	private String deliveryAddress;
	private String emailAddress;
	private String name;
	private String password;
	private Order m_Order;
	private ShoppingBasket basket;

	public Account(){

	}

	public void finalize() throws Throwable {

	}
	public void createNewAccount(){

	}

	public ShoppingBasket getBasket(){
		return basket;
	}

	public String getBillingAddress(){
		return billingAddress;
	}

	public boolean getClosed(){
		return closed;
	}

	public String getDeliveryAddress(){
		return deliveryAddress;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public String getName(){
		return name;
	}

	public Order getOrder(){
		return m_Order;
	}

	public void loadAccountDetails(){

	}

	public void markAccountClosed(){

	}

	public void retrieveAccountDetails(){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void setBasket(ShoppingBasket newVal){
		basket = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setBillingAddress(String newVal){
		billingAddress = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClosed(boolean newVal){
		closed = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDeliveryAddress(String newVal){
		deliveryAddress = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmailAddress(String newVal){
		emailAddress = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrder(Order newVal){
		m_Order = newVal;
	}

	public void submitNewAccountDetails(){

	}

	/**
	 * 
	 * @param Password
	 * @param UserName
	 */
	public boolean validateUser(String Password, String UserName){
		//Validate User Behavior

		return doValidateUser(Password,UserName);
	}
	/* Begin - EA generated code for  Activities and Interactions */
	public void doMarkAccountClosed()
	{
		// behavior is a Activity
		doValidateUser(password,name);
		if (bValidUser)
		{
			setClosed(true);
		}
		else
		{
			System.out.println("Invalid user");
		}
		return;
	}

	public boolean doValidateUser(String Password,String UserName)
	{
		// behavior is a Activity
		loadAccountDetails();
		boolean bRet;
		if (UserName==name)
		{
			if (Password == password)
			{
				bRet = true;
				bValidUser = true;
			}
			else
			{
				bRet = false;
			}
		}
		else
		{
			bRet = false;
		}
		return bRet;
	}

	public void doCreateNewAccount()
	{
		// behavior is a Interaction
		if (bValidUser)
		{
			doLoadAccountDetails();
		}
		else
		{
			return;
		}
	}

	public boolean doLoadAccountDetails()
	{
		// behavior is a Interaction
		if (name == "")
		{
			account.createNewAccount();
			account.setName("User1");
			account.setEmailAddress("Email Address1");
			account.setBillingAddress("Billing Address1");
			account.setDeliveryAddress("Delivery Address1");
			account.submitNewAccountDetails();
		}
		else if (closed)
		{
			account.retrieveAccountDetails();
		}
		return true;
	}

	/* End - EA generated code for  Activities and Interactions */
}//end Account