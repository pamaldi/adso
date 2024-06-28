package cloud.isaura.adso.llm;


import cloud.isaura.adso.configurations.Configuration;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class LlmProducerTest
{
    @Inject
    LlmProducer llmProducer;

    @InjectMock
    Configuration configuration;

    public static final String OLLAMA = "ollama";
    public static final String OLLAMA_URL = "http://localhost:11434";
    public static final String OLLAMA_MODEL_NAME = "llama3";

    @Test
    public void testLlmProducerExists() {
        assertNotNull(llmProducer, "The LLM Producer should be injected");
    }

    @Test
    public void given_a_not_existing_llm_code_when_producer_then_exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            llmProducer.produceLanguageModel();
        });
    }

    @Test
    public void given_ollama_llm_when_producer_and_not_base_url_then_null_pointer() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        assertThrows(IllegalArgumentException.class, () -> {
            llmProducer.produceLanguageModel();
        });
    }

    @Test
    public void given_ollama_llm_when_producer_and_not_model_name_then_null_pointer() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(OLLAMA_URL);
        assertThrows(IllegalArgumentException.class, () -> {
            llmProducer.produceLanguageModel();
        });
    }

    @Test
    public void given_ollama_llm_when_producer_then_ok() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(OLLAMA_URL);
        Mockito.when(configuration.getOllamaModelName()).thenReturn(OLLAMA_MODEL_NAME);
        LanguageModel languageModel = llmProducer.produceLanguageModel();
        assertNotNull(languageModel, "language model must be not null");
        assertTrue(languageModel instanceof OllamaLanguageModel);
        languageModel = llmProducer.produceLanguageModel();
        assertNotNull(languageModel, "language model must be not null");
        assertTrue(languageModel instanceof OllamaLanguageModel);


    }
}

