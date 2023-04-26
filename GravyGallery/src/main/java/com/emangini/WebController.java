package com.emangini;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

import static com.emangini.CookiesService.COOKIE_NAME;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebController {

    @NonNull
    private final DogService dogService;
    @NonNull
    private final CookiesService cookiesService;

    @GetMapping("/")
    public ModelAndView index(
            @CookieValue(value = COOKIE_NAME,defaultValue = "") String cookiesValue) {
        log.info("Cookies: {}", cookiesValue);

        ModelAndView model = new ModelAndView("index");
        List<Dog> allDogs = dogService.getAllDogs();
        model.addObject("dogs", cookiesService.filterDogListWithCookies(allDogs, cookiesValue));
        return model;
    }

    @PostMapping("/")
    public ModelAndView rate(@RequestParam Long dogId, @RequestParam(required = false) Double stars,
                             @RequestParam String comment,
                             @CookieValue(value = COOKIE_NAME, defaultValue = "") String cookiesValue,
                             HttpServletResponse response) {
        log.info("Received rating for picture {}: stars={} comment={}", dogId, stars, comment);

        ModelAndView model = new ModelAndView("index");
        String newCookiesValue = cookiesValue;
        try {
            if (stars == null) {
                model.addObject("errorMessage", "You need to select stars for rating dogs.");
            } else {
                Dog ratedDog = dogService.rateDog(stars, comment, dogId);
                String successMessage = String.format(Locale.US,
                        "Your rating for %s with %.1f stars and comment '%s' has been saved",
                        ratedDog.getName(), stars, comment);
                model.addObject("successMessage", successMessage);

                newCookiesValue = cookiesService.updateCookies(cookiesValue, dogId, stars, comment, response);
            }
        } catch (Exception e) {
            log.error("Exception occurred trying to rate picture: {}", e.getMessage());
            model.addObject("errorMessage", e.getMessage());
        } finally {
            List<Dog> allDogs = dogService.getAllDogs();
            model.addObject("dogs", cookiesService.filterDogListWithCookies(allDogs, newCookiesValue));
        }
        return model;
    }
}
