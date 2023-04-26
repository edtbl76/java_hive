package com.emangini;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Dog {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String pictureFileName;

    @Transient
    private boolean inCookies;

    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Opinion> opinions = new ArrayList<>();

    public void rate(double stars, String comment) {
        opinions.add(new Opinion(stars, comment));
    }

    public String getAverageRateAsString() {
        double averageRate = getAverageRate();
        return averageRate > 0 ? String.format(Locale.US, "%.2f", averageRate) : "Not Rated";
    }

    public double getAverageRate() {
        double totalStars;
        totalStars = opinions.stream().mapToDouble(Opinion::getStars).sum();

        double average = 0;
        if (!opinions.isEmpty()) {
            average = totalStars / opinions.size();
        }
        return average;
    }

    public double getHalfRoundedAverageRate() {
        return Math.round(getAverageRate() / 0.5) * 0.5;
    }

    public int getOpinionsSize() {
        int opinionsSize = opinions.size();
        if (isInCookies()) {
           opinionsSize--;
        }
        return opinionsSize;
    }

    public double getStarsInCookies() {
        Optional<Opinion> opinionsInCookies = getOpinionsInCookies();
        return opinionsInCookies
                .map(Opinion::getHalfRoundedStars)
                .orElse(0.0);
    }

    public String getCommentInCookies() {
        Optional<Opinion> opinionsInCookies = getOpinionsInCookies();
        return opinionsInCookies
                .map(Opinion::getComment)
                .orElse("");
    }

    public Optional<Opinion> getOpinionsInCookies() {
        for (Opinion opinion : opinions) {
            if (opinion.isInCookies()) {
                return Optional.of(opinion);
            }
        }
        return Optional.empty();
    }

    public boolean isInCookies() {
        return inCookies;
    }

}
