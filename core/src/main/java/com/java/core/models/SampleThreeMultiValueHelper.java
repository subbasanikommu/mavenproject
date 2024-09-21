package com.java.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.Date;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleThreeMultiValueHelper {
    @ValueMapValue
    private String bookname;
    @ValueMapValue
    private String publishdate;
    @ValueMapValue
    private String publishcopies;

    public String getBookname() {
        return bookname;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public String getPublishcopies() {
        return publishcopies;
    }
}
