package cloud.isaura.adso.configurations;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class Configuration
{
    @ConfigProperty(name = "cloud.isaura.adso.llm.default")
    private String defaultLlm;

    @ConfigProperty(name = "cloud.isaura.adso.llm")
    private String llm;

    @ConfigProperty(name = "cloud.isaura.adso.llm.ollama.baseUrl")
    private String ollamaBaseUrl;

    @ConfigProperty(name = "cloud.isaura.adso.llm.ollama.modelName")
    private String ollamaModelName;

    public String getDefaultLlm() {
        return defaultLlm;
    }

    public String getOllamaModelName() {
        return ollamaModelName;
    }

    public String getOllamaBaseUrl() {
        return ollamaBaseUrl;
    }

    public String getLlm() {
        return llm;
    }
}
