package cloud.isaura.adso.core.domain.llm.service;

import cloud.isaura.adso.core.domain.llm.LanguageModelDto;
import jakarta.enterprise.context.Dependent;

import java.util.List;

@Dependent
public class LanguageModelServiceImpl implements LanguageModelService
{
    @Override
    public List<LanguageModelDto> availableModels()
    {

        return List.of();
    }
}
