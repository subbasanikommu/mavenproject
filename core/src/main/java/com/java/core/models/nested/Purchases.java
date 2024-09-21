package com.java.core.models.nested;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Purchases {
    @Inject
    private String name;

    @Inject
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
