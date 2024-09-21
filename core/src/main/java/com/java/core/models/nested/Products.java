package com.java.core.models.nested;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Products {
    @Inject
    private String name;

    @Inject
    private String description;
    @Inject
    private List<NestedProducts> nestedproducts;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public List<NestedProducts> getNestedproducts() {
        return nestedproducts;
    }
}
