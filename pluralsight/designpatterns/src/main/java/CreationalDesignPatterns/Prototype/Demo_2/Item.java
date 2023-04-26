package CreationalDesignPatterns.Prototype.Demo_2;

public abstract class Item implements Cloneable {

   /*
      This is our abstract pojo template, but its also a prototype.
      The advantage of implementing Cloneable HERE is that any class that implements Item, will also implement
      Cloneable transitively.
    */
   private String title;
   private double price;
   private String url;

   /*
      NOTE:
         Cloneable interface requires us to return an instance of Object.
    */
   @Override
   protected Object clone() throws CloneNotSupportedException {
       return super.clone();
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
}
