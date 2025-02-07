package cloud.isaura.adso.core.interfaces.rest.request;

import java.time.LocalDateTime;

public class PromptResponse
{
    private final LocalDateTime time;
    private final String response;

    public PromptResponse(LocalDateTime time, String response)
    {
        this.time = time;
        this.response = response;
    }

    public LocalDateTime getTime()
    {
        return time;
    }


    public String getResponse()
    {
        return response;
    }


}
