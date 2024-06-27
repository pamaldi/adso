package cloud.isaura.adso.llm;

import cloud.isaura.adso.tools.Dialogic;
import dev.langchain4j.model.language.LanguageModel;
import jakarta.inject.Inject;

public class LLM implements Dialogic
{

    @Inject
    private LanguageModel languageModel;

    public LLM()
    {

    }

}
