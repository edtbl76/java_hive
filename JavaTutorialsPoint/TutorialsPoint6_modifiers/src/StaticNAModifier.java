public class StaticNAModifier {

    private static int numInstances = 0;

    private static int getCount() {
        return numInstances;
    }

    private static void addInstance() {
        numInstances++;
    }

    private StaticNAModifier() {
        StaticNAModifier.addInstance();
    }

    public static void main(String[] args) {
        System.out.println("Starting with " + StaticNAModifier.getCount() + " instances." );

        for (int i = 0; i < 500; ++i) {
            new StaticNAModifier();
        }
        System.out.println("Created " + StaticNAModifier.getCount() + " instances." );
    }
}
