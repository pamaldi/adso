package cloud.isaura.adso.llm;

import cloud.isaura.adso.configurations.Configuration;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.openai.OpenAiLanguageModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
public class LlmProducer
{

    OllamaLanguageModel ollamaLanguageModel;

    OpenAiLanguageModel openAiLanguageModel;

    @Inject
    Configuration configuration;

    @Produces
    @Singleton
    public LanguageModel produceLanguageModel() {


        String llm = configuration.getLlm();
        if(llm == null || llm.trim().length()==0)
        {
            throw new IllegalArgumentException("Not exisiting llm: ");
        }


        switch (LanguageModelCode.fromName(llm)) {
            case LanguageModelCode.OLLAMA:
                if(StringUtils.isEmpty(configuration.getOllamaBaseUrl())|| StringUtils.isEmpty(configuration.getOllamaModelName()))
                {
                    throw new IllegalArgumentException("Specify base url and model name");
                }
                return OllamaLanguageModel.builder().modelName(configuration.getOllamaModelName()).baseUrl(configuration.getOllamaBaseUrl()).build();
            case LanguageModelCode.OPEN_AI:
                return OpenAiLanguageModel.builder().build();
            default:
                throw new IllegalArgumentException("Unknown implementation class: " + llm);
        }
    }


}
