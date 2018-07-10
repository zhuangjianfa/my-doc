/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * description:TODO
 * Author	Date	Changes
 * zhuangjianfa  2018年3月6日 Created
 */
public class ReqRespNode implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 属性列表
     */
    private List<AttributeNode> attrList = new ArrayList<AttributeNode>();
    
    /**
     * 继承类名称
     */
    private String extendsClass;
    
    /**
     * 全称
     */
    private String fullName;
    
    /**
     * 属性包含自定义对象
     */
    private List<String> objList = new ArrayList<String>();

    
    /**
     * Returns this attrList object.
     * @return this attrList
     */
    public List<AttributeNode> getAttrList() {
        return attrList;
    }

    
    /**
     * Sets this attrList.
     * @param attrList this attrList to set
     */
    public void setAttrList(List<AttributeNode> attrList) {
        this.attrList = attrList;
    }

    
    /**
     * Returns this extendsClass object.
     * @return this extendsClass
     */
    public String getExtendsClass() {
        return extendsClass;
    }

    
    /**
     * Sets this extendsClass.
     * @param extendsClass this extendsClass to set
     */
    public void setExtendsClass(String extendsClass) {
        this.extendsClass = extendsClass;
    }

    
    /**
     * Returns this objList object.
     * @return this objList
     */
    public List<String> getObjList() {
        return objList;
    }

    
    /**
     * Sets this objList.
     * @param objList this objList to set
     */
    public void setObjList(List<String> objList) {
        this.objList = objList;
    }


    
    /**
     * Returns this fullName object.
     * @return this fullName
     */
    public String getFullName() {
        return fullName;
    }


    
    /**
     * Sets this fullName.
     * @param fullName this fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
}
