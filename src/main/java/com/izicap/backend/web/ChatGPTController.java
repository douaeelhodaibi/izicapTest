package com.izicap.backend.web;

import com.izicap.backend.services.ChatGPTService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ChatGPT")
public class ChatGPTController {
    // Injection de dépendance de ChatGPTService
    @Autowired
    private ChatGPTService chatGptService;

    // Gestion de la demande GET pour l'URL "/api/ChatGPT/answer"
    @GetMapping("/answer")
    public String getAnswer(@RequestParam String question) throws IOException {
        // Utilisation de ChatGPTService pour obtenir une réponse à la question
        String answer=chatGptService.getAnswer(question);
        try {
            // Create a new CSVWriter object
            CSVWriter writer = new CSVWriter(new FileWriter("questions_answers.csv",true));

        // Define the data to be written to the CSV file
        List<String[]> data = Arrays.asList(
                new String[]{"question",question},
                new String[]{"answer",answer}
                );

        // Write the data to the CSV file
        writer.writeAll(data);

        // Close the writer
        writer.close();

        System.out.println("Data written to CSV file successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }

        return answer;
    }
}
