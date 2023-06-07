package cloud.isaura.adso.math;

import java.math.BigDecimal;

public class FunEvaluator
{
    public static BigDecimal evaluate(Fun fun, BigDecimal x)
    {
        return fun.apply(x);
    }
}
