package com.emangini.unit;

import com.emangini.DogRepository;
import com.emangini.DogService;
import com.emangini.Dog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit:BlackBox: Rating Dogs")
class RateDogsTest {

    @InjectMocks
    DogService dogService;

    @Mock
    DogRepository dogRepository;

    // Test Data
    Dog dummy = new Dog("dummy", "dummyDog.jpg");
    int stars = 5;
    String comment = "I'm a dummy";

    @ParameterizedTest(name = "Rating dog with {0} stars")
    @ValueSource(doubles = {0.5, 5})
    @DisplayName("Correct range of stars test")
    void testCorrectRangeOfStars(double stars) {
        Dog dummyDog = dogService.rateDog(stars, dummy);
        assertThat(dummyDog.getAverageRate(), equalTo(stars));
    }

    @Test
    @DisplayName("Rating dogs with a comment")
    void testRatingWithComments() {
        when(dogRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(dummy));
        Dog dummyDog = dogService.rateDog(stars, comment, 0 );
        assertThat(dogService.getOpinions(dummyDog).iterator().next().getComment(), equalTo(comment));
    }

    @Test
    @DisplayName("Rating dogs with an empty comment")
    void testRatingWithEmptyComments() {
        when(dogRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(dummy));
        Dog dummyDog = dogService.rateDog(stars, dummy);
        assertThat(dogService.getOpinions(dummyDog).iterator().next().getComment(), emptyString());
    }

}
