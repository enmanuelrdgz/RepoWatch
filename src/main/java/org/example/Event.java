package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
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
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Repo getRepo() {
        return repo;
    }
    public void setRepo(Repo repo) {
        this.repo = repo;
    }
    public Payload getPayload() {
        return payload;
    }
    public void setPayload(Payload payload) {
        this.payload = payload;
    }
    public void print(){
        System.out.println("id: " + id + " type: " + type);
        System.out.println("repo id: " + repo.getId());
        System.out.println("repo name: " + repo.getName());
        System.out.println(repetitions + " repetitions");
    }
}
