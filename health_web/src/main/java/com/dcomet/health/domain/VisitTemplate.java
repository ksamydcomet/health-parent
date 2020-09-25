package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class VisitTemplate extends Base implements Serializable{
    
    private Integer id;
    private Integer vistVisitRID;
    private String vistGroup;
    private String vistSubGroup;
    private String vistType;
    private String vistSpeciality;
    private Integer vistPatRID;
    private String vistNodes;
    private Integer vistSeqNum;
 
    
    public  VisitTemplate(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVistVisitRID() {
        return vistVisitRID;
    }

    public void setVistVisitRID(Integer vistVisitRID) {
        this.vistVisitRID = vistVisitRID;
    }

    public String getVistGroup() {
        return vistGroup;
    }

    public void setVistGroup(String vistGroup) {
        this.vistGroup = vistGroup;
    }

    public String getVistSubGroup() {
        return vistSubGroup;
    }

    public void setVistSubGroup(String vistSubGroup) {
        this.vistSubGroup = vistSubGroup;
    }

    public String getVistType() {
        return vistType;
    }

    public void setVistType(String vistType) {
        this.vistType = vistType;
    }

    public String getVistSpeciality() {
        return vistSpeciality;
    }

    public void setVistSpeciality(String vistSpeciality) {
        this.vistSpeciality = vistSpeciality;
    }

    public Integer getVistPatRID() {
        return vistPatRID;
    }

    public void setVistPatRID(Integer vistPatRID) {
        this.vistPatRID = vistPatRID;
    }
    
    public String getVistNodes() {
        return vistNodes;
    }

    public void setVistNodes(String vistNodes) {
        this.vistNodes = vistNodes;
    }

    public Integer getVistSeqNum() {
        return vistSeqNum;
    }

    public void setVistSeqNum(Integer vistSeqNum) {
        this.vistSeqNum = vistSeqNum;
    }
    
    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tvistVisitRID: Integer=");
        sb.append(vistVisitRID);
        sb.append(";");

        sb.append("\n\tvistGroup: String=");
        sb.append(vistGroup);
        sb.append(";");

        sb.append("\n\tvistSubGroup: String=");
        sb.append(vistSubGroup);
        sb.append(";");

        sb.append("\n\tvistType: String=");
        sb.append(vistType);
        sb.append(";");

        sb.append("\n\tvistSpeciality: String=");
        sb.append(vistSpeciality);
        sb.append(";");

        sb.append("\n\tvistPatRID: Integer=");
        sb.append(vistPatRID);
        sb.append(";");       

        sb.append("\n\tvistNodes: String=");
        sb.append(vistNodes);
        sb.append(";");

        sb.append("\n\tvistSeqNum: Integer=");
        sb.append(vistSeqNum);
        sb.append(";");
        
        return sb.toString();
    }
}
 

