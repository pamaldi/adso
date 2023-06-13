package cloud.isaura.adso.tokenizers;


import cloud.isaura.adso.normalizers.Normalizer;
import cloud.isaura.adso.normalizers.NormalizerType;
import cloud.isaura.adso.normalizers.NormalizersFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class NormalizersFactoryTest
{
    @Test
    public void when_normalizers_factory_then_not_null()
    {
        Normalizer normalizer = NormalizersFactory.getNormalizer(NormalizerType.LOWER_CASE);
        assertNotNull(normalizer);
    }
}
