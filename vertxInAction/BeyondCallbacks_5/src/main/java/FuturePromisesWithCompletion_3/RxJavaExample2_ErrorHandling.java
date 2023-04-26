package FuturePromisesWithCompletion_3;

import io.reactivex.Observable;

public class RxJavaExample2_ErrorHandling {
    public static void main(String[] args) {
        Observable.<String>error(() -> new RuntimeException("Oops"))
                .map(String::toUpperCase) // this is never called :)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }
}
