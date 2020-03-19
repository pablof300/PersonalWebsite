package me.pabloestrada.api.rest;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.ResponseAuthenticator;
import me.pabloestrada.api.dto.ExerciseSummaryDTO;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/exercise")
@Api(value = "/exercise")
public final class ExerciseTrackerRestMethods
{
    private ExerciseTrackerService delegate;
    private ResponseAuthenticator responseAuthenticator;

    @Inject
    public ExerciseTrackerRestMethods(final ExerciseTrackerService delegate, final ResponseAuthenticator responseAuthenticator) {
        this.delegate = delegate;
        this.responseAuthenticator = responseAuthenticator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Getting exercise summary")
    @Path("/exercise-summary")
    public ExerciseSummaryDTO getExerciseSummary(@HeaderParam("bearerAuth") final String bearerAuth,
                                                 @Context final HttpServletResponse response) {
        return responseAuthenticator.authenticateAndCatchErrors(bearerAuth, response, () -> delegate.getExerciseSummary());
    }


    @POST
    @ApiOperation(value = "add strava code")
    @Path("/strava")
    public void addStravaCode(@QueryParam("code") final String code,
                                @HeaderParam("bearerAuth") final String bearerAuth,
                                @Context final HttpServletResponse response) {
        responseAuthenticator.authenticate(bearerAuth, response, () -> delegate.addStravaCode(code));
    }

    @GET
    @ApiOperation(value = "getStravaStatus")
    @Path("/strava-status")
    public boolean getStravaStatus(@HeaderParam("bearerAuth") final String bearerAuth,
                                @Context final HttpServletResponse response) {
        return responseAuthenticator.authenticateAndCatchErrors(bearerAuth, response, () -> delegate.getStravaStatus());
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add gym session")
    @Path("/gym-session")
    public void addGymSession(@QueryParam("runningDistanceInMiles") final float runningDistanceInMiles,
                              @QueryParam("durationInMinutes") final int durationInMinutes,
                              @HeaderParam("bearerAuth") final String bearerAuth,
                              @Context final HttpServletResponse response) {
        responseAuthenticator.authenticate(bearerAuth, response, () -> delegate.addGymSession(runningDistanceInMiles, durationInMinutes));
    }
}
