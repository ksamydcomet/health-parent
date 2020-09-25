package com.dcomet.health.vo;

import com.dcomet.fw.domain.Base;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CVS
 */
public class CommonVo extends Base implements Serializable {

    private Integer vokey;
    private String voValue;

    private List<CommonVoD> commonVoDList;

    public Integer getVokey() {
        return vokey;
    }

    public void setVokey(Integer vokey) {
        this.vokey = vokey;
    }

    public String getVoValue() {
        return voValue;
    }

    public void setVoValue(String voValue) {
        this.voValue = voValue;
    }

    public List<CommonVoD> getCommonVoDList() {
        return commonVoDList;
    }

    public void setCommonVoDList(List<CommonVoD> commonVoDList) {
        this.commonVoDList = commonVoDList;
    }
}
