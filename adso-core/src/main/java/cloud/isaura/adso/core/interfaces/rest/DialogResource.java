package cloud.isaura.adso.core.interfaces.rest;

import cloud.isaura.adso.core.domain.llm.model.*;
import cloud.isaura.adso.core.interfaces.rest.request.PromptRequest;
import cloud.isaura.adso.core.interfaces.rest.request.PromptResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;

@Path("/dialog")
public class DialogResource
{
    private static final Logger LOG = Logger.getLogger(DialogResource.class);

    @Inject
    private DialogFactory dialogFactory;

    @Path("/one-shot")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response oneShot(PromptRequest request)
    {
        LOG.info("One shot");
        SingleTurn singleTurn = this.dialogFactory.createOneShot(new InterlocutorPair(Interlocutor.HUMAN,Interlocutor.LLM));
        Message response = singleTurn.send(new Message(request.getContent(),Interlocutor.HUMAN));
        return Response.status(Response.Status.CREATED)
                .entity(new PromptResponse(LocalDateTime.now(),response.content()))
                .build();
    }

}
