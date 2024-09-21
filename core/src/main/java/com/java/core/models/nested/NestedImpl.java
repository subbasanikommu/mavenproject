package com.java.core.models.nested;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NestedImpl {
    @Inject
    private List<Products> products;
    @Inject
    private List<Sales> sales;
    @Inject
    private List<Purchases> purchases;

    public List<Products> getProducts() {
        return products;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public List<Purchases> getPurchases() {
        return purchases;
    }
}
