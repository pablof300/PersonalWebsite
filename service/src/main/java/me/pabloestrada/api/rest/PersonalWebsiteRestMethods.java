package me.pabloestrada.api.rest;

import com.google.inject.Inject;
import io.swagger.annotations.*;
import me.pabloestrada.api.PersonalWebsiteService;
import me.pabloestrada.api.ResponseAuthenticator;
import me.pabloestrada.core.personalwebsite.websiteinfo.FullWebsiteInfo;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/personal-website")
@Api(value = "/personal-website")
public final class PersonalWebsiteRestMethods {
    private PersonalWebsiteService delegate;
    private ResponseAuthenticator responseAuthenticator;

    @Inject
    public PersonalWebsiteRestMethods(final PersonalWebsiteService delegate, final ResponseAuthenticator responseAuthenticator) {
        this.delegate = delegate;
        this.responseAuthenticator = responseAuthenticator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get full website info using the model FullWebsiteInfo")
    @Path("/website")
    public FullWebsiteInfo getWebsiteInfo() {
        return delegate.getWebsiteInfo();
    }

    @PUT
    @ApiOperation(value = "Update base website info")
    @Path("/base-website")
    public void updateBaseWebsiteInfo(@QueryParam("firstParagraphOfAboutMe") final String firstParagraphOfAboutMe,
                                      @QueryParam("secondParagraphOfAboutMe") final String secondParagraphOfAboutMe,
                                      @QueryParam("listOfTechnicalLanguages") final String listOfTechnicalLanguages,
                                      @QueryParam("listOfFrameworks") final String listOfFrameworks,
                                      @QueryParam("listOfTools") final String listOfTools,
                                      @QueryParam("listOfLanguages") final String listOfLanguages,
                                      @QueryParam("resumePath") final String resumePath,
                                      @HeaderParam("bearerAuth") final String bearerAuth,
                                      @Context final HttpServletResponse response)
    {
      responseAuthenticator.authenticate(bearerAuth, response,
              () -> delegate.updateBaseWebsiteInfo(firstParagraphOfAboutMe, secondParagraphOfAboutMe, listOfTechnicalLanguages,
                      listOfFrameworks, listOfTools, listOfLanguages, resumePath));
    }

    @PUT
    @ApiOperation(value = "Get website info using the model WebsiteInfo")
    @Path("/projects")
    public void updateProjectInfo(@QueryParam("name") final String name,
                                  @QueryParam("type") final String type,
                                  @QueryParam("description") final String description,
                                  @QueryParam("funFact") final String funFact,
                                  @QueryParam("url") final String url,
                                  @QueryParam("imagePath") final String imagePath,
                                  @QueryParam("year") final int year,
                                  @QueryParam("id") final String id,
                                  @HeaderParam("bearerAuth") final String bearerAuth,
                                  @Context final HttpServletResponse response)
    {
        responseAuthenticator.authenticate(bearerAuth, response,
                () -> delegate.updateProjectInfo(name, type, description, funFact, url, imagePath, year, id));
    }

    @POST
    @ApiOperation(value = "Add a new project and get its id")
    @Path("/projects")
    public String addProjectInfo(@QueryParam("name") final String name,
                                 @QueryParam("type") final String type,
                                 @QueryParam("description") final String description,
                                 @QueryParam("funFact") final String funFact,
                                 @QueryParam("url") final String url,
                                 @QueryParam("imagePath") final String imagePath,
                                 @QueryParam("year") final int year,
                                 @HeaderParam("bearerAuth") final String bearerAuth,
                                 @Context final HttpServletResponse response)
    {
        return responseAuthenticator.authenticateAndCatchErrors(
                bearerAuth, response, () -> delegate.addProjectInfo(name, type, description, funFact, url, imagePath, year).toString());
    }
}


// TODO:
// Refactor to use Dropwizard auth:
//     @ApiOperation(value = "Get full website info using the model FullWebsiteInfo"
//            authorizations = {
//                    @Authorization(value="bearerAuth",
//                            scopes = {}
//                            )
//            }
//            )
// @SwaggerDefinition(securityDefinition = @SecurityDefinition(apiKeyAuthDefinitions = {
//        @ApiKeyAuthDefinition(key = "bearerAuth", name = "Authorization", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER)
//    }
//))