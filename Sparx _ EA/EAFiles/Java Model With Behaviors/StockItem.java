

/**
 * @author Paulene Dean
 * @version 1.0
 * @created 13-Oct-2021 11:10:23 PM
 */
public class StockItem {

	private string Author;
	private string catalogNumber;
	private number costPrice;
	private number listPrice;
	private string title;

	public StockItem(){

	}

	public void finalize() throws Throwable {

	}
	public string getAuthor(){
		return Author;
	}

	public string getCatalogNumber(){
		return catalogNumber;
	}

	public number getCostPrice(){
		return costPrice;
	}

	public number getListPrice(){
		return listPrice;
	}

	public string getTitle(){
		return title;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAuthor(string newVal){
		Author = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCatalogNumber(string newVal){
		catalogNumber = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCostPrice(number newVal){
		costPrice = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setListPrice(number newVal){
		listPrice = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTitle(string newVal){
		title = newVal;
	}
}//end StockItem