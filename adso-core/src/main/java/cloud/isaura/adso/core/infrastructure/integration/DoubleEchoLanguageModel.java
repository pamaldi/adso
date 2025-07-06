package cloud.isaura.adso.core.infrastructure.integration;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.output.Response;

import java.util.List;

public class DoubleEchoLanguageModel implements ChatModel
{

    @Override
    public String chat(String userMessage)
    {
        return userMessage+" an echo : "+userMessage;
    }




}
