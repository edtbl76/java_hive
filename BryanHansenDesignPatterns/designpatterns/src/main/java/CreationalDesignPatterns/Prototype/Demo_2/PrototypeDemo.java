package CreationalDesignPatterns.Prototype.Demo_2;

public class PrototypeDemo {

    public static void main(String[] args) {
        PrototypeRegistry registry = new PrototypeRegistry();
        Movie movie = (Movie)registry.createItem("Movie");
        movie.setTitle("Creational Patterns in Java");

        System.out.println(movie);
        System.out.println(movie.getMovieLength());
        System.out.println(movie.getTitle());
        System.out.println(movie.getUrl());

        Movie anotherMovie = (Movie)registry.createItem("Movie");
        anotherMovie.setTitle("The Gang Of Four");

        System.out.println(anotherMovie);
        System.out.println(anotherMovie.getMovieLength());
        System.out.println(anotherMovie.getTitle());
        System.out.println(anotherMovie.getUrl());


        /*
            We'll get two distinctly set up instances using the prototype pattern.

         */

    }
}
