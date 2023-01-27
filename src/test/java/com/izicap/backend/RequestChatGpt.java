package com.izicap.backend;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestChatGpt {
    public static Request.Builder getRequest(){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"model\":\"text-davinci-003\",\"prompt\":\"salut\",\"max_tokens\":2048,\"temperature\":0}");

       return new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                //Ajout des en-têtes pour spécifier le type de contenu et l'autorisation
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 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")
                .build().newBuilder();

    }
}
