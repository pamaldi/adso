package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.files.AdsoFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AdsoFileReaderTest
{

    @Test
    public void verify_reader_not_null() throws IOException
    {
        AdsoFileReader adsoFileReader = new AdsoFileReader("src/test/resources/promessisposi.txt",1000L);
        assertNotNull(adsoFileReader);
    }

    @Test
    public void verify_file_size() throws IOException
    {
        AdsoFileReader adsoFileReader = new AdsoFileReader("src/test/resources/promessisposi.txt",1000L);
        assertNotNull(adsoFileReader);
        Long fileSize = adsoFileReader.fileSize();
        assertNotNull(fileSize);
        assertTrue(fileSize>0);
    }

    @Test
    public void verify_number_of_blocks() throws IOException
    {
        AdsoFileReader adsoFileReader = new AdsoFileReader("src/test/resources/promessisposi.txt",1000L);
        assertNotNull(adsoFileReader);
        Long fileSize = adsoFileReader.fileSize();
        assertNotNull(fileSize);
        assertTrue(fileSize>0);
        Long numberOfBlocks = adsoFileReader.fileSize()/1000L;
        Long numberOfCall = 0L;
        while(adsoFileReader.hasNext())
        {
            byte[] bytes = adsoFileReader.next();
            assertNotNull(bytes);
            assertNotNull(bytes.length>0);
            numberOfCall = numberOfCall+1;
        }
        System.out.println("num "+numberOfBlocks+" numcall "+numberOfCall);
        assertTrue(numberOfBlocks+1 == numberOfCall);

    }
}
