package cloud.isaura.adso.core.domain.llm.model;

import cloud.isaura.adso.core.infrastructure.integration.LlmProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DialogFactory
{
    @Inject
    private LlmProducer producer;

    public Dialog createDialog(InterlocutorPair pair) {

        return new Dialog(pair,producer);
    }

    public SingleTurn createOneShot(InterlocutorPair pair) {
        SingleTurn singleTurn = new SingleTurn(producer);
        singleTurn.pair(pair);
        return singleTurn;
    }
}
