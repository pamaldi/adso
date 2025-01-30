package cloud.isaura.adso.core.infrastructure.integration;

import cloud.isaura.adso.core.domain.llm.LanguageModelCode;
import cloud.isaura.adso.core.infrastructure.configuration.Configuration;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.openai.OpenAiLanguageModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@Dependent
public class LlmProducer
{

    private LanguageModel languageModel;


    @Inject
    Configuration configuration;

    private LanguageModel getLanguageModel(String llm) {
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
                languageModel = OllamaLanguageModel.builder().modelName(configuration.getOllamaModelName()).baseUrl(configuration.getOllamaBaseUrl()).build();
                return languageModel;
            case LanguageModelCode.OPEN_AI:
                languageModel = OpenAiLanguageModel.builder().build();
                return languageModel;
            case LanguageModelCode.ECHO:
                languageModel = new DoubleEchoLanguageModel();
                return languageModel;
            default:
                throw new IllegalArgumentException("Unknown implementation class: " + llm);
        }
    }


    public LanguageModel produceLanguageModel() {
        if(languageModel != null)
        {
            return languageModel;
        }
        String llm = configuration.getLlm();
        return getLanguageModel(llm);
    }


    public LanguageModel produceLanguageModel(LanguageModelCode llmCode) {
        if(languageModel != null)
        {
            return languageModel;
        }
        return getLanguageModel(llmCode.getName());
    }

    protected void reset()
    {
        this.languageModel = null;
    }


}
