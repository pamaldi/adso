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

    private static void bench01()
    {
        AdsoTokenizer adsoTokenizer = new AdsoTokenizer();
        adsoTokenizer.setFile("src/main/resources/promessisposi.txt");
        NormalizersBuilder.newBuilder().withNormalizer(new LowerCaseNormalizer()).build();
        adsoTokenizer.withNormalizer(NormalizerType.LOWER_CASE);
        try
        {
            adsoTokenizer.load();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void bench02()
    {
        for(int i = 0; i < 1000;i++)
        {
            AdsoTokenizer adsoTokenizer = new AdsoTokenizer();
            adsoTokenizer.setFile("src/main/resources/promessisposi.txt");
            NormalizersBuilder.newBuilder().withNormalizer(new LowerCaseNormalizer()).build();
            adsoTokenizer.withNormalizer(NormalizerType.LOWER_CASE);
            try
            {
                adsoTokenizer.load();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }


    private static void bench03()
    {
        for(int i = 0; i < 1000;i++)
        {
            AdsoTokenizer adsoTokenizer = new AdsoTokenizer();
            adsoTokenizer.setFile("src/main/resources/promessisposi.txt");
            NormalizersBuilder.newBuilder().withNormalizer(new LowerCaseNormalizer()).build();
            adsoTokenizer.withNormalizer(NormalizerType.LOWER_CASE);
            try
            {
                adsoTokenizer.loadCharBuffer();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        Long before =  MemoryUtils.getCurrent();
        bench03();
        Long after =  MemoryUtils.getCurrent();
        System.out.println("Memory used: " + (after - before));
        MemoryUtils.print();
    }


}
