package com.java.core.models;

import com.sun.org.apache.xerces.internal.dom.ParentNode;
//import javafx.scene.Parent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleOne {
    private static final Logger LOG = LoggerFactory.getLogger(SampleOne.class);
    @Inject
    private String title;
    @Inject
    private String titleLink;
    @Inject
    private String fileReference;
    @Inject
    private String description;
    private String createdBy;
    private String lastModifiedBy;
    private String allPropertyNames;
    private String allPropertyValues;
    private String created;
    private String resourceType;
    private String primaryType;
    private String parentResourcePath;
    @SlingObject
    Resource currentResource;

    public String getTitle() {
        return title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public String getFileReference() {
        return fileReference;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getAllPropertyNames() {
        return allPropertyNames;
    }

    public String getAllPropertyValues() {
        return allPropertyValues;
    }

    public String getCreated() {
        return created;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public String getParentResourcePath() {
        return parentResourcePath;
    }

    @PostConstruct
    protected void init() {
        try {
            LOG.info("current resource path"+currentResource.getPath() );
            title = title + "my own title";
            Node currentNode = currentResource.adaptTo(Node.class);
            createdBy = currentNode.getProperty("jcr:created").getString();
            lastModifiedBy = currentNode.getProperty("jcr:lastModified").getString();
           PropertyIterator p1 = currentNode.getProperties();
           while (p1.hasNext()){
             Property prop = p1.nextProperty();
             allPropertyNames = allPropertyNames +" , " + prop.getName();
             allPropertyValues = allPropertyValues + ", "+ prop.getValue().getString();
           }
         ValueMap vm = currentResource.adaptTo(ValueMap.class);
          created = vm.get("jcr:createdBy", String.class);
         resourceType = vm.get("sling:resourceType", String.class);
         primaryType = vm.get("jcr:primaryType", String.class);
         parentResourcePath = currentNode.getParent().getPath();
        }catch (Exception e){
            LOG.error("Exception in reading properties", e);
        }
    }
}
