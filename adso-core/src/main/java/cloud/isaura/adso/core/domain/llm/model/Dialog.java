package cloud.isaura.adso.core.domain.llm.model;

import cloud.isaura.adso.core.infrastructure.integration.LlmProducer;


public class Dialog implements Dialogic
{
    private InterlocutorPair interlocutorPair;
    private DialogStatus status;


    private LlmProducer producer;

    public Dialog(InterlocutorPair interlocutorPair, LlmProducer llmProducer)
    {
        this.interlocutorPair = interlocutorPair;
        this.status = DialogStatus.CREATING;
        this.producer = llmProducer;
    }

    @Override
    public void start()
    {
        this.status = DialogStatus.CREATED;
    }

    @Override
    public void end()
    {
        this.status = DialogStatus.CLOSED;
    }

    @Override
    public Message send(Message message)
    {
        if(status.equals(DialogStatus.CREATING) || status.equals(DialogStatus.CLOSED))
        {
            throw new IllegalArgumentException("Dialog status not created");
        }

        if(interlocutorPair == null)
        {
            throw new IllegalArgumentException("Dialog status not created");
        }

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
