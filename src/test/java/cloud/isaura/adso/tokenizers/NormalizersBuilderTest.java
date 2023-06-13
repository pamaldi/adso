package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.normalizers.LowerCaseNormalizer;
import cloud.isaura.adso.normalizers.Normalizer;
import cloud.isaura.adso.normalizers.NormalizersBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class NormalizersBuilderTest
{
    @Test
    public void when_add_normalizer_then_list_has_size_one()
    {
        List<Normalizer> normalizerList = NormalizersBuilder
                .newBuilder().withNormalizer(new LowerCaseNormalizer()).build();
        assertNotNull(normalizerList);
        assertFalse(normalizerList.isEmpty());
        assertTrue(normalizerList.size()==1);
    }
}
