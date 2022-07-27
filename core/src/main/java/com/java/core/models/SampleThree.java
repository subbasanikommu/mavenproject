package com.java.core.models;

import com.java.core.models.Impl.SampleTwoImpl;
import org.apache.commons.logging.Log;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,adapters = SampleThreeMulti.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleThree implements SampleThreeMulti{
    private static final Logger LOG = LoggerFactory.getLogger(SampleThree.class);
    @Inject
    Resource componentResource;

    @ValueMapValue
    @Default(values = "AEM")
    private String name;


    @Override
    public String getName() {
        return name;
    }

    @Override
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
