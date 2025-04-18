package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Payload {
    private List<Commit> commits;
    private RepoMember member;
    private String action;
    private String ref;
    private String ref_type;
    public Payload(@JsonProperty("commits") List<Commit> commits, @JsonProperty("member") RepoMember member, @JsonProperty("action") String action, @JsonProperty("ref") String ref, @JsonProperty("ref_type") String ref_type) {
        this.member = member;
        this.action = action;
        this.ref = ref;
        this.ref_type = ref_type;
        this.commits = commits;
    }
}
