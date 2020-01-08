package me.pabloestrada.api.rest;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.WebsiteGeneratorService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/website-generator")
@Api(value = "/website-generator")
public final class WebsiteGeneratorRestMethods {
    private WebsiteGeneratorService delegate;

    @Inject
    public WebsiteGeneratorRestMethods(final WebsiteGeneratorService delegate) {
        this.delegate = delegate;
    }

    @POST
    @ApiOperation(value = "Generating the website UI landing page with new update information from website_data.json")
    @Path("/generate-website")
    public void generateWebsite() {
        delegate.generateWebsite();
    }
}
