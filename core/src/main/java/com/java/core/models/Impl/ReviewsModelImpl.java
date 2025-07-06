package com.java.core.models.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.java.core.models.ReviewsModel;

// import com.bajaj.bfl.core.models.ReviewsModel;

@Model(adaptables = {
        Resource.class }, adapters = ReviewsModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReviewsModelImpl implements ReviewsModel {
    @ValueMapValue
    private String reviewsTitle;

    @ValueMapValue
    private String reviewsHeadingDropdown;

    @Self
    private Resource componentResource;

    @ValueMapValue
    private String componentId;
    
    @ValueMapValue
    private boolean disableAmp;

    Integer cardLength = 0;

    @Override
    public String getComponentId() {
        return componentId;
    }

    @Override
    public String getReviewsTitle() {
        return reviewsTitle;
    }

    @Override
    public String getReviewsHeadingDropdown() {
        return reviewsHeadingDropdown;
    }

    @Override
    public List<Map<String, String>> getReviewsMultifields() {
        List<Map<String, String>> reviewsMap = new ArrayList<>();
        Resource reviewsMultifields = componentResource.getChild("reviewsMultifields");
        if (reviewsMultifields != null) {
            for (Resource reviewsMultiDetails : reviewsMultifields.getChildren()) {
                Map<String, String> rsMap = new HashMap<>();
                rsMap.put("cardTitle", reviewsMultiDetails.getValueMap().get("cardTitle", String.class));
                rsMap.put("cardDescription", reviewsMultiDetails.getValueMap().get("cardDescription", String.class));
                rsMap.put("cardName", reviewsMultiDetails.getValueMap().get("cardName", String.class));
                reviewsMap.add(rsMap);
                cardLength += 1;
            }
            return reviewsMap;
        }
        return reviewsMap;
    }
    
    @Override
    public boolean isDisableAmp() {
        return disableAmp;
    }

    @Override
    public String getNavigation() {
        String htmlStringg = "";
        htmlStringg= "<span class = 'rvs-nvg-dots' option=0 selected><p>1/" + Integer.toString(cardLength)+ "</p></span>";
        for(int i=1;i<cardLength;i++){
            htmlStringg += "<span class = 'rvs-nvg-dots' option=" + Integer.toString(i) +"><p>" + Integer.toString(i+1)+ "/" + Integer.toString(cardLength)+ "</p></span>";
        }
        return htmlStringg;
    }

}
