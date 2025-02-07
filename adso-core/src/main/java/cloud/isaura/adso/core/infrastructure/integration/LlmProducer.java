package cloud.isaura.adso.core.infrastructure.integration;

import cloud.isaura.adso.core.infrastructure.configuration.Configuration;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@Dependent
public class LlmProducer
{

    @Inject
    Configuration configuration;
    private ChatLanguageModel languageModel;

    private ChatLanguageModel getLanguageModel(String llm)
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

    public ChatLanguageModel produceLanguageModel()
    {
        if (languageModel != null) {
            return languageModel;
        }
        String llm = configuration.getOllamaModelName();
        return getLanguageModel(llm);
    }


    public ChatLanguageModel produceLanguageModel(String llmCode)
    {
        if (languageModel != null) {
            return languageModel;
        }
        return getLanguageModel(llmCode);
    }

    public ChatLanguageModel echo()
    {

        return new DoubleEchoLanguageModel();
    }

    protected void reset()
    {
        this.languageModel = null;
    }





}
