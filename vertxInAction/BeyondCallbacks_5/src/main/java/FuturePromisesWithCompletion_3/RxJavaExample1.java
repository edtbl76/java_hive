package FuturePromisesWithCompletion_3;

import io.reactivex.Observable;

import java.util.Objects;

public class RxJavaExample1 {

    public static void main(String[] args) {
        /*
            just() is great for examples/tests, but in real world code we need to make it more realistic

         */
        Observable.just(1, 2, 3)
                .map(Objects::toString)
                .map(s -> "@" + s)
                .subscribe(System.out::println);
    }

}
