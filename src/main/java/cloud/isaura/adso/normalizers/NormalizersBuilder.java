package cloud.isaura.adso.normalizers;


import java.util.ArrayList;
import java.util.List;

public class NormalizersBuilder
{
    private List<Normalizer> normalizerList;

    private NormalizersBuilder()
    {
        normalizerList = new ArrayList<>();
    }

    public static NormalizersBuilder newBuilder()
    {
        return new NormalizersBuilder();
    }

    public NormalizersBuilder withNormalizer(Normalizer normalizer)
    {
        this.normalizerList.add(normalizer);
        return this;
    }

    public List<Normalizer> build()
    {
        return this.normalizerList;
    }
}
