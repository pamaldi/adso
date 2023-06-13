package cloud.isaura.adso.tokenizers;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TokenizersTest
{

    @Test
    public void verify_tokenizer_not_null()
    {
        AdsoTokenizer adsoTokenizer = new AdsoTokenizer();
        assertNotNull(adsoTokenizer);
    }

    @Test
    public void when_path_is_not_set_then_false()
    {
        AdsoTokenizer adsoTokenizer = new AdsoTokenizer();
        assertNotNull(adsoTokenizer);
        assertFalse(adsoTokenizer.fileExists());
    }


    @Test
    public void when_path_is_set_then_true() throws IOException
    {
        AdsoTokenizer adsoTokenizer = new AdsoTokenizer();
        adsoTokenizer.setFile("src/main/resources/promessisposi.txt");
        assertNotNull(adsoTokenizer);
        assertTrue(adsoTokenizer.fileExists());
        adsoTokenizer.load();
        System.out.println(GraphLayout.parseInstance(adsoTokenizer).toPrintable());
        //System.out.println(GraphLayout.parseInstance(adsoTokenizer).toFootprint());

    }


}
