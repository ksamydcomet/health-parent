package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ABDUL
 */
public class ResourceAvailabilityWithCategory extends Base implements Serializable {

    private Integer resourceCategory;
    private String categoryName;

    private List<AvailableResource> availableResources;

    public ResourceAvailabilityWithCategory() {

    }

    public Integer getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(Integer resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<AvailableResource> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(List<AvailableResource> availableResources) {
        this.availableResources = availableResources;
    }

}
