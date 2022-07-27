package com.java.core.models.Impl;

import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.java.core.models.SampleTwo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,adapters = SampleTwo.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleTwoImpl implements SampleTwo {
    private static final Logger LOG = LoggerFactory.getLogger(SampleTwoImpl.class);
    @SlingObject
    ResourceResolver resourceResolver;
    @Self
    SlingHttpServletRequest slingHttpServletRequest;
    @OSGiService
    QueryBuilder queryBuilder;
    @ScriptVariable
    Page currentPage;
    @ResourcePath(path = "/content/mavenproject/en/english3")@Via("resource")
    Resource resource;
    @Inject
    @Via("resource")
    private String title;
    @Inject
    @Via("resource")
    private String titleLink;
    @Inject
    @Via("resource")
    private String fileReference;
    @Inject
    @Via("resource")
    private String description;
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getTitleLink() {
        return titleLink;
    }

    @Override
    public String getFileReference() {
        return fileReference;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getTitle();
    }

    @PostConstruct
        protected void init(){
        title = title + "my own title for this.";
        LOG.info("\n Inside INIT {} : {}", currentPage.getTitle(),resource.getPath());
    }
}
