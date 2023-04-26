public class ProxyImage implements Image {

    private RealImage realImage;
    private final String fileName;

    public ProxyImage(String fileName){
        // no load from disk!
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // If there isn't a realImage instance, create one, and then do the needful.
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
