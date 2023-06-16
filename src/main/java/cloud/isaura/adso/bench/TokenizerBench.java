package cloud.isaura.adso.bench;

import cloud.isaura.adso.memory.MemoryUtils;
import cloud.isaura.adso.normalizers.NormalizerType;
import cloud.isaura.adso.tokenizers.AdsoTokenizer;

import java.io.IOException;

public class TokenizerBench
{


    public void benchMono() throws IOException
    {
        System.out.println("Starting mono");
        Long before = MemoryUtils.getCurrent();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++)
        {
            AdsoTokenizer adsoTokenizer = AdsoTokenizer.newAdsoTokenizer()
                    .withNormalizer(NormalizerType.LOWER_CASE)
                    .withFile("src/main/resources/drugsComTrain_raw.tsv")
                    .withBufferSize(1000L)
                    .build();
            try
            {
                adsoTokenizer.load();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }
        Long end = System.currentTimeMillis();
        Long after = MemoryUtils.getCurrent();
        System.out.println("Memory used: " + (after - before));
        System.out.println("Time elapsed: " + (end - start));
    }






    public void benchMultiVT() throws IOException
    {
        System.out.println("Starting multiVT");
        Long before = MemoryUtils.getCurrent();
        Long start = System.currentTimeMillis();
        for(int i = 0; i < 1;i++)
        {
            AdsoTokenizer adsoTokenizer = AdsoTokenizer.newAdsoTokenizer()
                    .withNormalizer(NormalizerType.LOWER_CASE)
                    .withFile("src/main/resources/drugsComTrain_raw.tsv")
                    .withBufferSize(842891L)
                    .build();
            try
            {
                adsoTokenizer.loadMultiVT();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        Long end = System.currentTimeMillis();
        Long after = MemoryUtils.getCurrent();
        System.out.println("Memory used: " + (after - before));
        System.out.println("Time elapsed: " + (end - start));
    }

    public void benchMulti() throws IOException
    {
        System.out.println("Starting multi");
        Long before = MemoryUtils.getCurrent();
        Long start = System.currentTimeMillis();
        for(int i = 0; i < 1;i++)
        {
            AdsoTokenizer adsoTokenizer = AdsoTokenizer.newAdsoTokenizer()
                    .withNormalizer(NormalizerType.LOWER_CASE)
                    .withFile("src/main/resources/drugsComTrain_raw.tsv")
                    .withBufferSize(842891L)
                    .build();
            try
            {
                adsoTokenizer.loadMulti();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        Long end = System.currentTimeMillis();
        Long after = MemoryUtils.getCurrent();
        System.out.println("Memory used: " + (after - before));
        System.out.println("Time elapsed: " + (end - start));
    }







}
