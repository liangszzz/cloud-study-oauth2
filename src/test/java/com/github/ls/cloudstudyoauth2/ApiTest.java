package com.github.ls.cloudstudyoauth2;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest {

    @Test
    public void t1() throws IOException, InterruptedException {
        String auth = "f13b83edd9bf1ab61b60a3f5156b3dda40b09edc";
        StringBuffer url = new StringBuffer("https://api.github.com/user");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url.toString()))
                        .header("Authorization", "token " + auth)
                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }
}
