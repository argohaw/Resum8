package com.sriram.Resum8.controller;

import com.sriram.Resum8.service.AliveService;
import com.sriram.Resum8.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AliveController
{
    private AliveService aliveService;

    public AliveController(AliveService aliveService)
    {
        this.aliveService = aliveService;
    }

    @RequestMapping("/alive")
    public ResponseEntity<String> alive()
    {
        System.out.println("Inside alive controller");
        String result = aliveService.backendStatus();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/ollama-alive")
    public ResponseEntity<String> ollamaAlive()
    {
        String status = aliveService.ollamaStatus();

        if (status == null || status.startsWith("Failed to connect"))
        {
            return ResponseEntity.status(503).body(status);
        }

        return ResponseEntity.ok(status);
    }
}