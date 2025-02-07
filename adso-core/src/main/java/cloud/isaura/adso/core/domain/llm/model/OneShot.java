package cloud.isaura.adso.core.domain.llm.model;

public interface OneShot
{
    Message send(Message message);

    void pair(InterlocutorPair interlocutorPair);
}
