package com.izicap.backend;
import static org.junit.jupiter.api.Assertions.*;

import com.izicap.backend.services.ChatGPTService;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.io.IOException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ChatGPTServiceTest {
    @Value("${server.port})")
    private String PORT;

    @Value("salut")
    private String question;
    private String EXPECTED_PORT="8085";

    private  String EXPECTED_ANSWER="\n\nSalut! Comment allez-vous?";

    @InjectMocks
    private ChatGPTService chatGPTService;

    @Mock
    private OkHttpClient httpClient = new OkHttpClient();

    @Test
    public void PortTest(){
        assertTrue(PORT.contains(EXPECTED_PORT));
    }
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void MessageReturnTest() throws IOException{
        String expectedAnswer = "This is the expected answer.";
        String jsonResponse = "{\"choices\": [{\"text\": \"" + expectedAnswer + "\"}]}";

        Response response = new Response.Builder()
                .request(RequestChatGpt.getRequest().build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(MediaType.parse("application/json"), "{\"choices\": [{\"text\": \"\n\nSalut! Comment allez-vous?\"}]}"))
                .build();

        when(httpClient.newCall(any(Request.class)).execute()).thenReturn(response);

        assertTrue(expectedAnswer.contains(jsonResponse));
    }

}
