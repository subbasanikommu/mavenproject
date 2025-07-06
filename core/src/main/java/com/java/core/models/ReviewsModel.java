package com.java.core.models;

import java.util.List;
import java.util.Map;

public interface ReviewsModel {
    String getComponentId();
    String getReviewsTitle();
    String getReviewsHeadingDropdown();
    List<Map<String, String>> getReviewsMultifields();
    boolean isDisableAmp();
    String getNavigation();
}