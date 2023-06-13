package cloud.isaura.adso.tokenizers;

import cloud.isaura.adso.files.AdsoFileReader;
import cloud.isaura.adso.normalizers.Normalizer;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdsoTokenizer implements Tokenizer
{
    private String path;

    private List<Normalizer> normalizers;

    private AdsoFileReader adsoFileReader;


    public AdsoTokenizer()
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

    public void setFile(String path)
    {
        this.path = path;
    }

    public void load() throws IOException
    {

        this.adsoFileReader = new AdsoFileReader(this.path,1000L);
        String current = null;
        while (this.adsoFileReader.hasNext())
        {
            byte[] bytes = this.adsoFileReader.next();
            current = new String(bytes, StandardCharsets.UTF_8);

        }

    }



}
