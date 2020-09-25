package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author CVS
 */
public class AvailableResource extends Base implements Serializable {

    private Integer resourceRid;
    private String resourceName;

    public AvailableResource() {

    }

    public Integer getResourceRid() {
        return resourceRid;
    }

    public void setResourceRid(Integer resourceRid) {
        this.resourceRid = resourceRid;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
