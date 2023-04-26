import java.util.stream.IntStream;

class Client {
    private final CompositeEntity compositeEntity = new CompositeEntity();

    public void printData() {

        // Cool Java 8 thing (Expression Lambda)
        IntStream.range(0, compositeEntity.getData().length).forEach(
                n-> System.out.println("Data: " + compositeEntity.getData()[n])
        );
    }

    public void setData(String data1, String data2) {
        compositeEntity.setData(data1, data2);
    }

}
