import java.util.List;

public class varAllowedUsages {

    public static void main(String[] args) {

        // local primitive
        var name = "Ed Mangini";
        System.out.println(name);

        // enhanced for
        List<Integer> ints = List.of(1,2, 3, 4, 5);
        for ( var i : ints)
            System.out.println(i);

        // vintage for
        for (var i = 0; i < ints.size(); i++)
            System.out.println(ints.get(i));


    }
}
