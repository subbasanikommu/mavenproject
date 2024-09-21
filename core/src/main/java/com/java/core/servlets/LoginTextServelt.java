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

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.paths="+ "/bin/logintest"
           })
public class  LoginTextServelt extends SlingAllMethodsServlet {

        private static final long serialVersionUID = 1L;
        private static final Logger LOG = LoggerFactory.getLogger(LoginTextServelt.class);
    @Override
    protected void doGet( SlingHttpServletRequest request,
             SlingHttpServletResponse response) throws IOException {
                doPost(request,response);
         }
    @Override
    protected void doPost( SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws IOException {
       ResourceResolver resourceResolver = request.getResourceResolver();
       Resource userRes = resourceResolver.getResource("/content/mavenproject/en/english3");
       Iterator<Resource> iterator = userRes.listChildren();
       String username = request.getParameter("username");
       LOG.info("given username value"+ username);
       String password = request.getParameter("password");
        LOG.info("given password value"+ password);
       boolean flag = false;
       try {
           while (iterator.hasNext()){
               Resource eachRes = iterator.next();
              Node eachNode = eachRes.adaptTo(Node.class);
              if (eachNode.hasProperty("username") && eachNode.hasProperty("password")){
                  String userNameFromNode = eachNode.getProperty("username").getString();
                  String passWordFromNode = eachNode.getProperty("password").getString();
                  if (username.equalsIgnoreCase(userNameFromNode) && password.equalsIgnoreCase(passWordFromNode)){
                      flag = true;
                      break;
                  }
              }


           }
           if (flag){
               response.getWriter().write("authenticated");
           }else {
               response.getWriter().write("user not found");
           }
       }catch (Exception e){
            LOG.error("Exception in reading username and password", e);
       }

       String username1 = request.getParameter("username");
       String password1 = request.getParameter("password");
       if (username1.equalsIgnoreCase(password1)){
           response.getWriter().write("success");
       }else {
           response.getWriter().write("failed");
       }
    }
}
