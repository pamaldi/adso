package cloud.isaura.adso.tokenizers;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Vocabulary
{
    private HashMap<Character, Long> vocabularyMap;

    private ConcurrentHashMap<Character, Long> concurrentHashMap;

    public Vocabulary()
    {
        vocabularyMap = new HashMap<>();
        concurrentHashMap = new ConcurrentHashMap<>();
    }

    public  void addSynchChar(char c)
    {
        if (concurrentHashMap.containsKey(c))
        {
            concurrentHashMap.put(c, concurrentHashMap.get(c) + 1);
        }
        else
        {
            concurrentHashMap.put(c, 1L);
        }
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
