package cloud.isaura.adso.core.interfaces.rest;

import cloud.isaura.adso.core.domain.llm.service.LanguageModelService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.util.Set;

@Path("/")
public class LanguageModelResource
{
    private static final Logger LOG = Logger.getLogger(LanguageModelResource.class);

    @Inject
    private LanguageModelService languageModelService;

    @Path("available-models")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> availableModels()
    {
        LOG.info("Available models");
        return languageModelService.availableModels();
    }
}
