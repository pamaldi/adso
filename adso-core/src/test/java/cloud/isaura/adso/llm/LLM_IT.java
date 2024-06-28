package cloud.isaura.adso.llm;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class LLM_IT
{

    @Inject
    LLM llm;

    @Inject
    LlmProducer llmProducer;

    @Test
    void testLLM_isNotNull()
    {
       Assertions.assertNotNull(llm);
    }


    @Test
    void given_dialogic_llm_when_request_then_response()
    {
        Assertions.assertNotNull(llm);
        String response = llm.exchange("hi");
        Assertions.assertNotNull(response);
    }


}
