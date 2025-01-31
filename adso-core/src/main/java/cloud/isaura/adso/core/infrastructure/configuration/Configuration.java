package cloud.isaura.adso.core.infrastructure.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class Configuration
{

    @ConfigProperty(name = "cloud.isaura.adso.llm.ollama.baseUrl")
    private String ollamaBaseUrl;

    @ConfigProperty(name = "cloud.isaura.adso.llm.ollama.modelName")
    private String ollamaModelName;

    public String getOllamaModelName() {
        return ollamaModelName;
    }

    public String getOllamaBaseUrl() {
        return ollamaBaseUrl;
    }

}
