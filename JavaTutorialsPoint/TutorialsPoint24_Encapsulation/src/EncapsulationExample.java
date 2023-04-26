class Encap {

    private String name;
    private String id;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}


public class EncapsulationExample {

    public static void main(String[] args) {

        Encap encap = new Encap();

        encap.setName("Ed");
        encap.setAge(42);
        encap.setId("Boogers");
        System.out.println("Name: " + encap.getName() + ", Age: " + encap.getAge() + ", ID: " + encap.getId());
    }
}
