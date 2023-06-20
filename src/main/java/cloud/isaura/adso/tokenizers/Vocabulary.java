package cloud.isaura.adso.tokenizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Vocabulary
{
   private List<String> vocabularyList;

    public Vocabulary()
    {
        vocabularyList = new ArrayList<>();
    }



    public  void addChar(char c)
    {
        if (vocabularyMap.containsKey(c))
        {
            vocabularyMap.put(c, vocabularyMap.get(c) + 1);
        }
        else
        {
            vocabularyMap.put(c, 1L);
        }
    }

    public void print()
    {
        this.vocabularyMap.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    public void printMulti()
    {
        this.concurrentHashMap.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
