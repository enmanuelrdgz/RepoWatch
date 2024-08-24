package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    public List<Commit> getCommits() {
        return commits;
    }
    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
    public RepoMember getMember() {
        return member;
    }
    public void setMember(RepoMember member) {
        this.member = member;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }
    public String getRef_type() {
        return ref_type;
    }
    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
    }
}
