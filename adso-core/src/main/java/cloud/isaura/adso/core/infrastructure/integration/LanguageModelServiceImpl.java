package cloud.isaura.adso.core.infrastructure.integration;

import cloud.isaura.adso.core.domain.llm.LanguageModelDto;
import cloud.isaura.adso.core.domain.llm.service.LanguageModelService;
import cloud.isaura.adso.core.infrastructure.configuration.Configuration;

import dev.langchain4j.model.ollama.OllamaModel;
import dev.langchain4j.model.ollama.OllamaModels;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.List;

@Dependent
public class LanguageModelServiceImpl implements LanguageModelService
{
    @Inject
    private Configuration configuration;

    @Override
    public List<OllamaModel> availableModels()
    {
        OllamaModels ollamaModels = OllamaModels.builder().baseUrl(configuration.getOllamaBaseUrl()).build();
        return ollamaModels.availableModels().content();
    }
}
