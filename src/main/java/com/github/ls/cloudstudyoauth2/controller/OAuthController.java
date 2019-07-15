package com.github.ls.cloudstudyoauth2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * github oauth接入授权
 */
@Controller
public class OAuthController {

  @Value("${github.clientId}")
  private String clientId;

  @Value("${github.clientSecret}")
  private String clientSecret;

  @GetMapping("/")
  public String toIndex(ModelMap modelMap) {
    modelMap.put("clientId", clientId);
    modelMap.put("clientSecret", clientSecret);
    return "login";
  }

  @GetMapping("/redirect")
  public String redirect(String code) throws IOException, InterruptedException {

    StringBuffer url = new StringBuffer();
    url.append("https://github.com/login/oauth/access_token?")
        .append("client_id=")
        .append(clientId)
        .append("&client_secret=")
        .append(clientSecret)
        .append("&code=")
        .append(code);

    HttpClient client = HttpClient.newBuilder().build();
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url.toString()))
            .header("accept", "application/json")
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
    return "index";
  }
}
