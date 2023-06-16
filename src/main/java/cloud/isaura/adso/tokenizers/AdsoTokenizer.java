package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.files.AdsoFileReader;
import cloud.isaura.adso.normalizers.Normalizer;
import cloud.isaura.adso.normalizers.NormalizerType;
import cloud.isaura.adso.normalizers.NormalizersFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        int numberOfThreads = 12;
        System.out.println("Number of vt: " + numberOfThreads);
        Long startTimeFile = System.currentTimeMillis();
        SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get(path), StandardOpenOption.READ);
        Long fileSize = seekableByteChannel.size();
        System.out.println("size: " + fileSize);
        ByteBuffer bf = ByteBuffer.allocate(fileSize.intValue());
        seekableByteChannel.read(bf);
        final String current = new String(bf.array(), StandardCharsets.UTF_8);
        Vocabulary vocabulary = new Vocabulary();
        Long endTimeFile = System.currentTimeMillis();
        System.out.println("Time to read file: " + (endTimeFile - startTimeFile));
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        for(int i = 0; i < numberOfThreads; i++)
        {
            int start = i * (current.length() / numberOfThreads);
            int end = (i + 1) * (current.length() / numberOfThreads);
            executor.submit(()-> {
                TokenBuilder worker = new TokenBuilder(vocabulary);
                ((TokenBuilder) worker).perform(current.substring(start, end), this.normalizers,start,end);
            });


        }
        System.out.println("Finished all threads");
        //vocabulary.print();
        executor.shutdown();
        while (executor.isTerminated() == false)
        {
        }




        System.out.println("Finished all threads");
        //vocabulary.print();


    }


    public void loadMultiVT() throws IOException
    {
        int numberOfThreads = 12;
        System.out.println("Number of vt: " + numberOfThreads);
        Long startTimeFile = System.currentTimeMillis();
        SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get(path), StandardOpenOption.READ);
        Long fileSize = seekableByteChannel.size();
        System.out.println("size: " + fileSize);
        ByteBuffer bf = ByteBuffer.allocate(fileSize.intValue());
        seekableByteChannel.read(bf);
        final String current = new String(bf.array(), StandardCharsets.UTF_8);
        Vocabulary vocabulary = new Vocabulary();
        Long endTimeFile = System.currentTimeMillis();
        System.out.println("Time to read file: " + (endTimeFile - startTimeFile));
        for(int i = 0; i < numberOfThreads; i++)
            {
                int start = i * (current.length() / numberOfThreads);
                int end = (i + 1) * (current.length() / numberOfThreads);
                Runnable worker = new TokenBuilder(vocabulary);
                ((TokenBuilder) worker).perform(current.substring(start, end), this.normalizers,start,end);
                Thread.startVirtualThread(worker);
            }
        System.out.println("Finished all threads");
        //vocabulary.print();


    }

    public void load() throws IOException
    {


        Long startTimeFile = System.currentTimeMillis();
        SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get(path), StandardOpenOption.READ);
        Long fileSize = seekableByteChannel.size();
        System.out.println("size: " + fileSize);
        ByteBuffer bf = ByteBuffer.allocate(fileSize.intValue());
        seekableByteChannel.read(bf);
        String current = new String(bf.array(), StandardCharsets.UTF_8);
        Vocabulary vocabulary = new Vocabulary();
        Long endTimeFile = System.currentTimeMillis();
        System.out.println("Time to read file: " + (endTimeFile - startTimeFile));
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
        //vocabulary.print();


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
