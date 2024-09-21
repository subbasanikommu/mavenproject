package com.java.core.servlets;

import com.java.core.service.SampleRegistrationInterface;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service= Servlet.class,
        property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/sampleRegistration"
        })
public class SampleRegistration extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(SampleRegistration.class);
    @Reference
    SampleRegistrationInterface  srs;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        String status = srs.saveUserDetails(firstName,middleName,lastName,email,phoneNumber,userName,password);
        response.getWriter().write(status);
    }
}
