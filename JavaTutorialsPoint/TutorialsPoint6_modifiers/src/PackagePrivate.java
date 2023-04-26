@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PackagePrivate {

    private final String version = "1.5.1";
    @SuppressWarnings("SameReturnValue")
    private boolean process() {
        return true;
    }

    private PackagePrivate() { System.out.println(version + " \n" +  process());}

    public static void main(String[] args) {
        PackagePrivate pp = new PackagePrivate();
    }
}
