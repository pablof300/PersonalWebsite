package me.pabloestrada;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.jersey.filter.AllowedMethodsFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import me.pabloestrada.api.PersonalWebsiteRestMethods;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class PersonalWebsiteApplication
    extends Application<PersonalWebsiteConfiguration>
{
    public static void main(final String[] args) throws Exception {
        new PersonalWebsiteApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<PersonalWebsiteConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<PersonalWebsiteConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final PersonalWebsiteConfiguration configuration)
            {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final PersonalWebsiteConfiguration configuration, final Environment environment) {
        final FilterRegistration.Dynamic cors = environment.servlets()
                .addFilter("CORSFilter", CrossOriginFilter.class);

        cors.addMappingForUrlPatterns(
                EnumSet.allOf(DispatcherType.class), false, environment.getApplicationContext().getContextPath() + "*");
        cors.setInitParameter(AllowedMethodsFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,HEAD,OPTIONS");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM, "Link");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        final Injector injector = Guice.createInjector(new PersonalWebsiteModule());
        final PersonalWebsiteRestMethods api = injector.getInstance(PersonalWebsiteRestMethods.class);

        environment.jersey().register(api);
    }

    @Override
    public String getName() {
        return "Hello Pablo :)";
    }
}
