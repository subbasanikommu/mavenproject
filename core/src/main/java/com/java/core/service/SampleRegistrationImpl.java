package com.java.core.service;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(immediate = true, service = SampleRegistrationInterface.class)
public class SampleRegistrationImpl implements SampleRegistrationInterface{
    @Reference
    ResourceResolverFactory rrf;

    @Override
    public String saveUserDetails(String firstName, String middleName, String lastName, String email, String phoneNumber, String userName, String password) {
        String status = "";
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "writeService");

        try {
            ResourceResolver rr = rrf.getServiceResourceResolver(param);
            Resource resource = rr.getResource("/content/mavenproject");
            Node node = resource.adaptTo(Node.class);
            Session ses = node.getSession();
            Node userNameNode = node.addNode(userName, "nt:unstructured");
            userNameNode.getSession().save();
            userNameNode.setProperty("firstName", firstName);
            userNameNode.setProperty("middleName", middleName);
            userNameNode.setProperty("lastName", lastName);
            userNameNode.setProperty("email", email);
            userNameNode.setProperty("phoneNumber", phoneNumber);
            userNameNode.setProperty("userName", userName);
            userNameNode.setProperty("password", password);
            userNameNode.getSession().save();
            status = "success";
        }catch (Exception e){
            status = "failed";
        }
        return status;
    }
}
