package cloud.isaura.adso.tools;

import cloud.isaura.adso.llm.LanguageModelCode;
import cloud.isaura.adso.llm.LlmProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LlmTool implements Dialogic
{

    @Inject
    LlmProducer llmProducer;


    @Override
    public String exchange(String request) {
        return llmProducer.produceLanguageModel().generate(request).content();
    }

    @Override
    public String exchange(String request, LanguageModelCode languageModelCode) {
        return llmProducer.produceLanguageModel(languageModelCode).generate(request).content();
    }


}
