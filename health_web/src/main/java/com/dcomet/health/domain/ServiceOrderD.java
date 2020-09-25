package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class ServiceOrderD extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer sodSoRid;
    private Integer sodVisRid;
    private Integer sodPatRid;
    private String sodNodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSodSoRid() {
        return sodSoRid;
    }

    public void setSodSoRid(Integer sodSoRid) {
        this.sodSoRid = sodSoRid;
    }

    public Integer getSodVisRid() {
        return sodVisRid;
    }

    public void setSodVisRid(Integer sodVisRid) {
        this.sodVisRid = sodVisRid;
    }

    public Integer getSodPatRid() {
        return sodPatRid;
    }

    public void setSodPatRid(Integer sodPatRid) {
        this.sodPatRid = sodPatRid;
    }

    public String getSodNodes() {
        return sodNodes;
    }

    public void setSodNodes(String sodNodes) {
        this.sodNodes = sodNodes;
    }

    @Override
    public String toString() {
        
        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tsodSoRid: Integer=");
        sb.append(sodSoRid);
        sb.append(";");

        sb.append("\n\tsodVisRid: Integer=");
        sb.append(sodVisRid);
        sb.append(";");

        sb.append("\n\tsodPatRid: Integer=");
        sb.append(sodPatRid);
        sb.append(";");

        sb.append("\n\tsodNodes: String=");
        sb.append(sodNodes);
        sb.append(";");

        return sb.toString();
    }

}
