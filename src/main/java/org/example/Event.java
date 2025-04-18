package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Event {
    private long id;
    private String type;
    private Repo repo;
    private Payload payload;
    @JsonIgnore
    static int repetitions;
    public Event(@JsonProperty("id") long id, @JsonProperty("type") String type, @JsonProperty("repo") Repo repo, @JsonProperty("payload") Payload payload) {
        this.id = id;
        this.type = type;
        this.repo = repo;
        this.payload = payload;
        repetitions++;
    }
}
