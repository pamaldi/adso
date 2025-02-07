package cloud.isaura.adso.core.infrastructure.integration;

import cloud.isaura.adso.core.domain.llm.service.LanguageModelService;
import cloud.isaura.adso.core.infrastructure.configuration.Configuration;
import dev.langchain4j.model.ollama.OllamaModel;
import dev.langchain4j.model.ollama.OllamaModels;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;


import java.util.Set;
import java.util.stream.Collectors;

@Dependent
public class LanguageModelServiceImpl implements LanguageModelService
{
    @Inject
    private Configuration configuration;

    @Override
    public @NotNull Set<String> availableModels()
    {
        OllamaModels ollamaModels = OllamaModels.builder().baseUrl(configuration.getOllamaBaseUrl()).build();
        return ollamaModels.availableModels().content().stream().map(OllamaModel::getModel).collect(Collectors.toSet());

    }
}
