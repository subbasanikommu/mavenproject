package com.java.core.service;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true)
public class SampleImpl{
    public String message;

    public String getMessageMessage() {
        return "HelloMessage";
    }
}
