package com.dcomet.health.dao.data;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "t_appointment_token")
public class AppointmentTokenData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOKEN_ENT_RID", updatable = false)
    private Integer id;

    @Column(name = "TOKEN_UNIT_RID" ,updatable = false)
    private Integer tokenUnitRID;

    @Column(name = "TOKEN_DATE")
    private Date tokenDate;

    @Column(name = "TOKEN_NUMBER" , updatable = false)
    private Integer tokenNumber;

    public AppointmentTokenData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTokenUnitRID() {
        return tokenUnitRID;
    }

    public void setTokenUnitRID(Integer tokenUnitRID) {
        this.tokenUnitRID = tokenUnitRID;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public Integer getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(Integer tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\ttokenUnitRID: Integer=");
        sb.append(tokenUnitRID);
        sb.append(";");

        sb.append("\n\ttokenDate: Date=");
        sb.append(tokenDate);
        sb.append(";");

        sb.append("\n\ttokenNumber: Integer=");
        sb.append(tokenNumber);
        sb.append(";");
        return sb.toString();
    }
}