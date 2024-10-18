package cloud.isaura.adso.tools;

import cloud.isaura.adso.configurations.Configuration;
import cloud.isaura.adso.llm.LanguageModelCode;
import cloud.isaura.adso.llm.LlmProducer;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class LlmTool_Test
{
    @Inject
    LlmTool llmTool;

    //@InjectMock
    //LlmProducer llmProducer;

    @Test
    public void testLlmToolExists() {

        assertNotNull(llmTool, "The LLM Tool should be injected");
        String r = llmTool.exchange("hi", LanguageModelCode.ECHO);
        assertNotNull(r);
    }

    @Test
    public void given_llm_tool_when_exchange_then_response_ok() {

        assertNotNull(llmTool, "The LLM Tool should be injected");
        String r = llmTool.exchange("hi", LanguageModelCode.ECHO);
        assertNotNull(r);
        assertEquals(r, "hi hi");
    }



}
