package cloud.isaura.adso.normalizers;

import java.util.Map;

public class NormalizersFactory
{
    private NormalizersFactory(){}

    private static final Map<NormalizerType, Normalizer> NORMALIZER_MAP =
            Map.of (

                     NormalizerType.LOWER_CASE, new LowerCaseNormalizer()

                    );

    public static Normalizer getNormalizer(NormalizerType normalizerType) {
        if (NORMALIZER_MAP.containsKey(normalizerType)) {
            return NORMALIZER_MAP.get(normalizerType);
        }
        throw new UnsupportedOperationException();
    }
}
