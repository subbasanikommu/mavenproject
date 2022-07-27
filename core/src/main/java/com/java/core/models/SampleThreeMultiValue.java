package com.java.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleThreeMultiValue {
    private static final Logger LOG = LoggerFactory.getLogger(SampleThreeMultiValue.class);
    @SlingObject
    Resource componentResource;
    @ValueMapValue
    private String name;
    @ChildResource
    Resource Resource;
    List<Map<String,String>> multifieldText;

    public String getName() {
        return name;
    }

    public List<Map<String, String>> getMultifieldText() {
        List<Map<String,String>> bookDetailsMap = new ArrayList<>();
        try {
            Resource bookDetail = componentResource.getChild("multifieldText");
            if (bookDetail !=null){
                for (Resource book : bookDetail.getChildren()){
                    Map<String,String> bookMap = new HashMap<>();
                    bookMap.put("authorName", book.getValueMap().get("authorName", String.class));
                    bookMap.put("authorBook", book.getValueMap().get("authorBook", String.class));
                    bookMap.put("publishYear", book.getValueMap().get("publishYear", String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details {}",e.getMessage());
        }
        LOG.info("\n SIZE {}", bookDetailsMap.size());
        return bookDetailsMap;
    }
}