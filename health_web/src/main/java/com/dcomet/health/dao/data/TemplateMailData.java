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
@Table(name = "s_template_mail")
public class TemplateMailData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stm_rid", updatable = false, insertable = false)
    private Integer id;

    @Column(name = "stm_code")
    private String stmCode;

    @Column(name = "stm_name")
    private String stmName;

    @Column(name = "stm_node")
    private String stmNode;

    @Column(name = "stm_entity_rid")
    private Integer stmEntityRid;

    public TemplateMailData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStmCode() {
        return stmCode;
    }

    public void setStmCode(String stmCode) {
        this.stmCode = stmCode;
    }

    public String getStmName() {
        return stmName;
    }

    public void setStmName(String stmName) {
        this.stmName = stmName;
    }

    public String getStmNode() {
        return stmNode;
    }

    public void setStmNode(String stmNode) {
        this.stmNode = stmNode;
    }

    public Integer getStmEntityRid() {
        return stmEntityRid;
    }

    public void setStmEntityRid(Integer stmEntityRid) {
        this.stmEntityRid = stmEntityRid;
    }

    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\tstmCode: String=");
        sb.append(stmCode);
        sb.append(";");

        sb.append("\n\tstmName: String=");
        sb.append(stmName);
        sb.append(";");

        sb.append("\n\tstmNode: String=");
        sb.append(stmNode);
        sb.append(";");

        sb.append("\n\ttstmEntityRid: Integer=");
        sb.append(stmEntityRid);
        sb.append(";");

        return sb.toString();
    }
}
