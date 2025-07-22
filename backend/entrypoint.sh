#!/usr/bin/env bash
# Run Ollama model in background
ollama serve --model gemma:7b --host 0.0.0.0:11434 &
# Start Spring Boot app
java -jar /app/app.jar
