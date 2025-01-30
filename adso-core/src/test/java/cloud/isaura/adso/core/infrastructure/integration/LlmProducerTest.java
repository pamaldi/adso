package cloud.isaura.adso.core.infrastructure.integration;


import cloud.isaura.adso.core.infrastructure.configuration.Configuration;
import cloud.isaura.adso.core.domain.llm.LanguageModelCode;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @BeforeEach
    public void reset()
    {
        this.llmProducer.reset();
    }

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
    public void given_ollama_llm_when_producer_and_not_base_url_then_illegal_argument() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        assertThrows(IllegalArgumentException.class, () -> {
            llmProducer.produceLanguageModel();
        });
    }

    @Test
    public void given_ollama_llm_when_producer_and_not_model_name_then_illegal_argument() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(OLLAMA_URL);
        assertThrows(IllegalArgumentException.class, () -> {
            llmProducer.produceLanguageModel();
        });
    }

    @Test
    public void given_ollama_llm_when_producer_then_language_model_instance_of_OllamaLanguageModel() {
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

    @Test
    public void given_ollama_llm_when_producer_explicit_and_not_base_url_then_illegal_argument() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        assertThrows(IllegalArgumentException.class, () -> {
            llmProducer.produceLanguageModel(LanguageModelCode.OLLAMA);
        });
    }

    @Test
    public void given_ollama_llm_when_producer_explicit_then_language_model_instance_of_OllamaLanguageModel() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(OLLAMA_URL);
        Mockito.when(configuration.getOllamaModelName()).thenReturn(OLLAMA_MODEL_NAME);
        LanguageModel languageModel = llmProducer.produceLanguageModel(LanguageModelCode.OLLAMA);
        assertNotNull(languageModel, "language model must be not null");
        assertTrue(languageModel instanceof OllamaLanguageModel);
        languageModel = llmProducer.produceLanguageModel();
        assertNotNull(languageModel, "language model must be not null");
        assertTrue(languageModel instanceof OllamaLanguageModel);
    }

    @Test
    public void given_echo_llm_when_producer_explicit_then_language_model_instance_of_DoubleEchoLanguageModel() {

        LanguageModel languageModel = llmProducer.produceLanguageModel(LanguageModelCode.ECHO);
        assertNotNull(languageModel, "language model must be not null");
        assertTrue(languageModel instanceof DoubleEchoLanguageModel);

    }

}

