package me.pabloestrada.api;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/PersonalWebsite")
@Api(value = "/PersonalWebsite")
public class PersonalWebsiteRestMethods
{
    private PersonalWebsiteService delegate;

    @Inject
    public PersonalWebsiteRestMethods(final PersonalWebsiteService delegate) {
        this.delegate = delegate;
    }

    @GET
    @ApiOperation(value = "Getting developer name")
    @Path("/dev")
    public String getDeveloperName() {
        return delegate.getDeveloperName();
    }
}
