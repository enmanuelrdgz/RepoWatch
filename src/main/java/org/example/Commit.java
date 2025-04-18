package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Commit {
    private Author author;
    private String message;
    public Commit(@JsonProperty("author") Author author, @JsonProperty("message") String message) {
        this.author = author;
        this.message = message;
    }
}
