import java.util.*;

class Dog implements Comparator<Dog>, Comparable<Dog> {

    private String name;
    private int age;
    Dog() {}

    Dog(String n, int a) {
        name = n;
        age = a;
    }

    String getDogName() {
        return name;
    }

    int getDogAge() {
        return age;
    }

    // Override CompareTo and compare  (This sorts them by age)
    public int compareTo(Dog d) {
        return (this.name).compareTo(d.name);
    }

    public int compare(Dog d, Dog d1) {
        return d.age - d1.age;
    }
}

public class ComparatorDemo {

    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();

        list.add(new Dog("Shaggy", 3));
        list.add(new Dog("Lacy", 2));
        list.add(new Dog("Roger", 10));
        list.add(new Dog("Tommy", 4));
        list.add(new Dog("Tammy", 1));

        // sort it
        Collections.sort(list);

        for(Dog a: list) // print sorted list
            System.out.print(a.getDogName() + ", ");

        // Sorts array lis tusing comparator
        list.sort(new Dog());
        System.out.println(" ");

        for(Dog a: list) // print sorted list of ages
            System.out.print(a.getDogName() + "  : " + a.getDogAge() + ", ");
        System.out.println();

    }
}
