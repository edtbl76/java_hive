package com.emangini;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CookiesService {

    public static final String COOKIE_NAME = "dogList";
    public static final String VALUE_SEPARATOR = "#";
    public static final String DOG_SEPARATOR = "_";

    public String updateCookies(String cookieValue, Long dogId, Double stars,
                                String comment, HttpServletResponse response) {

        String newCookieValue = cookieValue + dogId + VALUE_SEPARATOR + stars +
                VALUE_SEPARATOR +
                Base64.getEncoder().encodeToString(comment.getBytes(StandardCharsets.UTF_8)) +
                DOG_SEPARATOR;

        log.debug("Adding cookie {}={}", COOKIE_NAME, newCookieValue);
        response.addCookie(new Cookie(COOKIE_NAME, newCookieValue));

        return newCookieValue;
    }

    public boolean isDogInCookies(Dog dog, String cookieValue) {
        String[] dogs = cookieValue.split(DOG_SEPARATOR);
        for (String s : dogs) {
            if (s.equals("")) {
                continue;
            }
            if (dog.getId() == Long.parseLong(s.split(VALUE_SEPARATOR)[0])) {
                return true;
            }
        }
        return false;
    }

    public List<Opinion> updateOpinionsWithCookiesValue(Dog dog, String cookieValue) {
        List<Opinion> outputOpinionList = new ArrayList<>();
        Optional<String> cookieValueForDog = getValueForDog(dog, cookieValue);

        if (cookieValueForDog.isPresent()) {
            String[] split = cookieValueForDog.get().split(VALUE_SEPARATOR);
            double stars = Double.parseDouble(split[1]);
            String comment = split.length > 2 ? new String(Base64.getDecoder().decode(split[2])) : "";


            boolean opinionInCookies = false;
            for (Opinion opinion : dog.getOpinions()) {
                opinionInCookies = isOpinionInCookies(opinion, stars, comment);
                opinion.setInCookies(opinionInCookies);
                outputOpinionList.add(opinion);
            }

            if(!opinionInCookies) {
                Opinion opinion = new Opinion(stars, comment);
                opinion.setInCookies(true);
                outputOpinionList.add(opinion);
            }
        }
        return outputOpinionList;
    }

    public boolean isOpinionInCookies(Opinion opinion, double stars, String comment) {
          return opinion.getStars() == stars && opinion.getComment().equals(comment);
    }


    private Optional<String> getValueForDog(Dog dog, String cookieValue) {
        String[] dogs = cookieValue.split(DOG_SEPARATOR);
        for (String s : dogs) {
            if (s.equals("")) {
                continue;
            }
            if (dog.getId() == Long.parseLong(s.split(VALUE_SEPARATOR)[0])) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public List<Dog> filterDogListWithCookies(Iterable<Dog> allDogs, String cookieValue) {
        List<Dog> filteredDogs = new ArrayList<>();
        allDogs.forEach(dog -> {
            boolean dogInCookies = isDogInCookies(dog, cookieValue);
            dog.setInCookies(dogInCookies);

            if (dogInCookies) {
                dog.setOpinions(updateOpinionsWithCookiesValue(dog, cookieValue));
            }

            filteredDogs.add(dog);
            log.trace("Dog: {}", dog);
        });
        return filteredDogs;
    }

}


