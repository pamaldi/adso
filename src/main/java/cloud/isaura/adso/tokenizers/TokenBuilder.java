package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.normalizers.Normalizer;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TokenBuilder implements Runnable
{
    public void perform(Vocabulary vocabulary, String current, List<Normalizer> normalizers)

    {

        for(int m = 0; m < normalizers.size();m++)
        {
            Normalizer normalizer = normalizers.get(m);
            current = normalizer.apply(current);
        }

        for (int i = 0; i < current.length(); i++)
        {
            char c = current.charAt(i);
            if (Character.isWhitespace(c))
            {
                continue;
            }
            vocabulary.addSynchChar(c);
        }
    }


    @Override
    public void run()
    {

    }
}
