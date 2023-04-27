

/**
 * @author Paulene Dean
 * @version 1.0
 * @created 13-Oct-2021 11:10:23 PM
 */
public class ShoppingBasket {

	private String shoppingBasketNumber;
	private LineItem m_LineItem;

	public ShoppingBasket(){

	}

	public void finalize() throws Throwable {

	}
	public void addLineItem(){

	}

	public void createNewBasket(){

	}

	public void deleteItem(){

	}

	public LineItem getLineItem(){
		return m_LineItem;
	}

	public void processOrder(){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLineItem(LineItem newVal){
		m_LineItem = newVal;
	}
}//end ShoppingBasket