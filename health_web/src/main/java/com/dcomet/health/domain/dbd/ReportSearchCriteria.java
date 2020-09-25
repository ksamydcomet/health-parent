package com.dcomet.health.domain.dbd;

import com.dcomet.fw.domain.SearchCriteria;

/**
 *
 * @author Dev1
 */
public class ReportSearchCriteria extends SearchCriteria {

    private Integer entityId;
    private String from;
    private String to;

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tEntity: Integer=");
        sb.append(entityId);
        sb.append(";");

        sb.append("\n\tFrom: String=");
        sb.append(from);
        sb.append(";");

        sb.append("\n\tTo: String=");
        sb.append(to);
        sb.append(";");

        return sb.toString();
    }

}
