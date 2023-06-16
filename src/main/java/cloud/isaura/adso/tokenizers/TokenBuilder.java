package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.normalizers.Normalizer;

import java.util.List;

public class TokenBuilder implements Runnable
{

    public TokenBuilder(Vocabulary vocabulary)
    {
        this.vocabulary = vocabulary;
    }


    private Vocabulary vocabulary;
    public void perform(String current, List<Normalizer> normalizers, int start, int end)
    {

        //System.out.println("Thread "+Thread.currentThread()+"start: " + start + " end: " + end);

        for (Normalizer normalizer : normalizers)
        {
            current = normalizer.apply(current);
        }

        for (int i = 0; i < current.length(); i++)
        {
            char c = current.charAt(i);
            if (Character.isWhitespace(c))
            {
                continue;
            }
            vocabulary.addChar(c);
        }
        //System.out.println("Thread "+Thread.currentThread()+"start: " + start + " end: " + end);
    }


    @Override
    public void run()
    {
        this.vocabulary = new Vocabulary();
    }
}
