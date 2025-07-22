package com.sriram.Resum8.util;

public class Constants
{
    public static final String RESUMEPROMPT = """
            You are a professional career coach and resume reviewer.
            
            Given:
            - A job description
            - A candidate’s resume (as plain text)
            
            Your task is to:
            1. Evaluate how well the resume aligns with the job description.
            2. Identify **strengths**, **weaknesses**, and **improvements**.
            
            ### Output must be in strict JSON format:
            {
              "strengths": [ "..." ],
              "weaknesses": [ "..." ],
              "improvements": [ "..." ]
            }
            
            Job Description:
            %s
            
            Resume:
            %s
            """;

    public static final String ALIVE = "alive";

    public static final String ALIVE_MESSAGE = "Master Sriram, I am up and running without any issues";

    private Constants()
    {
        throw new IllegalStateException("Constants Class");
    }
}
