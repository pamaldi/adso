package cloud.isaura.adso.bench;

import cloud.isaura.adso.memory.MemoryUtils;
import cloud.isaura.adso.normalizers.LowerCaseNormalizer;
import cloud.isaura.adso.normalizers.NormalizerType;
import cloud.isaura.adso.normalizers.NormalizersBuilder;
import cloud.isaura.adso.tokenizers.AdsoTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class TokenizerMemoryBench
{


    private static void benchMono()
    {
        for(int i = 0; i < 1;i++)
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
    }

    private static void benchMulti()
    {
        for(int i = 0; i < 1;i++)
        {
            AdsoTokenizer adsoTokenizer = AdsoTokenizer.newAdsoTokenizer()
                    .withNormalizer(NormalizerType.LOWER_CASE)
                    .withFile("src/main/resources/drugsComTrain_raw.tsv")
                    .withBufferSize(1000L)
                    .build();
            try
            {
                adsoTokenizer.loadMulti();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }




    public static void main(String[] args) throws IOException
    {
        Long before =  MemoryUtils.getCurrent();
        Long start = System.currentTimeMillis();
        benchMulti();
        Long end = System.currentTimeMillis();
        Long after =  MemoryUtils.getCurrent();
        System.out.println("Memory used: " + (after - before));
        System.out.println("Time elapsed: " + (end - start));
        //MemoryUtils.print();
    }


}
