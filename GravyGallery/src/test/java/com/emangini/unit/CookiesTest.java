package com.emangini.unit;

import com.emangini.Opinion;
import com.emangini.CookiesService;
import com.emangini.Dog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit:WhiteBox: Handling Cookies")
@Tag("unit")
class CookiesTest {

    @InjectMocks
    CookiesService cookiesService;

    @Mock
    HttpServletResponse response;

    // Test Data
    Dog dummy = new Dog("NotHotDog", "nothotdog.jpg");
    String dummyCookie = "0#0.0#_";

    @Test
    @DisplayName("Update Cookies Test")
    void testUpdateCookies() {
        doNothing().when(response).addCookie(any(Cookie.class));
        String cookies = cookiesService.updateCookies("", 0L, 0D, "", response);
        assertThat(cookies, containsString(CookiesService.VALUE_SEPARATOR));
        assertThat(cookies, containsString(CookiesService.DOG_SEPARATOR));
    }

    @Test
    @DisplayName("Check Dog in Cookies")
    void testCheckDogInCookies() {
        boolean dogInCookies = cookiesService.isDogInCookies(dummy, dummyCookie);
        assertThat(dogInCookies, equalTo(true));
    }

    @Test
    @DisplayName("Check Dog in Empty Cookies")
    void testCheckDogInEmptyCookies() {
        boolean dogInCookies = cookiesService.isDogInCookies(dummy, "");
        assertThat(dogInCookies, equalTo(false));
    }

    @Test
    @DisplayName("Update Opinions With Cookies")
    void testUpdateOpinionsWithCookies() {
        List<Opinion> opinions = cookiesService.updateOpinionsWithCookiesValue(dummy, dummyCookie);
        assertThat(opinions, not(empty()));
    }

    @Test
    @DisplayName("Update Opinions With Empty Cookies")
    void testUpdateOpinionsWithEmptyCookies() {
        List<Opinion> opinions = cookiesService.updateOpinionsWithCookiesValue(dummy, "");
        assertThat(opinions, empty());
    }
}
