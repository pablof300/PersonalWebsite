package me.pabloestrada.api.rest;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.pabloestrada.api.PersonalWebsiteService;
import me.pabloestrada.core.personalwebsite.websiteinfo.FullWebsiteInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/personal-website")
@Api(value = "/personal-website")
public final class PersonalWebsiteRestMethods {
    private PersonalWebsiteService delegate;

    @Inject
    public PersonalWebsiteRestMethods(final PersonalWebsiteService delegate) {
        this.delegate = delegate;
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
                                      @QueryParam("resumePath") final String resumePath
    )
    {
        delegate.updateBaseWebsiteInfo(firstParagraphOfAboutMe, secondParagraphOfAboutMe, listOfTechnicalLanguages,
                listOfFrameworks, listOfTools, listOfLanguages, resumePath);
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
                                  @QueryParam("id") final String id)
    {
        System.out.println(id);
        delegate.updateProjectInfo(name, type, description, funFact, url, imagePath, year, id);
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
                               @QueryParam("year") final int year)
    {
        return delegate.addProjectInfo(name, type, description, funFact, url, imagePath, year).toString();
    }
}
