package me.pabloestrada.core.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class EnvironmentConfiguration {
    @JsonProperty("host")
    public String host;

    @JsonProperty("type")
    public String type;

    @Override
    public String toString() {
        return "EnvironmentConfiguration{" +
                "host='" + host + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
