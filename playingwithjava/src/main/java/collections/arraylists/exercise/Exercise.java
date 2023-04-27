package collections.arraylists.exercise;

import utils.Generated;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Generated
@SuppressWarnings("all")
public class Exercise {

    public static void main(String[] args) {
        List<PuppyDawg> puppies = new ArrayList<>();
        puppies.add(new PuppyDawg("Gravy", 4, "Beagle"));
        puppies.add(new PuppyDawg("Maddie", 5, "Golden Doodle"));
        puppies.add(new PuppyDawg("Finn", 5, "Golden Doodle"));
        puppies.add(new PuppyDawg("Pia", 1, "Shiba Inu"));
        puppies.add(new PuppyDawg("Blue", 2, "Pitbull"));
        puppies.add(new PuppyDawg("Rosie", 6, "Labrador Retriever"));

        System.out.println("Puppies over 4: (enhanced for)");
        for (PuppyDawg puppy : puppies) {
            if (puppy.getAge() > 4){
                System.out.println("\t" + puppy.getName());
            }
        }

        System.out.println("Puppies over 4: (streams)");
        puppies.stream()
                .filter(puppy -> puppy.getAge() > 4)
                .map(PuppyDawg::getName)
                .forEach(System.out::println);


        System.out.println("Removing Golden Doodles: (while/list iterator)");
        Iterator<PuppyDawg> iterator = puppies.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getBreed().equals("Golden Doodle")) {
                iterator.remove();
            }
        }
        puppies
                .stream()
                .map(PuppyDawg::getName)
                .forEach(System.out::println);


        System.out.println("Adding Golden Doodles back!");
        puppies.add(new PuppyDawg("Maddie", 5, "Golden Doodle"));
        puppies.add(new PuppyDawg("Finn", 5, "Golden Doodle"));
        puppies
                .stream()
                .map(PuppyDawg::getName)
                .forEach(System.out::println);

        System.out.println("Removing Golden Doodles: (removeIf())");
        puppies.removeIf(puppy -> puppy.getBreed().equals("Golden Doodle"));
        puppies
                .stream()
                .map(PuppyDawg::getName)
                .forEach(System.out::println);


        System.out.println("Sort by name");
        puppies.sort(Comparator.comparing(PuppyDawg::getName));
        puppies
                .stream()
                .map(PuppyDawg::getName)
                .forEach(System.out::println);
    }
}
