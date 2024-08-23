package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

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
                System.out.println("Welcome to RepoWatch!");
                System.out.println("Please wait while we fetch the data of the user...");
                HttpResponse<String> response = HttpClient.newBuilder()
                        .build()
                        .send(fetchUserData(username, numberOfEventsToDisplay), HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 404) {
                    System.out.println("No user found with given username: " + username + ". Please enter a valid GitHub username:");
                    username = scanner.nextLine();
                }

//              System.out.println(response.body());
                else if (response.statusCode() == 200) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(response.body());
                    int pushEvents = 0;
                    int createEvents = 0;
                    int publicEvents = 0;
                    int pullEvents = 0;
                    int pullRequestReview = 0;
                    int openIssues = 0;
                    int commentIssues = 0;
                    int deleteBranch = 0;
                    int commentCommits = 0;
                    int starRepo = 0;
                    int memberEvent = 0;

                    System.out.println("Here is the summary of the last " + numberOfEventsToDisplay + " events done by " + username + " :");
                    for (JsonNode node : rootNode) {
                        switch (node.get("type").asText()) {
                            case "PushEvent":
                                pushEvents++;
                                System.out.println("Pushed " + node.get("payload").get("commits").size() + " commits to " + node.get("repo").get("name").asText());
                                break;
                            case "CreateEvent":
                                createEvents++;
                                if (node.get("payload").get("ref_type").asText().equals("repository")) {
                                    System.out.println("Created a new repository called " + node.get("repo").get("name").asText());
                                } else if (node.get("payload").get("ref_type").asText().equals("branch")) {
                                    System.out.println("Created a new branch called " + node.get("payload").get("ref").asText());
                                }
                                break;
                            case "PublicEvent":
                                publicEvents++;
                                System.out.println("Made the repo " + node.get("repo").get("name").asText() + " public");
                                break;
                            case "PullRequestEvent":
                                pullEvents++;
                                System.out.println("Opened a pull request in " + " or " + "Merged a pull request in <repository_name>");
                                break;
                            case "PullRequestReviewEvent":
                                pullRequestReview++;
                                break;
                            case "IssuesEvent":
                                openIssues++;
                                System.out.println(node.get("payload").get("action").asText().toUpperCase() + node.get("repo").get("name").asText());
                                break;
                            case "IssueCommentEvent":
                                commentIssues++;
                                break;
                            case "DeleteEvent":
                                deleteBranch++;
                                break;
                            case "CommentCommitEvent":
                                commentCommits++;
                                break;
                            case "WatchEvent":
                                starRepo++;
                                System.out.println("Starred " + node.get("repo").get("name").asText());
                                break;
                            case "MemberEvent":
                                memberEvent++;
                                if (node.get("payload").get("action").asText().equals("added")) {
                                    System.out.println("Added " + node.get("payload").get("member").get("login").asText() + " to " + node.get("repo").get("name").asText() + " as a " + node.get("payload").get("member").get("type").asText());
                                } else {
                                    System.out.println("Removed " + node.get("payload").get("member").get("login").asText() + " from " + node.get("repo").get("name").asText());
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