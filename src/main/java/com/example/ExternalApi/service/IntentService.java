package com.example.ExternalApi.service;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IntentService {
    @Autowired
    public List<String> getIntent() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("cookie", "_hjMinimizedPolls=761880; ajs_user_id=jay.patel@netomi.com; ajs_anonymous_id=484a4663-e004-4a11-a27d-625d1701cdd8; _hjSessionUser_2633221=eyJpZCI6IjljNzE4ZTgzLTY1MTMtNTNjZC05ZjZmLTliMWUzZDhhMTBiYyIsImNyZWF0ZWQiOjE2NzM5NTI4NzE2NDEsImV4aXN0aW5nIjp0cnVlfQ==; _hjHasCachedUserAttributes=true; access-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVlYWI2MTEwLTI4YmEtMTFlYi1hYTU0LTliMzVkOWZjYzY3NSIsImVtYWlsIjoiamF5LnBhdGVsQG5ldG9taS5jb20iLCJ0eXBlIjoiTk9OX1NBTEVTIiwiZmlyc3RfbmFtZSI6IkpheSIsImxhc3RfbmFtZSI6IlBhdGVsIiwiaWF0IjoxNjc0MTMwMzcyLCJleHAiOjE2NzQyMTY3NzJ9.6JUws7c81x4dD4xZDEgDLP70iShh7GHsEhzTZgv1ysY; _hjIncludedInSessionSample=0; _hjSession_2633221=eyJpZCI6ImViMDc0YjIxLTNhZTktNDk2My1iMjFkLTg4MTBjMTFiMjJmNSIsImNyZWF0ZWQiOjE2NzQxMzAzNzU5OTIsImluU2FtcGxlIjpmYWxzZX0=; _hjIncludedInPageviewSample=1; _hjAbsoluteSessionInProgress=0; ADRUM=s=1674130393716&r=https%3A%2F%2Fstudio.netomi.com%2Ftraining%2Ftopics%3F0");
        headers.set("x-bot-id", "e97a393b-6103-4be0-af6c-ff191121171b");
        headers.set("x-channel", "ZENDESK_CHAT");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        String url = "https://studio.netomi.com/api/resources/intents";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        try {

            // System.out.println(response);
            System.out.println("Successfully intentId");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.toString() + "  hariom");
        }

        // return  response;
        JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        List<JsonElement> payload = jsonObject.get("payload").getAsJsonArray().asList();
        //System.out.println(payload);
        List<String> intentIds = payload.parallelStream().map(e -> e.getAsJsonObject().get("id").getAsString()).collect(Collectors.toList());
        // System.out.println(intentIds);

        return intentIds;

    }


    public Map<String,String> getVariantId(String intentID) {

        //  List<String> intentIds = getIntent();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("cookie", "_hjMinimizedPolls=761880; ajs_user_id=jay.patel@netomi.com; ajs_anonymous_id=484a4663-e004-4a11-a27d-625d1701cdd8; _hjSessionUser_2633221=eyJpZCI6IjljNzE4ZTgzLTY1MTMtNTNjZC05ZjZmLTliMWUzZDhhMTBiYyIsImNyZWF0ZWQiOjE2NzM5NTI4NzE2NDEsImV4aXN0aW5nIjp0cnVlfQ==; _hjHasCachedUserAttributes=true; access-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVlYWI2MTEwLTI4YmEtMTFlYi1hYTU0LTliMzVkOWZjYzY3NSIsImVtYWlsIjoiamF5LnBhdGVsQG5ldG9taS5jb20iLCJ0eXBlIjoiTk9OX1NBTEVTIiwiZmlyc3RfbmFtZSI6IkpheSIsImxhc3RfbmFtZSI6IlBhdGVsIiwiaWF0IjoxNjc0MTMwMzcyLCJleHAiOjE2NzQyMTY3NzJ9.6JUws7c81x4dD4xZDEgDLP70iShh7GHsEhzTZgv1ysY; _hjIncludedInSessionSample=0; _hjSession_2633221=eyJpZCI6ImViMDc0YjIxLTNhZTktNDk2My1iMjFkLTg4MTBjMTFiMjJmNSIsImNyZWF0ZWQiOjE2NzQxMzAzNzU5OTIsImluU2FtcGxlIjpmYWxzZX0=; _hjIncludedInPageviewSample=1; _hjAbsoluteSessionInProgress=0; ADRUM=s=1674130393716&r=https%3A%2F%2Fstudio.netomi.com%2Ftraining%2Ftopics%3F0");
        headers.set("x-bot-id", "e97a393b-6103-4be0-af6c-ff191121171b");
        headers.set("x-channel", "ZENDESK_CHAT");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        String url = "https://studio.netomi.com/api/resources/intents/template/";
        System.out.println(url+intentID);


        // System.out.println(ID);
        ResponseEntity<String> response = restTemplate.exchange(url + intentID, HttpMethod.GET, httpEntity, String.class);

        try {

            System.out.println("Successfully variantIds");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.toString() + "  hariom");
        }

        // return  response;
        JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        List<JsonElement> responses = jsonObject.get("payload").getAsJsonObject().get("intent").getAsJsonObject().get("responses").getAsJsonArray().asList();
        // we go line by line to that particular item which we want to get.
        Map<String, String> variantIds = responses.stream().filter(e -> e.getAsJsonObject().get("language").getAsString().equalsIgnoreCase("en")).collect(Collectors.toMap(e -> e.getAsJsonObject().get("variantId").getAsString(), e -> e.getAsJsonObject().get("responseType").getAsString()));



        return variantIds;

//

    }


    public List<String> flow(String varientId, String responsetype) {


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("cookie", "_hjMinimizedPolls=761880; ajs_user_id=jay.patel@netomi.com; ajs_anonymous_id=484a4663-e004-4a11-a27d-625d1701cdd8; _hjSessionUser_2633221=eyJpZCI6IjljNzE4ZTgzLTY1MTMtNTNjZC05ZjZmLTliMWUzZDhhMTBiYyIsImNyZWF0ZWQiOjE2NzM5NTI4NzE2NDEsImV4aXN0aW5nIjp0cnVlfQ==; _hjHasCachedUserAttributes=true; access-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVlYWI2MTEwLTI4YmEtMTFlYi1hYTU0LTliMzVkOWZjYzY3NSIsImVtYWlsIjoiamF5LnBhdGVsQG5ldG9taS5jb20iLCJ0eXBlIjoiTk9OX1NBTEVTIiwiZmlyc3RfbmFtZSI6IkpheSIsImxhc3RfbmFtZSI6IlBhdGVsIiwiaWF0IjoxNjc0MTMwMzcyLCJleHAiOjE2NzQyMTY3NzJ9.6JUws7c81x4dD4xZDEgDLP70iShh7GHsEhzTZgv1ysY; _hjIncludedInSessionSample=0; _hjSession_2633221=eyJpZCI6ImViMDc0YjIxLTNhZTktNDk2My1iMjFkLTg4MTBjMTFiMjJmNSIsImNyZWF0ZWQiOjE2NzQxMzAzNzU5OTIsImluU2FtcGxlIjpmYWxzZX0=; _hjIncludedInPageviewSample=1; _hjAbsoluteSessionInProgress=0; ADRUM=s=1674130393716&r=https%3A%2F%2Fstudio.netomi.com%2Ftraining%2Ftopics%3F0");
        headers.set("x-bot-id", "e97a393b-6103-4be0-af6c-ff191121171b");
//        headers.set("x-channel", "ZENDESK_CHAT");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        String url = "https://studio.netomi.com/api/resources/intent-response-variant/";


        // System.out.println(varientId);
       // System.out.println(url + varientId.toString() + "?responseType=" + responsetype);
        ResponseEntity<String> response = restTemplate.exchange(url + varientId + "?responseType=" + responsetype, HttpMethod.GET, httpEntity, String.class);

        try {

            System.out.println("Successfully flow");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.toString() + "  hariom");
        }


        JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        List<JsonElement> payload = jsonObject.get("payload").getAsJsonArray().asList();
        //System.out.println(payload);
        List<String> flow = payload.parallelStream().map(e -> e.getAsJsonObject().get("response").getAsJsonObject().toString()).collect(Collectors.toList());

        System.out.println(flow);
        return flow;

    }

}
