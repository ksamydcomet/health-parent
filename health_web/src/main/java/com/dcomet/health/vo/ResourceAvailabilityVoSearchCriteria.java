package com.dcomet.health.vo;

import com.dcomet.fw.domain.SearchCriteria;
import java.util.List;

/**
 *
 * @author ABDUL
 */
public class ResourceAvailabilityVoSearchCriteria extends SearchCriteria {

    private String scheduledDateAndTime;
    private String estimatedDuration;
    private String estimatedIndex;
    private Integer resEntityRid;

    private List<ResourceAvailabilityWithCategory> resourceAvailabilityWithCategorys;

    public String getScheduledDateAndTime() {
        return scheduledDateAndTime;
    }

    public void setScheduledDateAndTime(String scheduledDateAndTime) {
        this.scheduledDateAndTime = scheduledDateAndTime;
    }

    public String getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(String estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getEstimatedIndex() {
        return estimatedIndex;
    }

    public void setEstimatedIndex(String estimatedIndex) {
        this.estimatedIndex = estimatedIndex;
    }

    public Integer getResEntityRid() {
        return resEntityRid;
    }

    public void setResEntityRid(Integer resEntityRid) {
        this.resEntityRid = resEntityRid;
    }

    public List<ResourceAvailabilityWithCategory> getResourceAvailabilityWithCategorys() {
        return resourceAvailabilityWithCategorys;
    }

    public void setResourceAvailabilityWithCategorys(List<ResourceAvailabilityWithCategory> resourceAvailabilityWithCategorys) {
        this.resourceAvailabilityWithCategorys = resourceAvailabilityWithCategorys;
    }

}
