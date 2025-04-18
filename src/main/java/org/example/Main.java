package org.example;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Main {
    private static HttpRequest fetchUserData(String username, int numberOfEventsToDisplay)
    {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.github.com/users/" + username + "/events?per_page=" + numberOfEventsToDisplay))
                    .GET()
                    .build();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        return request;
    }
    public static void main(String[] args) {
        int numberOfEventsToDisplay = 6;
        for(int i = 0; i < args.length; i++)
        {
            if(args[i].equals("--limit"))
            {
                numberOfEventsToDisplay = Integer.parseInt(args[i+1]);
            }
        }
        Scanner scanner = new Scanner(System.in);
        String username;
        if (args.length > 0) {
            username = args[0];
        } else {
            System.out.println("No username entered. Please enter a valid GitHub username:");
            username = scanner.nextLine();
        }
        while (true) {
            try {
                try (   InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Logo.txt");
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Please wait while we fetch the data of the user...");
                HttpResponse<String> response = HttpClient.newBuilder()
                        .build()
                        .send(fetchUserData(username, numberOfEventsToDisplay), HttpResponse.BodyHandlers.ofString());
//                System.out.println(response.body());
                if (response.statusCode() == 404) {
                    System.out.println("No user found with given username: " + username + ". Please enter a valid GitHub username:");
                    username = scanner.nextLine();
                }
                else if (response.statusCode() == 200) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Event> eventList = objectMapper.readValue(response.body(), new TypeReference<List<Event>>() {});

                    System.out.println("Here is the summary of the last " + eventList.size() + " events done by " + username + " :");
                    for (Event event : eventList) {
                        switch (event.getType()) {
                            case "PushEvent":
                                System.out.println("Pushed " + event.getPayload().getCommits().size() + " commits to " + event.getRepo().getName());
                                break;
                            case "CreateEvent":
                                if (event.getPayload().getRef_type().equals("repository")) {
                                    System.out.println("Created a new repository called " + event.getRepo().getName());
                                } else if (event.getPayload().getRef().equals("branch")) {
                                    System.out.println("Created a new branch called " + event.getPayload().getRef());
                                }
                                break;
                            case "PublicEvent":
                                System.out.println("Made the repo " + event.getRepo().getName() + " public");
                                break;
                            case "PullRequestEvent":
                                System.out.println("Opened a pull request in " + " or " + "Merged a pull request in <repository_name>");
                                break;
                            case "PullRequestReviewEvent":
                                break;
                            case "IssuesEvent":
                                System.out.println(event.getPayload().getAction().toUpperCase() + event.getRepo().getName());
                                break;
                            case "IssueCommentEvent":
                                break;
                            case "DeleteEvent":
                                break;
                            case "CommentCommitEvent":
                                break;
                            case "WatchEvent":
                                System.out.println("Starred " + event.getRepo().getName());
                                break;
                            case "MemberEvent":
                                if (event.getPayload().getAction().equals("added")) {
                                    System.out.println("Added " + event.getPayload().getMember().getLogin() + " to " + event.getRepo().getName() + " as a " + event.getPayload().getMember().getType());
                                } else {
                                    System.out.println("Removed " + event.getPayload().getMember().getLogin() + " from " + event.getRepo().getName());
                                }
                                break;
                        }
                    }
//                    System.out.println(username + " has had " + rootNode.size() + " total events in the past 90 days");
                    break;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}