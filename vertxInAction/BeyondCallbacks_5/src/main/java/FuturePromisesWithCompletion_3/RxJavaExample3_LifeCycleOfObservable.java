package FuturePromisesWithCompletion_3;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class RxJavaExample3_LifeCycleOfObservable {

    public static void main(String[] args) {

        Observable
                .just("--", "this", "is", "--", "a", "sequence", "of", "items", "|")
                .doOnSubscribe(d -> System.out.println("Subscribed!"))
          //      .delay(5, TimeUnit.SECONDS)
                .filter(s -> !s.startsWith("--"))
                .doOnNext(System.out::println)
                .map(String::toUpperCase)
                .buffer(2)
                .subscribe(
                        System.out::println,                        //  on success
                        Throwable::printStackTrace,                 //  on failure
                        () -> System.out.println(">>> DONE"));    //  on complete
    }
}
