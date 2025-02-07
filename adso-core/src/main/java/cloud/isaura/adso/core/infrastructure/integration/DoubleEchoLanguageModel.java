package cloud.isaura.adso.core.infrastructure.integration;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.output.Response;

import java.util.List;

public class DoubleEchoLanguageModel implements ChatLanguageModel
{

    @Override
    public String chat(String userMessage)
    {
        return userMessage+" an echo : "+userMessage;
    }

    @Override
    public Response<AiMessage> generate(List<ChatMessage> messages)
    {
        return null;
    }


}
