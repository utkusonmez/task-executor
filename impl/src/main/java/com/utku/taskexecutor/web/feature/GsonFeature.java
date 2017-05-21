package com.utku.taskexecutor.web.feature;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.InternalProperties;
import org.glassfish.jersey.internal.util.PropertiesHelper;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * GsonFeature
 */
public class GsonFeature implements Feature {
    public static final String JSON_FEATURE = GsonFeature.class.getSimpleName();

    @Override
    public boolean configure(final FeatureContext context) {
        final Configuration config = context.getConfiguration();

        final String jsonFeature = CommonProperties.getValue(config.getProperties(), config.getRuntimeType(),
                InternalProperties.JSON_FEATURE, JSON_FEATURE, String.class);

        // Other JSON providers registered
        if (!JSON_FEATURE.equalsIgnoreCase(jsonFeature)) {
            return false;
        }

        // Disable other JSON providers
        context.property(PropertiesHelper.getPropertyNameForRuntime(InternalProperties.JSON_FEATURE,
                config.getRuntimeType()), JSON_FEATURE);

        // Register GSON
        if (!config.isRegistered(GsonProvider.class)) {
            context.register(GsonProvider.class);
        }

        return true;
    }
}
