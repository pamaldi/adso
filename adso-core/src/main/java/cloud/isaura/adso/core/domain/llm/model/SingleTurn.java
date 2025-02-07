package cloud.isaura.adso.core.domain.llm.model;

import cloud.isaura.adso.core.infrastructure.integration.LlmProducer;
import jakarta.inject.Inject;

public class SingleTurn implements OneShot
{

    private InterlocutorPair interlocutorPair;


    private LlmProducer producer;

    public SingleTurn(InterlocutorPair interlocutorPair, LlmProducer producer)
    {
        this.interlocutorPair = interlocutorPair;
        this.producer = producer;
    }

    @Override
    public Message send(Message message)
    {

        return new Message (producer.produceLanguageModel().chat(message.content()),Interlocutor.LLM) ;
    }

    @Override
    public void pair(InterlocutorPair interlocutorPair)
    {
        Boolean validInterlocutorPair = (interlocutorPair.one().equals(Interlocutor.HUMAN) && interlocutorPair.two().equals(Interlocutor.LLM)) || (interlocutorPair.one().equals(Interlocutor.LLM) && interlocutorPair.two().equals(Interlocutor.HUMAN));
        if(!validInterlocutorPair)
        {
            throw new IllegalArgumentException("Invalid interlocutor pair");
        }
    }
}
