package org.example;

public class Event {
    int id;
    String type;
    Repo repo;
    Payload payload;
    static int repetitions;
    public Event(int id, String type, Repo repo, Payload payload) {
        this.id = id;
        this.type = type;
        this.repo = repo;
        this.payload = payload;
        repetitions++;
    }
}
