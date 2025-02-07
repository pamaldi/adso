package cloud.isaura.adso.core.infrastructure.configuration;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class OllamaConfigurationTest
{
    public static final String OLLAMA = "ollama";
    public static final String OLLAMA_URL = "http://localhost:11434";
    public static final String OLLAMA_MODEL_NAME = "llama3";
    @InjectMock
    Configuration configuration;

    @BeforeEach
    public void setup()
    {

    }

    @Test
    public void given_ollama_llm_when_build_configuration_then_base_url_and_model_name_not_null()
    {
        Mockito.when(configuration.getOllamaBaseUrl()).thenReturn(OLLAMA_URL);
        Mockito.when(configuration.getOllamaModelName()).thenReturn(OLLAMA_MODEL_NAME);
        assertEquals(OLLAMA_URL, configuration.getOllamaBaseUrl());
        assertEquals(OLLAMA_MODEL_NAME, configuration.getOllamaModelName());
    }

}
