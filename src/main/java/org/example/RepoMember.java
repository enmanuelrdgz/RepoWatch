package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RepoMember {
    private String login;
    private String type;
    public RepoMember(@JsonProperty("login") String login, @JsonProperty("type") String type) {
        this.login = login;
        this.type = type;
    }
}
