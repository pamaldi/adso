package cloud.isaura.adso.math;

import java.math.BigDecimal;

@FunctionalInterface
public interface Fun
{
    BigDecimal apply(BigDecimal x);
}
