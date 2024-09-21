package com.java.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DemoModelImpl {

    @Inject
    private List<Continent> continents;
    @Inject
    private List<Product> products;

    public List<Continent> getContinents() {
        return continents;
    }

    public List<Product> getProducts() {
        return products;
    }
}
