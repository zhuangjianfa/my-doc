/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.model;

import java.io.Serializable;

/**
 * @ClassDescription:TODO
 * Author	Date	Changes
 * zhuangjianfa  2018年3月8日 Created
 */
public class MethodNode implements Serializable{
    
    /**
     * description:TODO
     * remark:
     */
    private static final long serialVersionUID = 1L;

    /**
     * @description 作者
     */
    private String createdBy;
    
    /**
     * description 方法说明
     */
    private String description;
    
    /**
     * 方法名字
     */
    private String methodName;
    
    /**
     * 参数
     */
    private String requestName;

    /**
     * 返回值
     */
    private String responseName;
    
    /**
     * 创建时间
     */
    private String created;
    
    /**
     * 返回对象数据类型
     */
    private String dataType;
    
    /**
     * 路径
     */
    private String path;
    
    /**
     * 方法类型
     */
    private String methodType;
    
    /**
     * Returns this createdBy object.
     * @return this createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }


    
    /**
     * Sets this createdBy.
     * @param createdBy this createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Returns this description object.
     * @return this description
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * Sets this description.
     * @param description this description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /**
     * Returns this methodName object.
     * @return this methodName
     */
    public String getMethodName() {
        return methodName;
    }

    
    /**
     * Sets this methodName.
     * @param methodName this methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    
    /**
     * Returns this requestName object.
     * @return this requestName
     */
    public String getRequestName() {
        return requestName;
    }

    
    /**
     * Sets this requestName.
     * @param requestName this requestName to set
     */
    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }


    
    /**
     * Returns this responseName object.
     * @return this responseName
     */
    public String getResponseName() {
        return responseName;
    }


    
    /**
     * Sets this responseName.
     * @param responseName this responseName to set
     */
    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }


    
    /**
     * Returns this created object.
     * @return this created
     */
    public String getCreated() {
        return created;
    }


    
    /**
     * Sets this created.
     * @param created this created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }



    
    /**
     * Returns this dataType object.
     * @return this dataType
     */
    public String getDataType() {
        return dataType;
    }
    
    /**
     * Sets this dataType.
     * @param dataType this dataType to set
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }



    
    /**
     * Returns this path object.
     * @return this path
     */
    public String getPath() {
        return path;
    }



    
    /**
     * Sets this path.
     * @param path this path to set
     */
    public void setPath(String path) {
        this.path = path;
    }



    
    /**
     * Returns this methodType object.
     * @return this methodType
     */
    public String getMethodType() {
        return methodType;
    }



    
    /**
     * Sets this methodType.
     * @param methodType this methodType to set
     */
    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
    
    
}
