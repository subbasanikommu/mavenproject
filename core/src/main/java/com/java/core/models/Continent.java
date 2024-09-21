package com.java.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Continent {

    @Inject
    private String name;

    @Inject
    private List<Country> countries;


    public String getName() {
        return name;
    }

    public List<Country> getCountries() {
        return countries;
    }

}
