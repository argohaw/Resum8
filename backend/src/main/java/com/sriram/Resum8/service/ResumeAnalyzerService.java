package com.sriram.Resum8.service;

import com.sriram.Resum8.util.CommonUtil;
import com.sriram.Resum8.util.Constants;
import org.apache.tika.exception.TikaException;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeAnalyzerService
{
    private final OllamaChatModel chatModel;
    private final String model;

    @Autowired
    public ResumeAnalyzerService(OllamaChatModel chatModel, @Value("${spring.ai.ollama.model}") String model)
    {
        this.chatModel = chatModel;
        this.model = model;
    }

    public String analyzeResume(String jobDesc, MultipartFile resumePdf) throws TikaException, IOException
    {
        try
        {
            String resumeText = CommonUtil.extractTextFromPdf(resumePdf);
            String prompt = buildPrompt(jobDesc, resumeText);
            //String prompt = "Provide one strength, weakness and improvement of lewis in JSON format";

            Prompt aiPrompt = new Prompt(prompt, OllamaOptions.builder().model(model).temperature(0.6).build());

            ChatResponse response = chatModel.call(aiPrompt);

            return cleanJsonResponse(response.getResult().getOutput().getText());
        }
        finally
        {
            System.out.println("Running Finally");
        }
    }

    private String buildPrompt(String jobDesc, String resumeText)
    {
        return Constants.RESUMEPROMPT.formatted(jobDesc, resumeText);
    }

    private String cleanJsonResponse(String jsonResponse)
    {
        if (jsonResponse.startsWith("```json")) {
            jsonResponse = jsonResponse.replaceFirst("(?s)```json\\s*", ""); // remove ```json\n
        }
        if (jsonResponse.endsWith("```\n")) {
            jsonResponse = jsonResponse.replaceAll("```$", ""); // remove ending ```
        }
        jsonResponse = jsonResponse.trim();

        System.out.println("Cleaned JSON from Ollama:\n" + jsonResponse);

        return jsonResponse;

    }
}
