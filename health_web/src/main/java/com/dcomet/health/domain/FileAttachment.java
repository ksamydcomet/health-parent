package com.dcomet.health.domain;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;

/**
 *
 * @author Dev4
 */
public class FileAttachment extends Base implements Serializable {

    private Integer id;
    private Integer taType;
    private Integer taRefRid;
    private Integer taActive;
    private String taFileName;
    private String taFileDesc;
    private byte[] taFile;
    private Integer taEntityRid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaType() {
        return taType;
    }

    public void setTaType(Integer taType) {
        this.taType = taType;
    }

    public Integer getTaRefRid() {
        return taRefRid;
    }

    public void setTaRefRid(Integer taRefRid) {
        this.taRefRid = taRefRid;
    }

    public Integer getTaActive() {
        return taActive;
    }

    public void setTaActive(Integer taActive) {
        this.taActive = taActive;
    }

    public String getTaFileName() {
        return taFileName;
    }

    public void setTaFileName(String taFileName) {
        this.taFileName = taFileName;
    }

    public String getTaFileDesc() {
        return taFileDesc;
    }

    public void setTaFileDesc(String taFileDesc) {
        this.taFileDesc = taFileDesc;
    }

    public byte[] getTaFile() {
        return taFile;
    }

    public void setTaFile(byte[] taFile) {
        this.taFile = taFile;
    }

    public Integer getTaEntityRid() {
        return taEntityRid;
    }

    public void setTaEntityRid(Integer taEntityRid) {
        this.taEntityRid = taEntityRid;
    }
    
    @Override
    public String toString() {

        super.toString();
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tid: Integer=");
        sb.append(id);
        sb.append(";");

        sb.append("\n\ttaType: Integer=");
        sb.append(taType);
        sb.append(";");

        sb.append("\n\ttaRefRid: Integer=");
        sb.append(taRefRid);
        sb.append(";");

        sb.append("\n\ttaActive: Integer=");
        sb.append(taActive);
        sb.append(";");

        sb.append("\n\ttaFileName: String=");
        sb.append(taFileName);
        sb.append(";");

        sb.append("\n\ttaFileDesc: String=");
        sb.append(taFileDesc);
        sb.append(";");

        sb.append("\n\ttaFile: byte=");
        sb.append(taFile);
        sb.append(";");
        
        sb.append("\n\ttaEntityRid: Integer=");
        sb.append(taEntityRid);
        sb.append(";");

        return sb.toString();
    }
}
