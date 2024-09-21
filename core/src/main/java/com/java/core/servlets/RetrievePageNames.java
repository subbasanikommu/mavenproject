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
package com.java.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Iterator;

@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.paths="+ "/bin/listOfPages"
           })
public class RetrievePageNames extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(RetrievePageNames.class);

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource userRes = resourceResolver.getResource("/content/mavenproject");
        Page rootPage = userRes.adaptTo(Page.class);
        Iterator<Page> iteratorPage = rootPage.listChildren(new PageFilter(), true);

        JSONArray jsonArray = new JSONArray();
        try { 
            while (iteratorPage.hasNext()) {
                JSONObject jsonObject = new JSONObject();
                Page eachPage = iteratorPage.next();

                jsonObject.put("pageName", eachPage.getName());
                jsonObject.put("pagePath", eachPage.getPath());

                //getting page properties
                Node eachNode = eachPage.adaptTo(Node.class);
                if (eachNode.hasNode("jcr:content")){
                    Node jcrNode = eachNode.getNode("jcr:content");
                    PropertyIterator p1 = jcrNode.getProperties();
                    JSONObject jsonObject1 =  new JSONObject();
                    while (p1.hasNext()){
                        Property prop = p1.nextProperty();
                        if (!prop.isMultiple()){
                            jsonObject1.put(prop.getName(),prop.getValue().getString());
                        }
                    }
                    jsonObject.put("pageProperties", jsonObject1);
                }
                jsonArray.put(jsonObject);
            }
            response.getWriter().write(jsonArray.toString());
        } catch (Exception e) {
            LOG.error("Exception in reading username and password", e);
        }
    }
}
