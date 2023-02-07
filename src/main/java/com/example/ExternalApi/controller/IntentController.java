package com.example.ExternalApi.controller;

import com.example.ExternalApi.service.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@RestController
public class IntentController {

    @Autowired
    IntentService intentService;
    @GetMapping(value = "/intentId")
    private List<String>getIntent(){
        return intentService.getIntent();
    }

    @GetMapping(value = "/variantId")

    private List<String>getVarient(){
        List<String> Id= intentService.getIntent();
        List<String>result= new ArrayList<>();
        for(String id:Id){
            result.add(intentService.getVariantId(id).toString());

        }
       // System.out.println(result.size());
        return result;
    }




    @GetMapping(value = "/flow")

    private List<String> getFlow(){
        List<String> Id= intentService.getIntent();
        List<String> flow= new ArrayList<>();
        for(String intentId:Id){
            Map<String,String > varientResponse= intentService.getVariantId(intentId);

           for(Map.Entry<String,String> entry:varientResponse.entrySet()){
               flow.add(intentService.flow(entry.getKey(),entry.getValue()).toString());

               Path path = Paths.get("/Users/shrinepro1/Downloads/ExternalApi/src/main/resources/JsonFile/"+intentId+"----"+entry.getKey()+".json");
               String str= flow.toString();
               try {

                   Files.writeString(path, str, StandardCharsets.UTF_8);
               }

               // Catch block to handle the exception
               catch (IOException ex) {

                   System.out.print("Invalid Path");
               }
           }

        }
        System.out.println(flow);
        return flow;
    }




}
