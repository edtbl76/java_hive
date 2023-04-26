package com.emangini;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DBPopulator {

    /*
        NOTE: THe code LOOKS cleaner if you put @Autowired here. (The constructor can be omitted.)

        However, Spring IoC recommendations are to prefer Constructor injection.
        - More information can be found here: https://blog.marcnuri.com/field-injection-is-not-recommended/

        The challenge is providing @Autowired w/ Lombok, which is solved in the @RequiredArgsConstructor annotation
        above.

        By using @NonNull, we mark this as a required dependency:
            - Spring recommends that required dependencies use constructor injection. Optional dependencies can
            be managed w/ setter injection.
     */
    @NonNull
    private final DogService dogService;


    @PostConstruct
    private void initDatabase() {
        if (dogService.getAllDogs().isEmpty()) {

            log.debug("Seeding database");

            Dog lazycouch = new Dog("LazyCouch", "lazycouch.jpg");
            List<Opinion> lazyCouchOpinions = new ArrayList<>();
            lazyCouchOpinions.add(new Opinion(5, "adorbs :)"));
            lazyCouchOpinions.add(new Opinion(3, "lazy dog!"));
            lazycouch.setOpinions(lazyCouchOpinions);
            dogService.saveDog(lazycouch);

            Dog upsidedown = new Dog("UpsideDown", "upsidedown.jpg");
            List<Opinion> upsideDownOpinions = new ArrayList<>();
            upsideDownOpinions.add(new Opinion(5, "Amazing!"));
            upsideDownOpinions.add(new Opinion(4.5F, "Wonderful Puppy"));
            upsideDownOpinions.add(new Opinion(4, "I like this one"));
            upsideDownOpinions.add(new Opinion(3, "creepy face : |"));
            upsidedown.setOpinions(upsideDownOpinions);
            dogService.saveDog(upsidedown);

            dogService.saveDog(new Dog("Snuggle", "snuggle.jpg"));
            dogService.saveDog(new Dog("SleepyTime", "sleepytime.jpg"));
            dogService.saveDog(new Dog("Happy", "happy.jpg"));
            dogService.saveDog(new Dog("Bed", "bed.jpg"));
            dogService.saveDog(new Dog("Sunbathing", "sunbathing.jpg"));
            dogService.saveDog(new Dog("Lovebug", "lovebug.jpg"));
            dogService.saveDog(new Dog("SquishyFace", "squishyface.jpg"));
            dogService.saveDog(new Dog("ChairTop", "chairtop.jpg"));
            dogService.saveDog(new Dog("SnugglingConnor", "connorsnuggles.jpg"));
            dogService.saveDog(new Dog("SnugglingDad", "dadsnuggles.jpg"));
            dogService.saveDog(new Dog("SnugglingMom", "mommysnuggles.jpg"));
            dogService.saveDog(new Dog("PeekABoo", "peekaboo.jpg"));
            dogService.saveDog(new Dog("Sit!", "sit.jpg"));
            dogService.saveDog(new Dog("SpoiledPuppy", "spoiledpuppy.jpg"));
            dogService.saveDog(new Dog("MorningStretch", "stretch.jpg"));


        }

        log.debug("Number of dogs in the database: {}", dogService.getDogCount());
    }
}
