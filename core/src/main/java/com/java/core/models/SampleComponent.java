/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.java.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import java.util.Iterator;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleComponent {
    private static final Logger LOG = LoggerFactory.getLogger(SampleComponent.class);
    @Inject
    private String title;
    @Inject
    private String titleLink;
    @Inject
    private String fileReference;
    @Inject
    private String description;
    private String createdBy;
    private String lastModified;
    private String allPropertyValues;
    private String allPropertyName;
    private String lastModifiedBy;
    private String resourceType;
    private String currentResourcePath;
    private String currentResourceName;
    private String parentResourcePath;
    private String parentResourceName;
    private String parentPatentName;
    private String childResource;

    public String getChildResource() {
        return childResource;
    }

    public String getCurrentResourceName() {
        return currentResourceName;
    }

    public String getParentPatentName() {
        return parentPatentName;
    }

    public String getCurrentResourcePath() {
        return currentResourcePath;
    }

    public String getParentResourcePath() {
        return parentResourcePath;
    }

    public String getParentResourceName() {
        return parentResourceName;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getAllPropertyValues() {
        return allPropertyValues;
    }

    public String getAllPropertyName() {
        return allPropertyName;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

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

    @Self
    Resource currentResource;
    @SlingObject
    ResourceResolver resourceResolver;
    @PostConstruct
    protected void init() {
        try {
            LOG.info("current resource path"+ currentResource.getPath());
            title = title + "my own title";
            Node currentNode = currentResource.adaptTo(Node.class);
            createdBy = currentNode.getProperty("jcr:created").getString();
            lastModified = currentNode.getProperty("jcr:lastModified").getString();
            PropertyIterator p1 = currentNode.getProperties();
            while (p1.hasNext()){
               Property prop = p1.nextProperty();
               allPropertyName =  allPropertyName + "," + prop.getName();
               allPropertyValues = allPropertyValues + prop.getValue();
            }
           ValueMap vm = currentResource.adaptTo(ValueMap.class);

           lastModifiedBy = vm.get("jcr:lastModifiedBy", String.class);
          resourceType = vm.get("sling:resourceType", String.class);
         currentResourcePath = currentResource.getPath();
        parentResourceName = currentNode.getParent().getName();
       parentResourcePath = currentNode.getParent().getPath();
      Resource resource = resourceResolver.getResource("/content/mavenproject/en/samplepage/jcr:content/root/responsivegrid");
      Iterator<Resource> iterator = resource.listChildren();
      while (iterator.hasNext()){
         Resource childRes = iterator.next();
         childResource = childResource  +"," + childRes.getName();
      }
     Resource resResource = resourceResolver.getResource("/content/dam/mavenproject/assets");
     Node resNode = resResource.adaptTo(Node.class);
    NodeIterator ni = resNode.getNodes();
    while (ni.hasNext()){
       Node eachNode = ni.nextNode();
      if (!eachNode.getName().equalsIgnoreCase("jcr:content")){
         Node jcrNode = eachNode.getNode("jcr:content");
         jcrNode.setProperty("cricket","yes");
         jcrNode.getSession().save();

         //adding new node
         Node addedNode = eachNode.addNode("sample","nt:unstructured");
         addedNode.getSession().save();
      }
    }

        }catch (Exception e){
            LOG.error("Exception in reading properties", e);
        }

    }

}