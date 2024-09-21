package com.java.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SampleThreeBean {
    public static final Logger LOG = LoggerFactory.getLogger(SampleThreeBean.class);
    private String bookname;
    private Date publishdate;
    private int publishcopies;

    public SampleThreeBean(Resource resource){
        try {
            if (StringUtils.isNoneBlank(resource.getValueMap().get("bookname", String.class))){
                this.bookname = resource.getValueMap().get("bookname",String.class);
            }
            if (resource.getValueMap().get("publishdate", Date.class) !=null){
                this.publishdate=resource.getValueMap().get("publishdate", Date.class);
            }
            if (resource.getValueMap().get("publishcopies", Integer.class) !=null){
                this.publishcopies=resource.getValueMap().get("publishcopies", Integer.class);
            }
        }catch (Exception e){
            LOG.info("\n BEAN ERROR : {}", e.getMessage());
        }
    }

    public String getBookname() {
        return bookname;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public int getPublishcopies() {
        return publishcopies;
    }
}
