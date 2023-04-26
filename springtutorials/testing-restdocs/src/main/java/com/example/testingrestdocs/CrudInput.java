package com.example.testingrestdocs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@Getter
@Setter
public class CrudInput {

    @NotNull
    private long id;

    @NotBlank
    private String title;

    private String body;
    private List<URI> tagUris;

    @JsonCreator
    public CrudInput(
            @JsonProperty("id") long id,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body,
            @JsonProperty("tags") List<URI> tagUris) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.tagUris = tagUris;
    }


}
