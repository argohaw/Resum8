package com.sriram.Resum8.controller;

import com.sriram.Resum8.service.ResumeAnalyzerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
public class ResumeAnalyzerController
{
    private final ResumeAnalyzerService resumeAnalyzerService;

    public ResumeAnalyzerController(ResumeAnalyzerService resumeAnalyzerService)
    {
        this.resumeAnalyzerService = resumeAnalyzerService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analysisResponse(@RequestParam String jobDesc, @RequestParam("resumePdf") MultipartFile resumePdf)
    {
        try
        {
            String result = resumeAnalyzerService.analyzeResume(jobDesc, resumePdf);
            return ResponseEntity.ok(result);

        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
