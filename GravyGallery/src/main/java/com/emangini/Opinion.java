package com.emangini;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private double stars;
    @NonNull
    private String comment;


    @Transient
    private boolean inCookies;

    public double getHalfRoundedStars() {
        return Math.round(stars / 0.5) * 0.5;
    }

    public boolean isInCookies() {
        return inCookies;
    }
}
