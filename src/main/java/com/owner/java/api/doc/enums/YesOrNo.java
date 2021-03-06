/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.enums;


/**
 * description:TODO
 * Author	Date	Changes
 * zhuangjianfa  2018年3月9日 Created
 */
public enum YesOrNo {
    YES("Y"),
    
    NO("N");
    
    private String value;

    /**
     * TODO Class constructors.
     * @param value
     */
    private YesOrNo(String value){
        this.value = value;
    }

    
    /**
     * Returns this value object.
     * @return this value
     */
    public String getValue() {
        return value;
    }
    
}
