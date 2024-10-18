package cloud.isaura.adso.tools;

import cloud.isaura.adso.llm.LanguageModelCode;

public interface Dialogic
{
    String exchange(String request);

    String exchange(String request, LanguageModelCode languageModelCode);
}
