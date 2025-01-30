package cloud.isaura.adso.core.infrastructure.configuration;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class OllamaConfigurationTest
{
    public static final String OLLAMA = "ollama";
    public static final String OLLAMA_URL = "http://localhost:11434";
    public static final String OLLAMA_MODEL_NAME = "llama3";
    @InjectMock
    Configuration configuration;

    @BeforeEach
    public void setup() {
        Mockito.when(configuration.getLlm()).thenReturn(OLLAMA);
    }

    @Test
    public void given_ollama_llm_when_build_configuration_then_base_url_and_model_name_not_null()
    {
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(OLLAMA_URL);
        Mockito.when(configuration.getOllamaModelName()).thenReturn(OLLAMA_MODEL_NAME);
        assertEquals(OLLAMA, configuration.getLlm());
        assertEquals(OLLAMA_URL, configuration.getOllamaBaseUrl());
        assertEquals(OLLAMA_MODEL_NAME, configuration.getOllamaModelName());
    }

    @Test
    public void given_llm_different_from_ollama_and_other_null_when_build_configuration_then_ok()
    {
        Mockito.when(configuration.getLlm()).thenReturn("other");
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(null);
        Mockito.when(configuration.getOllamaModelName()).thenReturn(null);
        assertNull(configuration.getOllamaBaseUrl());
        assertNull(configuration.getOllamaModelName());
    }
}
