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
public class InterfaceNode implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String InterfaceDescript;
    
    private String interfaceName;
    
    private List<MethodNode> methodNodeList = new ArrayList<>();
    
    
    /**
     * Returns this interfaceDescript object.
     * @return this interfaceDescript
     */
    public String getInterfaceDescript() {
        return InterfaceDescript;
    }


    
    /**
     * Sets this interfaceDescript.
     * @param interfaceDescript this interfaceDescript to set
     */
    public void setInterfaceDescript(String interfaceDescript) {
        InterfaceDescript = interfaceDescript;
    }


    /**
     * Returns this interfaceName object.
     * @return this interfaceName
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    
    /**
     * Sets this interfaceName.
     * @param interfaceName this interfaceName to set
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    
    /**
     * Returns this methodNodeList object.
     * @return this methodNodeList
     */
    public List<MethodNode> getMethodNodeList() {
        return methodNodeList;
    }

    
    /**
     * Sets this methodNodeList.
     * @param methodNodeList this methodNodeList to set
     */
    public void setMethodNodeList(List<MethodNode> methodNodeList) {
        this.methodNodeList = methodNodeList;
    }

}
