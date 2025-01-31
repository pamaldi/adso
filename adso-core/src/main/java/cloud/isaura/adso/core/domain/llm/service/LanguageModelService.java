package cloud.isaura.adso.core.domain.llm.service;

import cloud.isaura.adso.core.domain.llm.LanguageModelDto;
import dev.langchain4j.model.ollama.OllamaModel;

import java.util.List;

public interface LanguageModelService
{
    List<OllamaModel> availableModels();
}
