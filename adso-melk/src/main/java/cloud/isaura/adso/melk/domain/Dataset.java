package cloud.isaura.adso.melk.domain;

public class Dataset
{
    private String name;
    private String description;
    private String uri;

    public void download()
    {
        if(uri== null)
        {
            throw new IllegalStateException("URI is not set");
        }

    }
}
