package cloud.isaura.adso.core.infrastructure.integration;

import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.output.Response;

public class DoubleEchoLanguageModel implements LanguageModel
{
    @Override
    public Response<String> generate(String prompt)
    {
        return Response.from(prompt + " " + prompt);
    }

    @Override
    public Response<String> generate(Prompt prompt)
    {
        return Response.from(prompt.text() + " " + prompt.text());
    }
}
