@SuppressWarnings("SameParameterValue")
public class PrivateAccessModifier {
    private String name;

    // These are public so that the world can gain access to the data stored in the class
    // without gaining access to the variable itself.
    private String getName() { return this.name; }
    private void setName(String name) { this.name = name; }

    public static void main(String[] args) {
        PrivateAccessModifier pam = new PrivateAccessModifier();
        pam.setName("Gizmo");
        System.out.println(pam.getName());
    }

}
