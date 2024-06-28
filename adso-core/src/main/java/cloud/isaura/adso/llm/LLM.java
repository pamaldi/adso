package cloud.isaura.adso.llm;

import cloud.isaura.adso.tools.Dialogic;
import dev.langchain4j.model.language.LanguageModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LLM implements Dialogic
{

    @Inject
    private LanguageModel languageModel;

    @Inject
    private LlmProducer llmProducer;

    public LLM()
    {

    }


    @Override
    public String exchange(String request) {
        this.languageModel=llmProducer.produceLanguageModel();
        return "";
    }
}
