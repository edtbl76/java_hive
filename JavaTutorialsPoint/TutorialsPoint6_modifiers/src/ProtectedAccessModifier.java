/*
Example of how a subclass an access a protected class.
 */
public class ProtectedAccessModifier {

    @SuppressWarnings("unused")
    private class DadString{ @SuppressWarnings("unused")
    protected String dump(String s) { return s; }}

    @SuppressWarnings("SameParameterValue")
    class ChildString{ String dump(String s) { return "Daddy! " + s; }}

    private ProtectedAccessModifier() {
        ChildString cs = new ChildString();
        System.out.println(cs.dump("Poop"));
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused") ProtectedAccessModifier pam = new ProtectedAccessModifier();
    }

}
