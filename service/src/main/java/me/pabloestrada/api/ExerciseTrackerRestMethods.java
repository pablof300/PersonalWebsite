package me.pabloestrada.api;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/exercise")
@Api(value = "/exercise")
public class ExerciseTrackerRestMethods
{
    private ExerciseTrackerService delegate;

    @Inject
    public ExerciseTrackerRestMethods(final ExerciseTrackerService delegate) {
        this.delegate = delegate;
    }

    @GET
    @ApiOperation(value = "Getting developer name")
    @Path("/dev")
    public String getDeveloperName() {
        return delegate.getDeveloperName();
    }
}
