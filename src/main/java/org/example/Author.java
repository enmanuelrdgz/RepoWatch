package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Author {
    private String email;
    private String name;
    public Author(@JsonProperty("email") String email,@JsonProperty("name")  String name) {
        this.email = email;
        this.name = name;
    }
}
