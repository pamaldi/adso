package cloud.isaura.adso.core.infrastructure.configuration;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ConfigurationTest
{

    @Inject
    Configuration config;

    @Test
    public void testConfigPropertyExists()
    {
        assertNotNull(config, "The Configuration should be injected");
    }

    @Test
    public void testLLmMandatoryProperties()
    {
        assertNotNull(config.getOllamaBaseUrl(), "Ollama base url should be not null");
        assertNotNull(config.getOllamaModelName(), "Ollama model name should be not null");
    }


}
