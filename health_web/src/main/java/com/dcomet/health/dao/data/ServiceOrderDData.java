package com.dcomet.health.dao.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev4
 */
@Entity
@Table(name = "t_service_order_d")
public class ServiceOrderDData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SOD_RID", updatable = false)
    private Integer id;

    @Column(name = "SOD_SO_RID")
    private Integer sodSoRid;

    @Column(name = "SOD_VIS_RID")
    private Integer sodVisRid;

    @Column(name = "SOD_PAT_RID")
    private Integer sodPatRid;

    @Column(name = "SOD_NODES")
    private String sodNodes;

    public ServiceOrderDData() {
    }

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
