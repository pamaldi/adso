package cloud.isaura.adso;

import cloud.isaura.adso.math.Fun;
import cloud.isaura.adso.math.FunEvaluator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class FunTest
{

    @Test
    public void whenCreateAFunctionThenNotNull()
    {
        FunEvaluator.evaluate(x -> new BigDecimal(5), new BigDecimal(4));
    }
}
