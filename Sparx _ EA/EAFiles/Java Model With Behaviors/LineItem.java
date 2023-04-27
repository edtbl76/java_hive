

/**
 * @author Paulene Dean
 * @version 1.0
 * @created 13-Oct-2021 11:10:22 PM
 */
public class LineItem {

	private int quantity;
	private StockItem item;

	public LineItem(){

	}

	public void finalize() throws Throwable {

	}
	public StockItem getItem(){
		return item;
	}

	public int getQuantity(){
		return quantity;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItem(StockItem newVal){
		item = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setQuantity(int newVal){
		quantity = newVal;
	}
}//end LineItem