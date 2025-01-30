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
    public void testConfigPropertyExists() {
        assertNotNull(config, "The Configuration should be injected");
    }

    @Test
    public void testLLmMandatoryProperties() {
        assertNotNull(config.getDefaultLlm(), "The default.llm should be not null");
        assertNotNull(config.getLlm(), "The llm should be not null");
    }



}
