package cloud.isaura.adso.tokenizers;

import java.util.HashMap;

public class Vocabulary
{
    private HashMap<Character, Long> vocabularyMap;

    public Vocabulary()
    {
        vocabularyMap = new HashMap<>();
    }

    public synchronized void addSynchChar(char c)
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
}
