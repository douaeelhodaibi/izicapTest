package com.izicap.backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatGPTService {
    private final OkHttpClient httpClient = new OkHttpClient();

    public String getAnswer(String question) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        //Création du corps de la demande
        RequestBody body = RequestBody.create(mediaType, "{\"model\":\"text-davinci-003\",\"prompt\":\""+question+"\",\"max_tokens\":2048,\"temperature\":0}");

        //Création de la demande en utilisant la méthode POST
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                //Ajout des en-têtes pour spécifier le type de contenu et l'autorisation
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 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")
                .build();

        // Exécution de la demande et récupération de la réponse
        try (Response response = httpClient.newCall(request).execute()) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body().string());

            // Extraction de la réponse de la demande dans une variable
            String answer = json.get("choices").get(0).get("text").asText();

            // Retour de la réponse
            return answer;
        }
    }
}
