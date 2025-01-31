package cloud.isaura.adso.core.domain.llm.service;

import cloud.isaura.adso.core.domain.llm.LanguageModelDto;

import java.util.List;

public interface LanguageModelService
{
    List<LanguageModelDto> availableModels();
}
