package cloud.isaura.adso.llm;

public enum LanguageModelCode
{

    OLLAMA("ollama"), OPEN_AI("open_ai");

    LanguageModelCode(String name)
    {

        this.name = name;
    }

    private String name;

    public String getName()
    {
        return name;
    }

    public static LanguageModelCode fromName(String name) {
        for (LanguageModelCode code : LanguageModelCode.values()) {
            if (code.getName().equalsIgnoreCase(name)) {
                return code;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }
}
