package cloud.isaura.adso.core.infrastructure.integration;

import cloud.isaura.adso.core.infrastructure.configuration.Configuration;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@Dependent
public class LlmProducer
{

    @Inject
    Configuration configuration;
    private ChatModel languageModel;

    private ChatModel getLanguageModel(String llm)
    {
        if (llm == null || llm.trim().length() == 0) {
            throw new IllegalArgumentException("Not exisiting llm: ");
        }

        if (StringUtils.isEmpty(configuration.getOllamaBaseUrl()) || StringUtils.isEmpty(configuration.getOllamaModelName())) {
            throw new IllegalArgumentException("Specify base url and model name");
        }
        languageModel = OllamaChatModel.builder().modelName(configuration.getOllamaModelName()).baseUrl(configuration.getOllamaBaseUrl()).build();
        return languageModel;
    }

    public ChatModel produceLanguageModel()
    {
        if (languageModel != null) {
            return languageModel;
        }
        String llm = configuration.getOllamaModelName();
        return getLanguageModel(llm);
    }


    public ChatModel produceLanguageModel(String llmCode)
    {
        if (languageModel != null) {
            return languageModel;
        }
        return getLanguageModel(llmCode);
    }

    public ChatModel echo()
    {

        return new DoubleEchoLanguageModel();
    }

    protected void reset()
    {
        this.languageModel = null;
    }





}
