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
@Table(name = "t_appointment_resource_map")
public class AppointmentResourceMapData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARM_RID", updatable = false)
    private Integer id;

    @Column(name = "ARM_APPT_RID ", updatable = false)
    private Integer armApptRid;

    @Column(name = "ARM_RESOURCE_RID")
    private Integer armResourceRid;

    public AppointmentResourceMapData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArmApptRid() {
        return armApptRid;
    }

    public void setArmApptRid(Integer armApptRid) {
        this.armApptRid = armApptRid;
    }

    public Integer getArmResourceRid() {
        return armResourceRid;
    }

    public void setArmResourceRid(Integer armResourceRid) {
        this.armResourceRid = armResourceRid;
    }
 @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tarmApptRid: Integer=");
        sb.append(armApptRid);
        sb.append(";");

        sb.append("\n\tarmResourceRid: Integer=");
        sb.append(armResourceRid);
        sb.append(";");
            return sb.toString();
}
}
