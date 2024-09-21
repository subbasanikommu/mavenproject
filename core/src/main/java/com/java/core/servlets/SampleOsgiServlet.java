package com.java.core.servlets;

import com.java.core.service.SampleImpl;
import com.java.core.service.SampleInterface;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service= Servlet.class,
        property={
                Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths="+ "/bin/sampletest"
        })
public class SampleOsgiServlet extends SlingAllMethodsServlet {

//    @Reference
//    SampleInterface sampleInterface;
    SampleImpl sm = new SampleImpl();
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(sm.getMessageMessage());
    }

}
