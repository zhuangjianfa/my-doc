/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.model;

import java.io.Serializable;

import com.owner.java.api.doc.enums.YesOrNo;

/**
 * description:属性模型
 * Author	Date	Changes
 * zhuangjianfa  2018年3月6日 Created
 */
public class AttributeNode implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 属性名称
     */
    private String attrName;
    
    /**
     * 属性类型
     */
    private String attrType;
    
    /**
     * 属性是否必填
     */
    private String isRequired = YesOrNo.NO.getValue();
    
    /**
     * 说明
     */
    private String descript = "";
    
    /**
     * 属性备注
     */
    private String remark = "";

    
    /**
     * Returns this attrName object.
     * @return this attrName
     */
    public String getAttrName() {
        return attrName;
    }

    
    /**
     * Sets this attrName.
     * @param attrName this attrName to set
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    
    /**
     * Returns this attrType object.
     * @return this attrType
     */
    public String getAttrType() {
        return attrType;
    }

    
    /**
     * Sets this attrType.
     * @param attrType this attrType to set
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    
    /**
     * Returns this isRequired object.
     * @return this isRequired
     */
    public String getIsRequired() {
        return isRequired;
    }

    
    /**
     * Sets this isRequired.
     * @param isRequired this isRequired to set
     */
    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    
    /**
     * Returns this descript object.
     * @return this descript
     */
    public String getDescript() {
        return descript;
    }

    
    /**
     * Sets this descript.
     * @param descript this descript to set
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    
    /**
     * Returns this remark object.
     * @return this remark
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * Sets this remark.
     * @param remark this remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
    
}
