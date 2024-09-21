package com.java.core.models;

import java.util.List;
import java.util.Map;

public interface SampleThreeMulti {
    String getName();
    List<Map<String,String>> getMultifieldText();
    List<SampleThreeBean> getMultifieldBook();
}
