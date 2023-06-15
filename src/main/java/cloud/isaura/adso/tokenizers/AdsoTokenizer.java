package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.files.AdsoFileReader;
import cloud.isaura.adso.normalizers.Normalizer;
import cloud.isaura.adso.normalizers.NormalizerType;
import cloud.isaura.adso.normalizers.NormalizersBuilder;
import cloud.isaura.adso.normalizers.NormalizersFactory;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AdsoTokenizer implements Tokenizer
{
    public static final long BUFFER_SIZE = 1000L;
    private String path;

    private List<Normalizer> normalizers;

    private AdsoFileReader adsoFileReader;

    private Long bufferSize;

    private Long getBufferSize()
    {
        return this.bufferSize!=null?this.bufferSize:BUFFER_SIZE;
    }


    private AdsoTokenizer()
    {
        this.normalizers = new ArrayList<>();

    }

    public Boolean fileExists()
    {
        if(Objects.isNull(this.path) || this.path.isBlank())
        {
            return false;
        }

        Path path = Paths.get(this.path);
        return Files.exists(path);
    }



    public void loadMulti() throws IOException
    {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores: " + availableProcessors);
        long realBufferSize = getBufferSize() * availableProcessors;
        System.out.println("Real buffer size: " + realBufferSize);
        this.adsoFileReader = new AdsoFileReader(this.path, realBufferSize);
        String current = null;
        Vocabulary vocabulary = new Vocabulary();
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(availableProcessors);
        while (this.adsoFileReader.hasNext())
        {
            byte[] bytes = this.adsoFileReader.next();
            current = new String(bytes, StandardCharsets.UTF_8);


               for(int i = 0; i < availableProcessors; i++)
               {
                     int start = i * (current.length() / availableProcessors);
                     int end = (i + 1) * (current.length() / availableProcessors);
                     Runnable worker = new TokenBuilder();
                        ((TokenBuilder) worker).perform(vocabulary, current.substring(start, end), this.normalizers);
               }

        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }
        System.out.println("Finished all threads");
        vocabulary.print();

    }


    public void load() throws IOException
    {

        long realBufferSize = getBufferSize();
        System.out.println("Real buffer size: " + realBufferSize);
        this.adsoFileReader = new AdsoFileReader(this.path, realBufferSize);
        String current = null;
        Vocabulary vocabulary = new Vocabulary();

        while (this.adsoFileReader.hasNext())
        {
            byte[] bytes = this.adsoFileReader.next();
            current = new String(bytes, StandardCharsets.UTF_8);
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
                vocabulary.addChar(c);
            }

        }

        System.out.println("Finished all threads");
        vocabulary.print();

    }

    public static AdsoTokenizer newAdsoTokenizer()
    {
        return new AdsoTokenizer();
    }

    public AdsoTokenizer withNormalizer(NormalizerType normalizerType)
    {
        this.normalizers.add(NormalizersFactory.getNormalizer(normalizerType));
        return this;
    }

    public  AdsoTokenizer withBufferSize(Long bufferSize)
    {
        this.bufferSize=bufferSize;
        return this;
    }

    public AdsoTokenizer withFile(String path)
    {
        this.path = path;
        return this;
    }

    public AdsoTokenizer build()
    {
        return this;
    }

}
