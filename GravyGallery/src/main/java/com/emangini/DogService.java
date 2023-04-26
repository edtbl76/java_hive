package com.emangini;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DogService {

    private static final double MIN_STARS = 0.5;
    private static final double MAX_STARS = 5;

    @NonNull
    private DogRepository dogRepository;

    public Dog saveDog(Dog dog) {
        return dogRepository.saveAndFlush(dog);
    }

    public Dog rateDog(double stars, String comment, long dogId) {
        Optional<Dog> optionalDog = dogRepository.findById(dogId);
        if(optionalDog.isEmpty()) {
            throw new DogException("Dog with id " + dogId + " not found.");
        }
        rateDog(stars, comment, optionalDog.get());
        return optionalDog.get();
    }

    public Dog rateDog(double stars, Dog dog) {
        return rateDog(stars, "", dog);
    }

    public Dog rateDog(double stars, String comment, Dog dog) {
        log.debug("Rating picture with {} stars and comment : \"{}\"", stars, comment);

        if (stars < MIN_STARS) {
            throw new DogException("The minimum number of stars is " + MIN_STARS);
        }

        if (stars > MAX_STARS) {
            throw new DogException("The maximum number of stars is " + MAX_STARS);
        }

        dog.rate(stars, comment);
        saveDog(dog);
        log.debug("Doginfo after rating: {}", dog);

        return dog;
    }

    public long getDogCount() {
        return dogRepository.findAll().spliterator().getExactSizeIfKnown();
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public List<Opinion> getOpinions(Dog dog) {
        Optional<Dog> optionalDog = dogRepository.findById(dog.getId());
        if (optionalDog.isEmpty()) {
            throw new DogException("Picture not available: " + dog);
        }
        return optionalDog.get().getOpinions();
    }

}
