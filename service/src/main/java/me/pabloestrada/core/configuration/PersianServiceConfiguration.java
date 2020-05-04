package me.pabloestrada.core.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class PersianServiceConfiguration
    extends Configuration
{
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("databaseURI")
    public String databaseURI;

    @JsonProperty("environment")
    public EnvironmentConfiguration environmentConfiguration;
}
