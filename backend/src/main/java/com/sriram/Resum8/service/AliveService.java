package com.sriram.Resum8.service;

import com.sriram.Resum8.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AliveService
{
    private final RestTemplate restTemplate;
    private final String ollamaUrl;

    @Autowired
    public AliveService(RestTemplate restTemplate, @Value("${spring.ai.ollama.base-url}") String url)
    {
        this.restTemplate = restTemplate;
        this.ollamaUrl = url;
    }

    public final String ALIVE = "alive";

    public final String ALIVE_MESSAGE = "Master Sriram, I am up and running with any issues";

    public String backendStatus()
    {
        return "{\"alive\": \"" + Constants.ALIVE + "\", \"message\": \"" + Constants.ALIVE_MESSAGE + "\"}";
    }

    public String ollamaStatus()
    {
        String url = ollamaUrl + "/api/tags";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try
        {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful())
            {
                return response.getBody();
            }
            else
            {
                System.out.println("Ollama responded with non-2xx status: " + response.getStatusCode());
                return "Ollama is reachable but returned status: " + response.getStatusCode();
            }
        }
        catch (RestClientException e)
        {
            System.out.println("Failed to connect to Ollama at " + url + ": " + e.getMessage());
            return "Failed to connect to Ollama: " + e.getMessage();
        }
    }
}
